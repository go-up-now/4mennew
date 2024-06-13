package com.poly.service.Impl;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.dto.respone.UserResponse;
import com.poly.entity.Order;
import com.poly.entity.Role;
import com.poly.entity.User;
import com.poly.mapper.UserMapper;
import com.poly.repository.OrderDetailRepository;
import com.poly.repository.OrderRepository;
import com.poly.repository.RoleRepository;
import com.poly.repository.UserRepository;
import com.poly.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService {
    @NonFinal
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("username existed");

        User user = userMapper.toUser(request);
        Set<Role> roles = new HashSet<>();
        Role role = new Role();

        role.setName(com.poly.enums.Role.USER.name());
        roles.add(role);

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(UserUpdationRequest request, int id) {
        User user = this.getUser(id);
//        Role role = new Role();
        var password = user.getPassword();
        var roles = roleRepository.findAllById(request.getRole());

        userMapper.updateUser(user, request);

//        if(roles.isEmpty()){
//            role.setName(com.poly.enums.Role.USER.name());
//            roles.add(role);
//        }

        user.setRoles(new HashSet<>(roles));

        if(request.getPassword().isEmpty()){
            user.setPassword(password);
        }
        else
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
//        User user = this.getUser(id);
//        Order order = orderRepository.findByUser(user)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//            orderDetailRepository.deleteByOrder(order);
//            orderRepository.deleteByUser(user);
            userRepository.deleteById(id);
    }

    @Override
    public Boolean checkUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public UserResponse getUserResponse(int id) {
        return userMapper.toUserResponse(this.getUser(id));
    }

    @Override
    public UserResponse getMyInformation() {
        var authenticate = SecurityContextHolder.getContext().getAuthentication();
        var username = authenticate.getName();

        return userMapper.toUserResponse(this.getUserByUsername(username));
    }

    @Override
    public UserResponse updateMyInformation(UserUpdationRequest request){
        var authenticate = SecurityContextHolder.getContext().getAuthentication();
        var username = authenticate.getName();

        User user = this.getUserByUsername(username);

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("ErrorCode.USER_NOT_FOUND"));
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userMapper.toUserResponse(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public void updateUserPassword(String username, String password) {
        User user = this.getUserByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
