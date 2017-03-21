package com.mreapps.myweek.controller;

import com.mreapps.myweek.entity.User;
import com.mreapps.myweek.exception.BadRequestException;
import com.mreapps.myweek.model.user.NewUser;
import com.mreapps.myweek.repository.UserRepository;
import com.mreapps.myweek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends AbstractCrudController<User>
{
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService)
    {
        super(User.class.getName(), userRepository);
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public User create(@RequestBody @Valid NewUser newUser, BindingResult result)
    {
        if (result.hasErrors())
        {
            throw new BadRequestException("Illegal body content");
        }

        return userService.createNewUser(newUser);
    }
}
