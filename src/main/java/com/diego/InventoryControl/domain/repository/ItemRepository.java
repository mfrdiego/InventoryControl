package com.diego.InventoryControl.domain.repository;

import com.diego.InventoryControl.domain.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, String> {
    boolean existsBySerialNumber(String itemNumber);
}
