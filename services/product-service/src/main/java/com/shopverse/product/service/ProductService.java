package com.shopverse.product.service;

import com.shopverse.product.entity.Product;
import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product getProductByCode(String code);

	Product createProduct(Product product);
	
	Product saveProduct(Product p);
	
	Product updateProduct(Long id, Product p);

	void deleteProduct(Long id);
}
