package com.sabal.spring5webapp.io.repositories;

import com.sabal.spring5webapp.io.entity.PasswordResetTokenEntity;
import com.sabal.spring5webapp.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    UserEntity findUserByEmailVerificationToken(String token);

    @Query(value = "select * from Users u where u.Email_Verification_status = 'true'",
            countQuery = "select count(*) from Users u where u.Email_Verification_status = 'true'",
            nativeQuery = true)
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);

    @Query(value = "select * from users u where u.first_name = ?1 and u.last_name = ?2", nativeQuery = true)
    List<UserEntity> findUserByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "select * from users u where u.last_name = :lastName", nativeQuery = true)
    List<UserEntity> findUserByLastName(@Param("lastName") String lastName);

    @Query(value = "select * from users u where u.first_name like %:keyword% or u.last_name like %:keyword%", nativeQuery = true)
    List<UserEntity> findUserByKeyword(@Param("keyword") String keyword);

    @Query(value = "select u.first_name, u.last_name from users u where u.first_name like %:keyword% or u.last_name like %:keyword%", nativeQuery = true)
    List<Object[]> findUserFirstNameAndLastNameByKeyword(@Param("keyword") String keyword);

    /*@Transactional
    @Modifying
    @Query(value = "update users u set u.email_verification_status = :emailVerificationStatus where u.user_id = :userId", nativeQuery = true)
    List<Object[]> updateUserEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);*/

    @Query("select user from UserEntity user where user.userId = :userId")
    UserEntity findUserEntityByUserId(@Param("userId") String userId);

    @Query("select user.firstName, user.lastName from UserEntity user where user.userId = :userId")
    List<Object[]> getUserEntityFullNameByUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity user set user.emailVerificationStatus = :emailVerificationStatus where user.userId = :userId")
    List<Object[]> updateUserEntityEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus, @Param("userId") String userId);



}
