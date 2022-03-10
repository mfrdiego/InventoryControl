package com.diego.InventoryControl.domain.repository;

import com.diego.InventoryControl.domain.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    boolean existsByPartNumber(String partNumber);
}
