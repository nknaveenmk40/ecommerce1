package com.ecommerce1.ecommerce1.controller;

import com.ecommerce1.ecommerce1.common.ApiResponse;
import com.ecommerce1.ecommerce1.dto.UserDto;
import com.ecommerce1.ecommerce1.model.User;
import com.ecommerce1.ecommerce1.repository.UserRepo;
import com.ecommerce1.ecommerce1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;
    //Sign up
@PostMapping("/signup")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user){
    Optional<User> optionalUser = userRepo.findByEmail(user.getEmail());
    if (optionalUser.isPresent()){
        return new ResponseEntity<>(new ApiResponse(false,"User already exist"), HttpStatus.BAD_REQUEST);
    }


    userService.createUser(user);
    return new ResponseEntity<>(new ApiResponse(true,"account created"),HttpStatus.CREATED);

}


    //Sign in

   @PostMapping("/signin")
    public ResponseEntity<ApiResponse> userLogin(@Valid @RequestBody UserDto userDto){
    Optional<User> optionalUser = userRepo.findByEmail(userDto.getEmail());
    Optional<User> optionalUser1 = userRepo.findByPassword(userDto.getPassword());
    if(optionalUser.isEmpty() || optionalUser1.isEmpty()){
        return new ResponseEntity<>(new ApiResponse(false,"User details not found"),HttpStatus.NOT_FOUND);
    }
    userService.userLogin(userDto);
    return new ResponseEntity<>(new ApiResponse(true,"User successfully logged in"),HttpStatus.ACCEPTED);

   }
}
