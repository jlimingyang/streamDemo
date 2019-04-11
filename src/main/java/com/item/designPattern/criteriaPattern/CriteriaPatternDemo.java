package com.item.designPattern.criteriaPattern;

import java.util.ArrayList;
import java.util.List;

public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("张三","男", "单身"));
        persons.add(new Person("李四","男", "已婚"));
        persons.add(new Person("刘诗诗","女", "已婚"));
        persons.add(new Person("冯提莫","女", "单身"));
        persons.add(new Person("卢本伟","男", "单身"));
        persons.add(new Person("宋宪坤","男", "单身"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("男性: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("女性: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("单身男士: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("单身或者女性: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons){
        for (Person person : persons) {
            System.out.println("人物 : [ 姓名 : " + person.getName()
                    +", 性别 : " + person.getGender()
                    +", 婚姻状况 : " + person.getMaritalStatus()
                    +" ]");
        }
    }
}
