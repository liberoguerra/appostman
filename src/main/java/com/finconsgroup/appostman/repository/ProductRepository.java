package com.finconsgroup.appostman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
