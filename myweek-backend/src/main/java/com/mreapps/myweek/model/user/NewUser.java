package com.mreapps.myweek.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mreapps.myweek.enums.Gender;
import com.mreapps.myweek.validator.FieldMatch;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 *
 */
@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "emailAddress", second = "confirmEmailAddress", message = "The email fields must match")
})
public class NewUser
{
    @Email
    @NotEmpty
    private String emailAddress;
    private String confirmEmailAddress;
    @NotEmpty
    private String password;
    private String confirmPassword;
    @NotEmpty
    private String firstname;
    private String middlename;
    @NotEmpty
    private String lastname;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "CET")
    private Date birthday;
}
