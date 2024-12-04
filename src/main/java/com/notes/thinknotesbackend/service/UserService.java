package com.notes.thinknotesbackend.service;

import com.notes.thinknotesbackend.dto.UserDTO;
import com.notes.thinknotesbackend.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    List<User> getAllUsers();
    void updateUserRole(Long id,String roleName);
    UserDTO getUserById(Long id);
    boolean existsByUserName(@NotBlank @Size(min = 3, max = 20) String username);
    boolean existsByEmail(@NotBlank @Size(max = 50) @Email String email);
    User findUserByuserName(String username);



}
