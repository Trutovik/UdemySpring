package com.sabal.spring5webapp.io.repositories;

import com.sabal.spring5webapp.io.entity.AddressEntity;
import com.sabal.spring5webapp.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

    List<AddressEntity> findAllByUserDetails(UserEntity entity);
    AddressEntity findByAddressId(String addressId);
}
