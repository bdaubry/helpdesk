package com.brianaubry.helpdesk.service;

import com.brianaubry.helpdesk.model.User;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

}
