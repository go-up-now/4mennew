package com.poly.service;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.dto.respone.UserResponse;
import com.poly.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUser(int id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(UserCreationRequest request);
    UserResponse updateUser(UserUpdationRequest request, int id);
    void deleteUser(int id);
    Boolean checkUsername(String username);
    UserResponse getUserResponse(int id);
    UserResponse getMyInformation();
    UserResponse updateMyInformation(UserUpdationRequest request);
    User getUserByUsername(String username);
    UserResponse getUserByEmail(String email);
    void updateUserPassword(String username, String password);
}
