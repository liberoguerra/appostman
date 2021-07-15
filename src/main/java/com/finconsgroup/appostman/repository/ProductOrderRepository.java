package com.finconsgroup.appostman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.ProductOrder;
import com.finconsgroup.appostman.model.key.ProductOrderKey;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderKey>{

}
