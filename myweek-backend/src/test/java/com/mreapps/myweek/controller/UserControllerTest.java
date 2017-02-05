package com.mreapps.myweek.controller;

import com.mreapps.myweek.MyweekApplication;
import com.mreapps.myweek.entity.User;
import com.mreapps.myweek.enums.Gender;
import com.mreapps.myweek.model.user.NewUser;
import com.mreapps.myweek.repository.UserRepository;
import com.mreapps.myweek.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyweekApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractControllerTest
{
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @Before
    public void setup() throws Exception
    {
        super.setup();

        given(userRepository.findOne(1L)).willReturn(createUser(1L, "messi@barcelona.es"));
        given(userRepository.findAll(any(Pageable.class))).willReturn(new PageImpl<>(Arrays.asList(
                createUser(1L, "messi@barcelona.es"),
                createUser(2L, "ronaldo@realmadrid.es")
        )));
        given(userService.createNewUser(any())).willReturn(createUser(3L, "neymar@barcelona.es"));
    }

    @Test
    public void userFound() throws Exception
    {
        mockMvc.perform(get("/users/1/")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("emailAddress", is("messi@barcelona.es")));
    }

    @Test
    public void userNotFound() throws Exception
    {
        mockMvc.perform(get("/users/100/")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void listUsers() throws Exception
    {
        mockMvc.perform(get("/users")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void createNewUser() throws Exception
    {
        NewUser newUser = new NewUser();
        newUser.setEmailAddress("messi@barcelona.es");
        newUser.setConfirmEmailAddress("messi@barcelona.es");
        newUser.setPassword("test123");
        newUser.setConfirmPassword("test123");
        newUser.setFirstname("Lionel");
        newUser.setMiddlename(null);
        newUser.setLastname("Messi");
        newUser.setGender(Gender.MALE);
        newUser.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1987-06-24"));

        mockMvc.perform(post("/users/create")
                .content(json(newUser))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(3)));
    }

    @Test
    public void createInvalidUser() throws Exception
    {
        NewUser newUser = new NewUser();
        newUser.setEmailAddress("messi@barcelona.es");
        newUser.setConfirmEmailAddress("messi@barcelona.ess");
        newUser.setPassword("test123");
        newUser.setConfirmPassword("test123");
        newUser.setFirstname("Lionel");
        newUser.setMiddlename(null);
        newUser.setLastname("Messi");
        newUser.setGender(Gender.MALE);
        newUser.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1987-06-24"));

        mockMvc.perform(post("/users/create")
                .content(json(newUser))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    private User createUser(long id, String emailAddress)
    {
        User user = new User();
        user.setId(id);
        user.setEmailAddress(emailAddress);

        return user;
    }
}