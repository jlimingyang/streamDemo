package com.item.designPattern.criteriaPattern;

import java.util.ArrayList;
import java.util.List;

public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("单身")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}
