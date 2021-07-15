package com.finconsgroup.appostman.model.key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductOrderKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8999399601723918019L;

	/**
	 * 
	 */
	

	@Column(name = "product_id")
	Long productId;

	@Column(name = "user_id")
	Long userId;
	
	@Column(name = "price_id")
	Long priceId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(priceId, productId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrderKey other = (ProductOrderKey) obj;
		return Objects.equals(priceId, other.priceId) && Objects.equals(productId, other.productId)
				&& Objects.equals(userId, other.userId);
	}
	
	

}
