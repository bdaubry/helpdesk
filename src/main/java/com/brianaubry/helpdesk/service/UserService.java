package com.brianaubry.helpdesk.service;

import com.brianaubry.helpdesk.model.Role;
import com.brianaubry.helpdesk.model.User;

public interface UserService {

    User findUserByEmail(String email);

    boolean findRoleByUser(String role, User user);

    boolean isAdmin(User user);

    boolean isUser(User user);

    void saveUser(User user);

}
