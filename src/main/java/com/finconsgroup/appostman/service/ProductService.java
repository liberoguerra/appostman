package com.finconsgroup.appostman.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finconsgroup.appostman.model.Product;
import com.finconsgroup.appostman.model.ProductOrder;
import com.finconsgroup.appostman.model.ProductPrice;
import com.finconsgroup.appostman.model.User;
import com.finconsgroup.appostman.model.key.ProductOrderKey;
import com.finconsgroup.appostman.repository.ProductOrderRepository;
import com.finconsgroup.appostman.repository.ProductPriceRepository;
import com.finconsgroup.appostman.repository.ProductRepository;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	private String provaSingleton = "";

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductOrderRepository repositoryProductOrder;

	@Autowired
	private ProductPriceRepository repositoryProductPrice;

	@Autowired
	private UserService userService;

	/**
	 * Returns a list of all products
	 * 
	 * @return
	 */
	public List<Product> list() {
		logger.info("Sono qui:" + provaSingleton);
		return repository.findAll();
	}

//	@Transactional(value = TxType.MANDATORY)
	public Product findById(Long id) throws Exception {

		Optional<Product> optionalProduct = repository.findById(id);
		if (optionalProduct.isEmpty()) {
			throw new Exception("Product not found");
		}

		return optionalProduct.get();
	}

	/**
	 * create a new product
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackOn = Exception.class, value = TxType.REQUIRED)
	public Product createProduct(Product product) throws Exception {
		Product productSaved = null;
		try {
			productSaved = repository.save(product);
		} catch (Exception e) {
			throw new Exception("Error during product creation");
		}

		/*
		 * simulate the rollback
		 * 
		 */

//		productSaved = repository.save(product);
//		if (productSaved != null) {
//			throw new RuntimeException("rollback");
//		}

		return productSaved;
	}

	/**
	 * update a product
	 * 
	 * @param product
	 * @return
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public Product updateProduct(Product product) throws Exception {
		provaSingleton = "ciao";

		Optional<Product> optionalProduct = repository.findById(product.getId());
		if (optionalProduct.isEmpty()) {
			throw new Exception("Product not found");
		}

		Product productToModify = optionalProduct.get();

		productToModify.setDescription(product.getDescription());
		productToModify.setName(product.getName());

		productToModify = repository.save(productToModify);
		logger.info("save product twice con verion {}", productToModify.getVersion());

		return productToModify;
	}

	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class) // , rollbackOn = Exception.class)
	public Product updateProductWithRollback(Product product) throws Exception {

		Optional<Product> optionalProduct = repository.findById(product.getId());
		if (optionalProduct.isEmpty()) {
			logger.info("product not found");
			throw new Exception("Product not found");
		}
		logger.info("product found");
		Product productToModify = optionalProduct.get();

		productToModify.setDescription(product.getDescription());
		productToModify.setName(product.getName());

		productToModify = repository.save(productToModify);

		logger.info("throw new exception to simulate rollback");
		throw new Exception("simulate rollback");

	}

	@Transactional(value = TxType.REQUIRES_NEW)
	public void simulateOptimistickLock(Long id, String description) {
		Product product;
		try {
			product = this.findById(id);
			product.setDescription(description);
			product = repository.save(product);
			logger.info("save product con verion {}", product.getVersion());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}

	@Transactional(value = TxType.REQUIRED)
	public Product save(Product product) throws Exception {

		product = repository.save(product);
		logger.info("save product con verion {}", product.getVersion());

		return product;
	}

	@Transactional(value = TxType.REQUIRED)
	public void simulateOptimistickLockDelete(Long id) {
		try {
			Product product = findById(id);
			repository.delete(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Transactional
	public ProductOrder ordinaProdotto(Long productId, Long userId, ProductPrice price) throws Exception {
	
		Product product = this.findById(productId);
		User user = userService.findById(userId);
		
		price.setProduct(product);
		ProductPrice priceSaved = repositoryProductPrice.save(price);
		//here we are just saved the product price
		
		ProductOrder productOrder = new ProductOrder();
		productOrder.setOrderDateTime(LocalDateTime.now());
		
		ProductOrderKey key = new ProductOrderKey();
		key.setPriceId(priceSaved.getId());
		key.setProductId(productId);
		key.setUserId(userId);
		
		productOrder.setId(key);
		
		productOrder = repositoryProductOrder.save(productOrder);
		return productOrder;
		
		
		
	}

}
