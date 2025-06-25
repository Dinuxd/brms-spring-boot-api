package com.api.brms.service;

import com.api.brms.dto.UserRequest;
import com.api.brms.dto.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> findAllUsers();
    UserResponse findUserById(Long id);
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
