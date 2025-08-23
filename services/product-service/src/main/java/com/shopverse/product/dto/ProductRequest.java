package com.shopverse.product.dto;

import java.math.BigDecimal;
import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
	@NotBlank(message = "Product name is required")
	private String productName;
	@NotBlank(message = "Product code is required")
	private String productCode;
	@NotBlank(message = "Price is required")
	@Min(value = 1, message = "Price must be greater than 0")
	private BigDecimal price;
	@NotBlank(message = "Stock Quantity is required")
	@Min(value = 0, message = "Stock Quantity cannot be Negative")
	private Integer stockQuantity;
	private Map<String, Object> productDetails; // accepts JSON object
	private String status;

	// getters & setters
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Map<String, Object> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Map<String, Object> productDetails) {
		this.productDetails = productDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
