package com.shopverse.product.exception;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id);
    }
    public ProductNotFoundException(String code) {
        super("Product not found with code: " + code);
    }
}
