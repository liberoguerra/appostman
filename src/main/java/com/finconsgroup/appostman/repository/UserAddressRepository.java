package com.finconsgroup.appostman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.UserAddress;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}
