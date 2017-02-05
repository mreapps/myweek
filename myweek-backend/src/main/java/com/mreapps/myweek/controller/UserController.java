package com.mreapps.myweek.controller;

import com.mreapps.myweek.entity.User;
import com.mreapps.myweek.exception.BadRequestException;
import com.mreapps.myweek.exception.EntityNotFoundException;
import com.mreapps.myweek.model.user.NewUser;
import com.mreapps.myweek.repository.UserRepository;
import com.mreapps.myweek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("users")
public class UserController
{
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService)
    {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> list(Pageable pageable)
    {
        return userRepository.findAll(pageable);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User get(@PathVariable long userId)
    {
        User user = userRepository.findOne(userId);

        if (user == null) throw new EntityNotFoundException(String.format("User id %s not found", userId));

        return user;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public User create(@RequestBody @Valid NewUser newUser, BindingResult result)
    {
        if (result.hasErrors()) throw new BadRequestException("Illegal body content");

        return userService.createNewUser(newUser);
    }

    // TODO change password
    // TODO change email address
    // TODO delete user
}
