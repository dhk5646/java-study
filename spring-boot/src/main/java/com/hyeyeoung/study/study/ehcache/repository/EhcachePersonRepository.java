package com.hyeyeoung.study.study.ehcache.repository;

import com.hyeyeoung.study.study.ehcache.database.Persons;
import com.hyeyeoung.study.study.ehcache.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class EhcachePersonRepository {

    public Person selectPerson(String id) {
        return Persons.getPerson(id);
    }

    public void deletePerson(String id) {
        Persons.deletePerson(id);
    }
}
