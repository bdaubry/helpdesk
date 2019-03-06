package com.brianaubry.helpdesk.service;

import com.brianaubry.helpdesk.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}
