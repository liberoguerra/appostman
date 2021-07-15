package com.finconsgroup.appostman.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finconsgroup.appostman.model.User;
import com.finconsgroup.appostman.model.UserAddress;
import com.finconsgroup.appostman.repository.UserAddressRepository;
import com.finconsgroup.appostman.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserAddressRepository addressRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Returns a list of all products
	 * 
	 * @return
	 */
	public List<User> list() {
		List<User> userList = repository.findAll();
//		for(User u : userList) {
//			for(UserAddress a : u.getUserAddressList()) {
//				a.getId();
//			}
//		}
		return userList;
	}

	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}


	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public User findById(Long id) throws Exception {
		Optional<User> optionalUser = repository.findById(id);
		if (optionalUser.isEmpty()) {
			throw new Exception("User not found");
		}

		return optionalUser.get();
	}
	
	/**
	 * create a new product
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackOn = Exception.class, value = TxType.REQUIRED)
	public User createUser(User user) {
		logger.info("start user save");
		
		List<UserAddress>  userAddressesList = user.getUserAddressList();
		user.setUserAddressList(null);
		User userSaved = repository.save(user);
		
		//save user's address
		for(UserAddress address : userAddressesList) {
			address.setUser(userSaved);
			addressRepository.save(address);
		}
		
		return userSaved;
	}
	
	@Transactional(rollbackOn = Exception.class, value = TxType.REQUIRED)
	public void delete(Long id) throws Exception {
		logger.info("start user delete");
		repository.delete(findById(id));
	}
	
	public List<User> findByAddress(String address){
		return repository.findByAddress(address);
	}

	

}
