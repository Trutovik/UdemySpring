package com.sabal.spring5webapp.shared;

import jdk.jshell.execution.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilsTest {

    @Autowired
    Utils utils;

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    final void testGenerateUserId() {
        String userid = utils.generateUserId(30);
        String userid1 = utils.generateUserId(30);
        assertNotNull(userid);
        assertTrue(userid.length() == 30);
        assertNotNull(userid1);
        assertTrue(userid1.length() == 30);
        assertTrue(!userid.equalsIgnoreCase(userid1));
    }

    @Test
    final void testHasTokenExpired() {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjYXNiYWhAbWFpbC5ydSIsImV4cCI6MTYwNjM2NTMyOX0.KnFKQax3nkqD0o3v-3uguL6U6yo12UGoNxnX4wJgUIyYNFXOyoyTZiCNy6zM7kImjCur13YFRrS5RYOZbb7r6Q";
        boolean hasTokenExpired = Utils.hasTokenExpired(expiredToken);
        assertTrue(hasTokenExpired);
    }

    @Test
    final void testHasTokenNotExpired() {
        String token = Utils.generateEmailVerificationToken("34ht40v9r3etvuti");
        assertNotNull(token);
        boolean hasTokenExpired = Utils.hasTokenExpired(token);
        assertFalse(hasTokenExpired);
    }


}
