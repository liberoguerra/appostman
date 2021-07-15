package com.finconsgroup.appostman.restcontroller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finconsgroup.appostman.model.Product;
import com.finconsgroup.appostman.model.ProductPrice;
import com.finconsgroup.appostman.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

	private static final Logger logger = LoggerFactory.getLogger(ProductApi.class);

	@Autowired
	private ProductService productService;

	/**
	 * product list
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Object> productList() {
		logger.info("start productList");
		return new ResponseEntity<Object>(productService.list(), HttpStatus.OK);
	}

	/**
	 * create a new product
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Object> addproduct(@RequestBody Product product) {
		try {
			return new ResponseEntity<Object>(productService.createProduct(product), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * update an existent product
	 * 
	 * @param product
	 * @return
	 */
	@PutMapping
	ResponseEntity<Object> updateProdcut(@RequestBody Product product) {
		try {
			return new ResponseEntity<Object>(productService.updateProduct(product), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * simulate a rollback
	 * 
	 * @param product
	 * @return
	 */
	@PutMapping(value = "/simulaterollback")
	ResponseEntity<Object> updateProdcutWithRollback(@RequestBody Product product) {
		logger.info("simulate Rollback");
		try {
			return new ResponseEntity<Object>(productService.updateProductWithRollback(product), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * simulate an Optimistick lock
	 * 
	 * @param product
	 * @return
	 */
	@PutMapping(value = "/opt")
	ResponseEntity<Object> simulateOptimistickProdcut(@RequestBody Product product) {
		logger.info("simulate optimistick lock");
		final ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.execute(() -> productService.simulateOptimistickLock(product.getId(), "prova 1"));
		executor.execute(() -> productService.simulateOptimistickLock(product.getId(), "prova 2"));

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * Method for simulate optimistick lock during delete
	 * 
	 * @return
	 */
	@DeleteMapping
	ResponseEntity<Object> simulateOptimistickLockDelete(@RequestParam(name = "id") Long id) {
		logger.info("simulate optimistick lock during delete");

		try {
			final ExecutorService executor = Executors.newFixedThreadPool(2);
			executor.execute(() -> productService.simulateOptimistickLockDelete(id));
			executor.execute(() -> productService.simulateOptimistickLockDelete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return null;
	}

	/**
	 * simulate a rollback
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping(value = "/order")
	ResponseEntity<Object> createOrder(@RequestBody ProductPrice price,
			@RequestParam(name = "productId") Long productId, @RequestParam(name = "userId") Long userId) {
		try {
			return new ResponseEntity<Object>(productService.ordinaProdotto(productId, userId, price), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	

}
