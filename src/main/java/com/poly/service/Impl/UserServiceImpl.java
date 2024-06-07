package com.poly.service.Impl;

import com.poly.dto.request.UserCreationRequest;
import com.poly.dto.request.UserUpdationRequest;
import com.poly.entity.Role;
import com.poly.entity.User;
import com.poly.mapper.UserMapper;
import com.poly.repository.RoleRepository;
import com.poly.repository.UserRepository;
import com.poly.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService {

    @Autowired
     private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("username existed");

        Role role = roleRepository.findById(request.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userMapper.toUser(request);
        user.setRole(role);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdationRequest request, int id) {
        User user = this.getUser(id);
        Role role = roleRepository.findById(request.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));

        userMapper.updateUser(user, request);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean checkUsername(String username) {
        return userRepository.existsByUsername(username);
    }

//    @Override
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
}
