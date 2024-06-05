package com.poly.service;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(int id);
    List<User> getAllUsers();
    User createUser(UserCreationRequest request);
    User updateUser(UserUpdationRequest request, int id);
    void deleteUser(int id);
    Boolean checkUsername(String username);
}
