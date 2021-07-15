package com.finconsgroup.appostman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long>{

}
