package com.titan.probe.services;

import com.titan.probe.models.User;

public interface UserService {
    public User findUserByUsername(String username);
    public void saveUser(User user);
}
