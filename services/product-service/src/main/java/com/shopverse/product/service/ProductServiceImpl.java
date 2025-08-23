package com.shopverse.product.service;

import com.shopverse.product.entity.Product;
import com.shopverse.product.exception.ProductNotFoundException;
import com.shopverse.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product getProductByCode(String code) {
        return repo.findByProductCode(code)
                .orElseThrow(() -> new ProductNotFoundException(code));
    }

    @Override
    public Product createProduct(Product product) {
        return repo.save(product);
    }

    
    @Override
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));


        existing.setPrice(product.getPrice());
        existing.setProductCode(product.getProductCode());

        return repo.save(existing);
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
