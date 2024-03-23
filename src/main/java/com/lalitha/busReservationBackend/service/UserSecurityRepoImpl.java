package com.lalitha.busReservationBackend.service;

import com.lalitha.busReservationBackend.entity.User;
import com.lalitha.busReservationBackend.exception.ResourceNotFoundNameException;
import com.lalitha.busReservationBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityRepoImpl implements UserSecurityService{
    @Autowired
    UserRepository userSecurityRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String name) {
        User userSecurity=userSecurityRepo.findByUsername(name)
                .orElseThrow(()->new ResourceNotFoundNameException("User","name",name));;
        return userSecurity;
    }
    public boolean oldPasswordIsValid(User userSecurity,String oldPassword){
        return passwordEncoder.matches(oldPassword,userSecurity.getPassword());
    }

    @Override
    public void changePassword(User userSecurity, String newPassword) {
        userSecurity.setPassword(passwordEncoder.encode(newPassword));
        userSecurityRepo.save(userSecurity);
    }
}
