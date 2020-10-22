package com.sparta.svilen.Model;

import com.sparta.svilen.EmployeeDTO;
import com.sparta.svilen.Printer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class DatabaseDAO {
    private final Properties dbProperties = new Properties();
    private Connection connection;
    private final HashMap<Integer, EmployeeDTO> employeeRecords = new HashMap<>();
    private final HashMap<Integer, EmployeeDTO> employeeDuplicateRecords = new HashMap<>();

    public DatabaseDAO() { }

    private void getConnection() throws IOException, SQLException {
            dbProperties.load(new FileReader("resources/database.properties"));
            connection = DriverManager.getConnection(dbProperties.getProperty("url"), dbProperties.getProperty("username"), dbProperties.getProperty("password"));
    }

    public void insertEmployee(EmployeeDTO employee) {
        try {
            String queryInsertEmployee = "INSERT INTO `dbemployees`.`employees` (`employee_id`, `name_prefix`, " +
                    "`first_name`, `middle_initial`, `last_name`, `gender`, `email`, `dob`,`date_joined`, `salary`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(queryInsertEmployee);
            preparedStatementAssignParameters(preparedStatement, employee);
            preparedStatement.execute();
            if (preparedStatement.executeUpdate() == 1) {
                Printer.print("Insertion successful.");
            } else {
                Printer.print("Insertion failed.");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEmployeesFromFile() {
        try (BufferedReader csvReader = new BufferedReader(new FileReader("resources/EmployeeRecords.csv"))) {
            getConnection();
            String[] data;
            String row;
            while ((row = csvReader.readLine()) != null) {
                if (checkRowForId(row)) continue;
                data = row.split(",");
                if (!employeeRecords.containsKey(Integer.parseInt(data[0]))){
                    insertEmployee(addEmployeeToHashMap(data, employeeRecords));
                } else {
                    addEmployeeToHashMap(data, employeeDuplicateRecords);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private boolean checkRowForId(String row) {
        return !Character.isDigit(row.trim().charAt(0));
    }

    private EmployeeDTO addEmployeeToHashMap(String[] data, HashMap<Integer, EmployeeDTO> integerEmployeeHashMap) {
        EmployeeDTO employee = new EmployeeDTO(data);
        integerEmployeeHashMap.put(employee.getEmployee_id(), employee);
        return employee;
    }

    private void preparedStatementAssignParameters(PreparedStatement preparedStatement, EmployeeDTO employee) {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}







