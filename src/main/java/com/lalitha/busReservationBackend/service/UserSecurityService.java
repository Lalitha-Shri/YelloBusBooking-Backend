package com.lalitha.busReservationBackend.service;

import com.lalitha.busReservationBackend.entity.User;

public interface UserSecurityService {
    User getUser(String name);

    boolean oldPasswordIsValid(User userSecurity,String oldPassword);
    void changePassword(User userSecurity, String newPassword);
}
