package com.sabal.spring5webapp.io.repository;

import com.sabal.spring5webapp.io.entity.AddressEntity;
import com.sabal.spring5webapp.io.entity.UserEntity;
import com.sabal.spring5webapp.io.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    String userId = "1hj6md90";

    static boolean recordsCreated = false;

    @BeforeEach
    void setUp() throws Exception {
        if (!recordsCreated) {
            createRecords();
        }
    }

    @Test
    final void testGetVerifiedUsers() {
        Pageable pageableRequest = PageRequest.of(0, 1);
        Page<UserEntity> page = userRepository.findAllUsersWithConfirmedEmailAddress(pageableRequest);
        assertNotNull(page);

        List<UserEntity> userEntities = page.getContent();
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
    }

    @Test
    final void findUserByFirstNameAndLastName() {
        String firstName = "Ivan";
        String lastName = "Sabalevich";
        List<UserEntity> users = userRepository.findUserByFirstNameAndLastName(firstName, lastName);
        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getFirstName().equals(firstName));
    }

    @Test
    final void findUserByLastName() {
        String lastName = "Sabalevich";
        List<UserEntity> users = userRepository.findUserByLastName(lastName);
        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getLastName().equals(lastName));
    }

    @Test
    final void findUserByKeyword() {
        String keyword = "van";
        List<UserEntity> users = userRepository.findUserByKeyword(keyword);
        assertNotNull(users);
        assertTrue(users.size() == 2);

        UserEntity user = users.get(0);
        assertTrue(user.getLastName().contains(keyword) || user.getFirstName().contains(keyword));
    }

    @Test
    final void findUserFirstNameAndLastNameByKeyword() {
        String keyword = "van";
        List<Object[]> users = userRepository.findUserFirstNameAndLastNameByKeyword(keyword);

        Object[] user = users.get(0);
        String userFirstName = String.valueOf(user[0]);
        String userLastName = String.valueOf(user[1]);

        assertNotNull(userFirstName);
        assertNotNull(userLastName);

        assertTrue(user.length == 2);

        System.out.println("userLastName = " + userLastName);
        System.out.println("userFirstName = " + userFirstName);
    }

    /*@Test
    final void testUpdateUserEmailVerificationStatus() {
        boolean newEmailVerificationStatus = false;
        String userId = "1hj6md90";
        userRepository.updateUserEmailVerificationStatus(newEmailVerificationStatus, userId);
        UserEntity storedDetails = userRepository.findByUserId("1hj6md90");
        boolean storedEmailVerificationStatus = storedDetails.getEmailVerificationStatus();
        assertTrue(storedEmailVerificationStatus == newEmailVerificationStatus);
    }*/

    @Test
    final void testFindUserEntityByUserId() {
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        assertNotNull(userEntity);
        assertTrue(userEntity.getUserId().equals(userId));
    }

    @Test
    final void testGetUserEntityFullNameByUserId() {
        List<Object[]> objects = userRepository.getUserEntityFullNameByUserId(userId);

        Object[] user = objects.get(0);
        String userFirstName = String.valueOf(user[0]);
        String userLastName = String.valueOf(user[1]);

        assertNotNull(userFirstName);
        assertNotNull(userLastName);

        assertTrue(user.length == 2);

        System.out.println("userLastName = " + userLastName);
        System.out.println("userFirstName = " + userFirstName);
    }

    /*@Test
    final void testUpdateUserEntityEmailVerificationStatus() {
        boolean newEmailVerificationStatus = false;
        userRepository.updateUserEntityEmailVerificationStatus(newEmailVerificationStatus, userId);
        UserEntity storedDetails = userRepository.findByUserId("1hj6md90");
        boolean storedEmailVerificationStatus = storedDetails.getEmailVerificationStatus();
        assertTrue(storedEmailVerificationStatus == newEmailVerificationStatus);
    }*/

    private void createRecords() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Ivan");
        userEntity.setLastName("Sabalevich");
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword("xxx");
        userEntity.setEmail("ivan@sabalevich.com");
        userEntity.setEmailVerificationStatus(true);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setType("shipping");
        addressEntity.setAddressId("df74mf04nf");
        addressEntity.setCity("Vancouver");
        addressEntity.setCountry("Canada");
        addressEntity.setPostalCode("ABCCDA");
        addressEntity.setStreetName("123 Street Address");

        List<AddressEntity> addresses = new ArrayList<>();
        addresses.add(addressEntity);

        userEntity.setAddresses(addresses);

        userRepository.save(userEntity);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setFirstName("Ivan");
        userEntity2.setLastName("Sabalevich");
        userEntity2.setUserId("1hj6md91");
        userEntity2.setEncryptedPassword("xxx");
        userEntity2.setEmail("ivan@sabalevich.com");
        userEntity2.setEmailVerificationStatus(true);

        AddressEntity addressEntity2 = new AddressEntity();
        addressEntity2.setType("shipping");
        addressEntity2.setAddressId("df74mf04ng");
        addressEntity2.setCity("Vancouver");
        addressEntity2.setCountry("Canada");
        addressEntity2.setPostalCode("ABCCDA");
        addressEntity2.setStreetName("123 Street Address");

        List<AddressEntity> addresses2 = new ArrayList<>();
        addresses2.add(addressEntity2);

        userEntity2.setAddresses(addresses2);

        userRepository.save(userEntity2);
        recordsCreated = true;
    }
}
