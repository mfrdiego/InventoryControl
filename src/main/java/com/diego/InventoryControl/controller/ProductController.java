package com.diego.InventoryControl.controller;

import com.diego.InventoryControl.domain.dtos.ProductDto;
import com.diego.InventoryControl.domain.model.ProductModel;
import com.diego.InventoryControl.domain.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
        //verificação antes de salvar se existe algum serialNumber
        if (productService.existsBySerialNumber(productDto.getSerialNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(("Serial number in use"));
        }
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
    @GetMapping("/{serialNumber}")
    public ResponseEntity<Object> getBuscaSerialNumber(@PathVariable(value = "serialNumber") String serialNumber) {
        Optional<ProductModel> productModelOptional = productService.findBySerialNumber(serialNumber);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("serial number not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Object> deleteSerialNumber(@PathVariable(value = "serialNumber") String cpf) {
        Optional<ProductModel> productModelOptional = productService.findBySerialNumber(cpf);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("serial number not found");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("serial number successfully deleted");
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Object> atualizarProduct(@PathVariable(value = "serialNumber") String serialNumber,
                                                   @RequestBody @Valid ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productService.findBySerialNumber(serialNumber);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serial number not found");
        }
        ProductModel productModel = productModelOptional.get();
        productModel.setName(productDto.getName());
        productModel.setCategory(productDto.getCategory());
        productModel.setDescription(productDto.getDescription());
        productModel.setQuantity(productDto.getQuantity());
        productModel.setStatus(productDto.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
}