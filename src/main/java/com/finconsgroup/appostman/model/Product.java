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
@Table(name="PRODUCT")
public class Product {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Version
	@Column(name="version")
	private Long version;
	
	@OneToMany(mappedBy = "product")
	private List<ProductOrder> productOrderList;
	
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

	public List<ProductPrice> getProductPriceList() {
		return productPriceList;
	}

	public void setProductPriceList(List<ProductPrice> productPriceList) {
		this.productPriceList = productPriceList;
	}

	@OneToMany(mappedBy="product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ProductPrice> productPriceList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
