package com.item.test;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String name;
    private String gender;
    private Integer age;

    private List<Employee> employees;



    public Employee(String name, String gender, Integer ages) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        employees = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        employees.add(e);
    }

    public void remove(Employee e) {
        employees.remove(e);
    }

    public List<Employee> getSubordinates(){
        return employees;
    }

    @Override
    public String toString(){
        return ("Employee :[ 姓名 : "+ name
                +", 性别 : "+ gender + ", 年龄 :"
                + age+" ]");
    }

}
