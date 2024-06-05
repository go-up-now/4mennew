package com.poly.mapper;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.User;
import org.mapstruct.MappingTarget;

//@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    User toUserDTO(User user);
    void updateUser(@MappingTarget User user, UserUpdationRequest request);
}
