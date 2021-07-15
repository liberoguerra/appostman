package com.finconsgroup.appostman.model;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
	
	
    private Long id;
	
	private String firstName;
	
	private String lastName;
	
	
	private List<ProductOrder> productOrderList;
	
	private List<UserAddressDTO> userAddressList;

	/**
	 * @return the productOrderList
	 */
	public List<ProductOrder> getProductOrderList() {
		return productOrderList;
	}

	/**
	 * @param productOrderList the productOrderList to set
	 */
	public void setProductOrderList(List<ProductOrder> productOrderList) {
		this.productOrderList = productOrderList;
	}

	/**
	 * @return the userAddressList
	 */
	public List<UserAddressDTO> getUserAddressList() {
		if(userAddressList==null) {
			userAddressList = new ArrayList<UserAddressDTO>();
		}
		return userAddressList;
	}

	/**
	 * @param userAddressList the userAddressList to set
	 */
	public void setUserAddressList(List<UserAddressDTO> userAddressList) {
		this.userAddressList = userAddressList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	
	
}
