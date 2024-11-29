package com.notes.thinknotesbackend.service;

import com.notes.thinknotesbackend.dto.UserDTO;
import com.notes.thinknotesbackend.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void updateUserRole(Long id,String roleName);
    UserDTO getUserById(Long id);
}
