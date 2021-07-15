package com.finconsgroup.appostman.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finconsgroup.appostman.model.User;
import com.finconsgroup.appostman.model.UserAddress;
import com.finconsgroup.appostman.model.UserAddressDTO;
import com.finconsgroup.appostman.model.UserDTO;
import com.finconsgroup.appostman.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserApi {

	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Object> userList() {
		logger.info("start userList");
		List<User> userList = userService.list();
		
		List<UserDTO> userDtoList = new ArrayList<UserDTO>();
		
//		for (User user : userList) {
//			
//			userService.getEntityManager().detach(user);
//			if (Hibernate.isInitialized(user.getUserAddressList())) {
//				
//			}
//		}
		
		
//		
		for (User user : userList) {
			userService.getEntityManager().detach(user);
			
			UserDTO userDto = new UserDTO();
			BeanUtils.copyProperties(user, userDto);
			
			
			if (Hibernate.isInitialized(user.getUserAddressList())) {

				for (UserAddress userAdd : user.getUserAddressList()) {
					userService.getEntityManager().detach(userAdd);
					UserAddressDTO addDTO = new UserAddressDTO();
					BeanUtils.copyProperties(userAdd, addDTO);
					userDto.getUserAddressList().add(addDTO);
				}
			}
			userDtoList.add(userDto);
		}
		return new ResponseEntity<Object>(userDtoList, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	ResponseEntity<Object> deleteUser(@RequestParam(name = "id") Long id) {
		logger.info("start deleteUser");
		try {
			userService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Object> addUser(@RequestBody User user) {
		try {
			return new ResponseEntity<Object>(userService.createUser(user), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	ResponseEntity<Object> userListByAddress(@RequestParam(name = "address") String address) {
		logger.info("start userListByAddress");
		List<User> userList = userService.findByAddress(address);
		List<UserDTO> userDtoList = new ArrayList<UserDTO>();
		for (User user : userList) {
			userService.getEntityManager().detach(user);
			UserDTO userDto = new UserDTO();
			BeanUtils.copyProperties(user, userDto);
			for (UserAddress userAdd : user.getUserAddressList()) {
				userService.getEntityManager().detach(userAdd);
				UserAddressDTO addDTO = new UserAddressDTO();
				BeanUtils.copyProperties(userAdd, addDTO);
				userDto.getUserAddressList().add(addDTO);
			}
			userDtoList.add(userDto);
		}
		return new ResponseEntity<Object>(userDtoList, HttpStatus.OK);
	}

}
