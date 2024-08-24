package com.hyeyeoung.study.study.ehcache.database;

import com.hyeyeoung.study.study.ehcache.entity.Person;

import java.util.ArrayList;
import java.util.List;


public class Persons {

    private static final List<Person> persons = new ArrayList<>();

    static {
        // 테스트 목적으로 데이터를 초기화 한다.
        persons.add(Person.create("aks", "악스"));
    }

    public static Person getPerson(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }

        return persons.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElseGet(Person::empty);
    }

    public static void deletePerson(String id) {
        persons.removeIf(person -> person.getId().equals(id));
    }
}
