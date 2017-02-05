package com.mreapps.myweek.service;

import com.mreapps.myweek.entity.Person;
import com.mreapps.myweek.entity.User;
import com.mreapps.myweek.model.user.NewUser;
import com.mreapps.myweek.repository.PersonRepository;
import com.mreapps.myweek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService
{
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PersonRepository personRepository, UserRepository userRepository)
    {
        this.personRepository = personRepository;
        this.userRepository = userRepository;
    }

    public User createNewUser(NewUser newUser)
    {
        Person person = new Person();
        person.setFirstname(newUser.getFirstname());
        person.setMiddlename(newUser.getMiddlename());
        person.setLastname(newUser.getLastname());
        person.setGender(newUser.getGender());
        person.setBirthday(newUser.getBirthday());

        person = personRepository.save(person);

        User user = new User();
        user.setEmailAddress(newUser.getEmailAddress());
        // TODO encrypt password
        user.setEncryptedPassword(newUser.getPassword());
        user.setPerson(person);

        return userRepository.save(user);
    }
}
