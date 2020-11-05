package com.sabal.spring5webapp.service;

import com.sabal.spring5webapp.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto user);
    UserDto updateUser(String userId, UserDto user);
    void deleteUser(String userId);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
    List<UserDto> getUsers(int page, int limit);
    boolean verifyEmailToken(String token);
}
