package com.shopverse.product.controller;

import com.shopverse.product.dto.ProductRequest;
import com.shopverse.product.entity.Product;
import com.shopverse.product.service.ProductService;

import jakarta.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;
	private final ObjectMapper objectMapper;

	public ProductController(ProductService productService, ObjectMapper objectMapper) {
		this.productService = productService;
		this.objectMapper = objectMapper;
	}

	@GetMapping
	public List<Product> getAll() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/code/{code}")
	public Product getByCode(@PathVariable String code) {
		return productService.getProductByCode(code);
	}

	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest req) throws JsonProcessingException {
		log.info("Creating product: {}", req.getProductName());
		Product p = new Product();
		p.setProductName(req.getProductName());
		p.setProductCode(req.getProductCode());
		p.setPrice(req.getPrice());
		p.setStockQuantity(req.getStockQuantity());
		p.setStatus(req.getStatus() == null ? "ACTIVE" : req.getStatus());
		if (req.getProductDetails() != null) {
			p.setProductDetails(objectMapper.writeValueAsString(req.getProductDetails()));
		}
		Product saved = productService.saveProduct(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductRequest req)
			throws JsonProcessingException {
		Product p = new Product();
		p.setProductName(req.getProductName());
		p.setProductCode(req.getProductCode());
		p.setPrice(req.getPrice());
		p.setStockQuantity(req.getStockQuantity());
		p.setStatus(req.getStatus());
		if (req.getProductDetails() != null) {
			p.setProductDetails(objectMapper.writeValueAsString(req.getProductDetails()));
		}

		Product updated = productService.updateProduct(id, p);

		if (updated == null) {
			return ResponseEntity.notFound().build(); // ✅ 404
		}
		return ResponseEntity.ok(updated); // ✅ 200
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
