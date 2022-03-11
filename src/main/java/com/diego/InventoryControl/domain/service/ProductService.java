package com.diego.InventoryControl.domain.service;

import com.diego.InventoryControl.domain.model.ProductModel;
import com.diego.InventoryControl.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }
    public boolean existsByPartNumber(String PartNumber) {
        return productRepository.existsByPartNumber(PartNumber);
    }

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> findByPartNumber(String partNumber) {
        return productRepository.findById(partNumber);
    }

    @Transactional
    public void delete(ProductModel productModel) {
        productRepository.delete(productModel);
    }
}