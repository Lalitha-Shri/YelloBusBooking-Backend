package com.lalitha.busReservationBackend.service;


import com.lalitha.busReservationBackend.dto.JwtAuthResponse;
import com.lalitha.busReservationBackend.dto.LoginDto;
import com.lalitha.busReservationBackend.dto.RegisterDto;


import com.lalitha.busReservationBackend.entity.Role;
import com.lalitha.busReservationBackend.entity.User;
import com.lalitha.busReservationBackend.exception.ToDoApiException;
import com.lalitha.busReservationBackend.repository.RoleRepository;
import com.lalitha.busReservationBackend.repository.UserRepository;
import com.lalitha.busReservationBackend.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
        throw new ToDoApiException(HttpStatus.BAD_REQUEST,"UserName already exists");
    }
    if(userRepository.existsByEmail(registerDto.getEmail())){
        throw new ToDoApiException(HttpStatus.BAD_REQUEST,"Email already exists");
    }
        User user=new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Set<Role> roles=new HashSet<>();
    Role userRole=roleRepository.findByName("ROLE_USER");
    roles.add(userRole);
    user.setRoles(roles);
    userRepository.save(user);
    return "User Registered successfully!!";

}

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtUtil.generate( loginDto.getUsername());
        Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsername(),loginDto.getUsername());
        String role=null;

        if(userOptional.isPresent()){
            User loggedUser=userOptional.get();
            //Optional<Role> optionalRole=loggedUser.getRoles().stream().findFirst();
            List<Role> roleList=loggedUser.getRoles().stream()
                    .filter(_role->_role.getName().equalsIgnoreCase("ROLE_ADMIN"))
                    .collect(Collectors.toList());
            if(roleList.size()>0){
                Role dbrole=roleList.get(0);
                role=dbrole.getName();

            }
            else{
                Role userRole=loggedUser.getRoles().stream().findFirst().get();
                role=userRole.getName();
            }
        }
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);
        return jwtAuthResponse;
    }
}
