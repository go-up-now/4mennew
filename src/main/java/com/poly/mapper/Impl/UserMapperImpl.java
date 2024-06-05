package com.poly.mapper.Impl;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.User;
import com.poly.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(UserCreationRequest request) {
        if(request == null)
            return null;
        else{
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setAddress(request.getAddress());
            user.setPhone(request.getPhone());
            user.setBirthday(request.getBirthday());
            user.setCreatedAt(LocalDateTime.now());
            user.setImg(request.getImg());
            user.setFullname(request.getFullname());
            user.setState(request.getState());
            user.setGender(request.getGender());
            return user;
        }
    }

    @Override
    public User toUserDTO(User user) {
        if(user == null)
            return null;
        else {
            UserCreationRequest request = new UserCreationRequest();
            request.setUsername(user.getUsername());
            request.setPassword(user.getPassword());
            request.setEmail(user.getEmail());
            request.setAddress(user.getAddress());
            request.setPhone(user.getPhone());
            request.setBirthday(user.getBirthday());
            request.setCreatedAt(user.getCreatedAt());
            request.setImg(user.getImg());
            request.setFullname(user.getFullname());
            request.setState(user.getState());
            request.setGender(user.getGender());
        }

        return null;
    }

    @Override
    public void updateUser(User user, UserUpdationRequest request) {
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setBirthday(request.getBirthday());
        user.setUpdatedAt(LocalDateTime.now());
        user.setImg(request.getImg());
        user.setFullname(request.getFullname());
        user.setState(request.getState());
        user.setGender(request.getGender());
    }

}
