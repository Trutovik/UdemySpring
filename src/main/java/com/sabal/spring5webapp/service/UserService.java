package com.sabal.spring5webapp.service;

import com.sabal.spring5webapp.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
}
