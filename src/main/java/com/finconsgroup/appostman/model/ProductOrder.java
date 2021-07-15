package com.finconsgroup.appostman.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Version;

import com.finconsgroup.appostman.model.key.ProductOrderKey;


@Entity
@Table(name = "PRODUCT_ORDER")
public class ProductOrder {

	@EmbeddedId
	ProductOrderKey id;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "price_id")
	ProductPrice productPrice;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "user_id")
	User user;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "product_id")
	Product product;

	@Column(name = "order_datetime")
	private LocalDateTime orderDateTime;

	@Version
	@Column(name = "version")
	private Long version;

	public ProductOrderKey getId() {
		return id;
	}

	public void setId(ProductOrderKey id) {
		this.id = id;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	

	

}
