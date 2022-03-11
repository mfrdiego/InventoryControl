package com.diego.InventoryControl.domain.service;

import com.diego.InventoryControl.domain.model.ItemModel;
import com.diego.InventoryControl.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Transactional
    public ItemModel save(ItemModel itemModel) {
        return itemRepository.save(itemModel);
    }
    public boolean existsBySerialNumber(String ItemNumber) {
        return itemRepository.existsBySerialNumber(ItemNumber);
    }

    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }

    public Optional<ItemModel> findBySerialNumber(String serialNumber) {
        return itemRepository.findById(serialNumber);
    }

    @Transactional
    public void delete(ItemModel productModel) {
        itemRepository.delete(productModel);
    }
}
