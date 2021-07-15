package com.finconsgroup.appostman.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "startOffer")
	private LocalDate startOffer;

	@Column(name = "endOffer")
	private LocalDate endOffer;

	@Version
	@Column(name = "version")
	private Long version;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
	
	@OneToMany(mappedBy = "productPrice")
	private List<ProductOrder> productOrderList;
	

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the startOffer
	 */
	public LocalDate getStartOffer() {
		return startOffer;
	}

	/**
	 * @param startOffer the startOffer to set
	 */
	public void setStartOffer(LocalDate startOffer) {
		this.startOffer = startOffer;
	}

	/**
	 * @return the endOffer
	 */
	public LocalDate getEndOffer() {
		return endOffer;
	}

	/**
	 * @param endOffer the endOffer to set
	 */
	public void setEndOffer(LocalDate endOffer) {
		this.endOffer = endOffer;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

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
