package com.sabal.spring5webapp.service.impl;

import com.sabal.spring5webapp.io.entity.UserEntity;
import com.sabal.spring5webapp.io.repositories.PasswordResetTokenRepository;
import com.sabal.spring5webapp.io.repositories.UserRepository;
import com.sabal.spring5webapp.security.SecurityConstants;
import com.sabal.spring5webapp.shared.Utils;
import com.sabal.spring5webapp.shared.dto.AddressDto;
import com.sabal.spring5webapp.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    SecurityConstants securityConstants;

    String userId = "gasghoho;ph";
    String password = "dlashaoliu";
    String emailVerificationToken = "hadusfhaidshfuads";

    UserEntity userEntity;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("Alex");
        userEntity.setUserId(userId);
        userEntity.setEmail("test@test.com");
        userEntity.setEmailVerificationToken(emailVerificationToken);
        userEntity.setEncryptedPassword(password);
    }

    @Test
    void testGetUser() {
        when(userRepository.findByEmail((anyString()))).thenReturn(userEntity);

        UserDto dto = userService.getUser("test@test.com");

        assertNotNull(dto);
        assertEquals("Alex", dto.getFirstName());
    }

    @Test
    final void testGetUser_UsernameNotFoundException() {
        when(userRepository.findByEmail((anyString()))).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userService.getUser("test@test.com");
                }
                );
    }

    @Test
    final void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateAddressId(anyInt())).thenReturn("gfdslkjgh");
        when(utils.generateUserId(anyInt())).thenReturn(userId);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(password);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        //when(Utils.generateEmailVerificationToken(anyString())).thenReturn(emailVerificationToken);

        AddressDto addressDto = new AddressDto();
        addressDto.setType("shipping");
        addressDto.setCity("Vancouver");
        addressDto.setCountry("Canada");
        addressDto.setPostalCode("ABC123");
        addressDto.setStreetName("123 Street name");

        AddressDto billingAddressDto = new AddressDto();
        billingAddressDto.setType("billing");
        billingAddressDto.setCity("Vancouver");
        billingAddressDto.setCountry("Canada");
        billingAddressDto.setPostalCode("ABC123");
        billingAddressDto.setStreetName("123 Street name");

        List<AddressDto> addresses = new ArrayList<>();
        addresses.add(addressDto);
        addresses.add(billingAddressDto);

        UserDto userDto = new UserDto();
        userDto.setFirstName("Aliaksandr");
        userDto.setLastName("Sabalevich");
        userDto.setPassword("12345678");
        userDto.setEmail("test@test.com");

        userDto.setAddresses(addresses);

        UserDto storedUserDetails = userService.createUser(userDto);
        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
        assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
    }
}