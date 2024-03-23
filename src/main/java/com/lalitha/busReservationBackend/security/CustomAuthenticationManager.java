package com.lalitha.busReservationBackend.security;



import com.lalitha.busReservationBackend.entity.User;
import com.lalitha.busReservationBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       String userName= authentication.getName();
        String password=authentication.getCredentials().toString();
        User user= userRepository.findByUsernameOrEmail(userName,password)
                .orElseThrow(()->new UsernameNotFoundException("User not exist in Db"));
        if(isValidUser(userName,password))
        {return
                new UsernamePasswordAuthenticationToken(userName,password);}
        else {
            throw new AuthenticationException("Invalid Credentials") {
            };
        }

    }
    private Boolean isValidUser(String userName,String password)
    {
        User user= userRepository.findByUsernameOrEmail(userName,password)
                .orElseThrow(()->new UsernameNotFoundException("User not exist in Db"));
        return  passwordEncoder.matches(password,user.getPassword());
    }
}
