package com.lalitha.busReservationBackend.controller;

import com.lalitha.busReservationBackend.dto.PassengerDto;
import com.lalitha.busReservationBackend.dto.PasswordRequestUtil;
import com.lalitha.busReservationBackend.entity.User;
import com.lalitha.busReservationBackend.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin

public class UserSecurityController {
    @Autowired
    UserSecurityService userSecurityService;
    @GetMapping("/{Name}")// gets the user by name and updates the password
    public ResponseEntity<User> getPassengerByName(@PathVariable("Name")String name)
    {
        User getUser=userSecurityService.getUser(name);
        return new ResponseEntity<User>(getUser, HttpStatus.OK);
    }
    @PostMapping
    public String passwordChange(@RequestBody PasswordRequestUtil passwordRequestUtil){
        User userSecurity=userSecurityService.getUser(passwordRequestUtil.getUsername());
        if(!userSecurityService.oldPasswordIsValid(userSecurity,passwordRequestUtil.getOldPassword())){
            return "Incorrect Old Password";
        }
        else{
            userSecurityService.changePassword(userSecurity,passwordRequestUtil.getNewPassword());
            return "Password changed Successfully";
        }
    }


}
