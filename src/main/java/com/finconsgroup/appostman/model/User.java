package com.finconsgroup.appostman.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="USER")
public class User {
	
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Version
	@Column(name="version")
	private Long version;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<ProductOrder> productOrderList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UserAddress> userAddressList;

//	/**
//	 * @return the productOrderList
//	 */
//	public List<ProductOrder> getProductOrderList() {
//		return productOrderList;
//	}
//
//	/**
//	 * @param productOrderList the productOrderList to set
//	 */
//	public void setProductOrderList(List<ProductOrder> productOrderList) {
//		this.productOrderList = productOrderList;
//	}

	/**
	 * @return the userAddressList
	 */
	public List<UserAddress> getUserAddressList() {
		return userAddressList;
	}

	/**
	 * @param userAddressList the userAddressList to set
	 */
	public void setUserAddressList(List<UserAddress> userAddressList) {
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
	
}
