package com.mreapps.myweek.controller;

import com.mreapps.myweek.entity.Person;
import com.mreapps.myweek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping(value = "persons")
public class PersonController
{
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Person> list(Pageable pageable)
    {
        return personRepository.findAll(pageable);
    }

    @RequestMapping(value = "{personId}", method = RequestMethod.GET)
    public Person get(@PathVariable long personId)
    {
        return personRepository.findOne(personId);
    }
}
