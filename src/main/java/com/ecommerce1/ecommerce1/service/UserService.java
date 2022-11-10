package com.ecommerce1.ecommerce1.service;

import com.ecommerce1.ecommerce1.dto.UserDto;
import com.ecommerce1.ecommerce1.model.User;
import com.ecommerce1.ecommerce1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Base64;

@Service
public class UserService {
@Autowired
UserRepo userRepo;

    public void createUser(@Valid User user1) {


        String encryptedEmail = user1.getEmail();
        byte[] encodedMail = Base64.getEncoder().encode(encryptedEmail.getBytes());
        String encodedEmail = new String(encodedMail);

        String encryptedPassword = user1.getPassword();
        byte[] encodedPass = Base64.getEncoder().encode(encryptedPassword.getBytes());
        String encodedPassword = new String(encodedPass);

        User user = new User();
        user.setFirstName(user1.getFirstName());
        user.setLastName(user1.getLastName());
        user.setEmail(encodedEmail);
        user.setPassword(encodedPassword);
        userRepo.save(user);

    }

    public void userLogin(UserDto userDto) {
        User existingUser=new User();

        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());
    }
}


