package com.sparta.svilen.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

public class EmployeeDTO {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/MM/yyyy");
    int employee_id, salary;
    LocalDate dob, date_joined;
    char middle_initial;
    String first_name, name_prefix, last_name, gender, email;

    public EmployeeDTO() {}

    public EmployeeDTO(int employee_id, int salary, LocalDate dob, LocalDate date_joined, String name_prefix, String first_name,
                       char middle_initial, String last_name, String gender, String email) {
        this.employee_id = employee_id;
        this.salary = salary;
        this.dob = dob;
        this.date_joined = date_joined;
        this.name_prefix = name_prefix;
        this.first_name = first_name;
        this.middle_initial = middle_initial;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
    }

    public EmployeeDTO(String[] data) {
        this.employee_id = Integer.parseInt(data[0]);
        this.name_prefix = data[1];
        this.first_name = data[2];
        this.middle_initial = data[3].charAt(0);
        this.last_name = data[4];
        this.gender = data[5];
        this.email = data[6];
        try {
            this.dob = simpleDateFormat.parse(data[7]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            this.date_joined = simpleDateFormat.parse(data[8]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.salary = Integer.parseInt(data[9]);
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(LocalDate date_joined) {
        this.date_joined = date_joined;
    }

    public char getMiddle_initial() {
        return middle_initial;
    }

    public void setMiddle_initial(char middle_initial) {
        this.middle_initial = middle_initial;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getName_prefix() {
        return name_prefix;
    }

    public void setName_prefix(String name_prefix) {
        this.name_prefix = name_prefix;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", salary=" + salary +
                ", dob=" + dob +
                ", date_joined=" + date_joined +
                ", middle_initial=" + middle_initial +
                ", first_name='" + first_name + '\'' +
                ", name_prefix='" + name_prefix + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
