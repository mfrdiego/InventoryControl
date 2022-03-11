package com.diego.InventoryControl.controller;


import com.diego.InventoryControl.domain.dtos.ItemDto;
import com.diego.InventoryControl.domain.model.ItemModel;
import com.diego.InventoryControl.domain.model.ProductModel;
import com.diego.InventoryControl.domain.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/item")
public class ItemController {

    final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemDto itemDto){
        if (itemService.existsBySerialNumber(itemDto.getSerialNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(("Serial number in user"));
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDto, itemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemModel));
    }
    @GetMapping
    public ResponseEntity<List<ItemModel>> getAllItem(){
        return  ResponseEntity.status(HttpStatus.OK).body(itemService.findAll());
    }
    @GetMapping("/{serialNumber}")
    public ResponseEntity<Object> getFindPartNumber(@PathVariable(value = "serialNumber") String serialNumber) {
        Optional<ItemModel> itemModelOptional = itemService.findBySerialNumber(serialNumber);
        if (!itemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serial number not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itemModelOptional.get());
    }
    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Object> deleteSerialNumber(@PathVariable(value = "serialNumber") String serialNumber) {
        Optional<ItemModel> itemModelOptional = itemService.findBySerialNumber(serialNumber);
        if (!itemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serial number not found");
        }
        itemService.delete(itemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Serial number successfully deleted");
    }
    @PutMapping("/{serialNumber}")
    public ResponseEntity<Object> upadeteItem(@PathVariable(value = "serialNumber") String serialNumber,
                                                   @RequestBody @Valid ItemDto itemDto) {
        Optional<ItemModel> itemModelOptional = itemService.findBySerialNumber(serialNumber);
        if (!itemModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serial number not found");
        }
        ItemModel itemModel = itemModelOptional.get();
        itemModel.setLocal(itemDto.getLocal());
        itemModel.setQuality(itemDto.getQuality());
        itemModel.setNote(itemDto.getNote());
        return ResponseEntity.status(HttpStatus.OK).body(itemService.save(itemModel));
    }
}
