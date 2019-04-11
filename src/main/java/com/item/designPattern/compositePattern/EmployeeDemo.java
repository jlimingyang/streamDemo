package com.item.designPattern.compositePattern;

public class EmployeeDemo {

    public static void main(String[] args) {
        Employee bb = new Employee("爸爸","男",56);
        Employee zz = new Employee("祖祖","男",188);
        Employee mm = new Employee("妈妈","女",50);
        Employee yy = new Employee("爷爷","男",80);
        Employee nn = new Employee("奶奶","女",78);
        Employee wo = new Employee("我","男",25);
        Employee lp = new Employee("老婆","女",25);
        yy.add(bb);
        nn.add(mm);
        bb.add(wo);
        mm.add(lp);
        zz.add(yy);
        for (Employee headEmployee : zz.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }

    }
}
