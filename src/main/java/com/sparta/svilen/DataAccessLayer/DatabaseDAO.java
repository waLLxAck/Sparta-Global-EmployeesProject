package com.sparta.svilen.DataAccessLayer;

import com.sparta.svilen.Model.EmployeeDTO;
import com.sparta.svilen.View.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static com.sparta.svilen.Helpers.Printer.print;

public class DatabaseDAO {
    private PreparedStatement preparedStatement;
    private final Properties dbProperties = new Properties();
    private Connection connection;
    private final HashMap<Integer, EmployeeDTO> employeeRecords = new HashMap<>();
    private final HashMap<Integer, EmployeeDTO> employeeDuplicateRecords = new HashMap<>();
    private HashMap<Integer, EmployeeDTO> employeeRecordsDummy = new HashMap<>();
    private final List<Thread> threads = new ArrayList<>();

    //Settings
    public static final boolean DROP_TABLE_AND_CREATE_TABLE = true;
    private static final int PREPARED_STATEMENT_BATCH_SIZE = 250;
    public static final int NUMBER_OF_THREADS = 5;

    private static int HASHMAP_SPLIT_THRESHOLD;

    public DatabaseDAO() {
    }

    public void start(String fileName) {
        getConnection();
        try (BufferedReader csvReader = new BufferedReader(new FileReader("resources/" + fileName + ".csv"))) {
            HASHMAP_SPLIT_THRESHOLD = ((int) csvReader.lines().count())/NUMBER_OF_THREADS;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (DROP_TABLE_AND_CREATE_TABLE) {
                dropTableEmployee();
                createTableEmployee();
            }
            long start = System.nanoTime();
            insertEmployeesFromFile(fileName);
            for (Thread thread : threads) {
                thread.join();
            }
            print("Total time taken to add records: " + (System.nanoTime() - start)/1000000);
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        Logger.logDuplicates(employeeDuplicateRecords); //FIXME: Doesn't record the records
    }

    private void getConnection() {
        try {
            dbProperties.load(new FileReader("resources/database.properties"));
            connection = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties.getProperty("username"), dbProperties.getProperty("password"));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropTableEmployee() throws SQLException {
        String queryDropEmployee = "DROP TABLE IF EXISTS Employees;";
        preparedStatement = connection.prepareStatement(queryDropEmployee);
        preparedStatement.executeUpdate();
    }

    private void createTableEmployee() throws SQLException {
        String queryCreateTableEmployee = "CREATE TABLE Employees (\n" +
                "employee_id INT PRIMARY KEY,\n" +
                "name_prefix VARCHAR(6),\n" +
                "first_name VARCHAR(30),\n" +
                "middle_initial VARCHAR(1),\n" +
                "last_name VARCHAR(30),\n" +
                "gender VARCHAR(6),\n" +
                "email VARCHAR(40),\n" +
                "dob DATE,\n" +
                "date_joined DATE,\n" +
                "salary INT\n" +
                ");";

        preparedStatement = connection.prepareStatement(queryCreateTableEmployee);
        preparedStatement.executeUpdate();
    }

    public void insertEmployee(HashMap<Integer, EmployeeDTO> employees) {
        int counter = 0;
        try {
            getConnection();
            connection.setAutoCommit(false);
            String queryInsertEmployee = "INSERT INTO `dbemployees`.`employees` (`employee_id`, `name_prefix`, " +
                    "`first_name`, `middle_initial`, `last_name`, `gender`, `email`, `dob`,`date_joined`, `salary`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(queryInsertEmployee);
            for (EmployeeDTO e : employees.values()) {
                preparedStatementAssignParameters(e);
                counter++;
                if (counter % PREPARED_STATEMENT_BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch();
            connection.commit();
            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEmployeesFromFile(String employeeRecordsLarge) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader("resources/" + employeeRecordsLarge + ".csv"))) {
            csvReader.lines().filter(this::checkRowForId).map(row -> row.split(",")).forEach(entry -> {
                accept(entry);
                if (employeeRecords.size() % HASHMAP_SPLIT_THRESHOLD == 0) {
                    createNewThread(employeeRecordsDummy);
                    employeeRecordsDummy = new HashMap<>();
                }
            });
            createNewThread(employeeRecordsDummy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkRowForId(String row) {
        return Character.isDigit(row.trim().charAt(0));
    }

    private void addEmployeeToHashMap(String[] data, HashMap<Integer, EmployeeDTO> integerEmployeeHashMap) {
        EmployeeDTO employee = new EmployeeDTO(data);
        integerEmployeeHashMap.put(employee.getEmployee_id(), employee);
    }

    private void preparedStatementAssignParameters(EmployeeDTO employee) throws SQLException {
            preparedStatement.setInt(1, employee.getEmployee_id());
            preparedStatement.setString(2, employee.getName_prefix());
            preparedStatement.setString(3, employee.getFirst_name());
            preparedStatement.setString(4, String.valueOf(employee.getMiddle_initial()));
            preparedStatement.setString(5, employee.getLast_name());
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setObject(8, employee.getDob());
            preparedStatement.setObject(9, employee.getDate_joined());
            preparedStatement.setInt(10, employee.getSalary());
            preparedStatement.addBatch();
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void accept(String[] data) {
        if (employeeRecords.containsKey(Integer.parseInt(data[0]))) {
            addEmployeeToHashMap(data, employeeDuplicateRecords);
        } else {
            addEmployeeToHashMap(data, employeeRecords);
            addEmployeeToHashMap(data, employeeRecordsDummy);
        }
    }

    private void createNewThread(HashMap<Integer, EmployeeDTO> employeeRecordsDummy) {
        threads.add(new Thread(() -> {
            DatabaseDAO databaseDAO = new DatabaseDAO();
            databaseDAO.insertEmployee(employeeRecordsDummy);
        }));
        threads.get(threads.size()-1).start();
    }
}







