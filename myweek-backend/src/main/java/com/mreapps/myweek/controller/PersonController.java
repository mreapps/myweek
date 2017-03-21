package com.mreapps.myweek.controller;

import com.mreapps.myweek.entity.Person;
import com.mreapps.myweek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping(value = "persons")
public class PersonController extends AbstractCrudController<Person>
{
    @Autowired
    public PersonController(PersonRepository personRepository)
    {
        super(Person.class.getName(), personRepository);
    }


}
