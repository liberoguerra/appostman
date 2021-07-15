package com.finconsgroup.appostman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//@Query(value = "SELECT distinct(u) FROM User u join u.userAddressList a WHERE a.id != null")
//	"SELECT * FROM USER as U INNER JOIN USER_ADDRESS as A ON A.USER_DI ? U.ID"
//	"WHERE A.ADDRESS = ?";
	
	@Query(value = "SELECT distinct(u) FROM User u join u.userAddressList a WHERE a.nation = ?1")
	List<User> findByAddress(String address);
}
