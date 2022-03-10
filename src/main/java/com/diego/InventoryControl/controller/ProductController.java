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
        //verificação antes de salvar se existe algum partNumber
        if (productService.existsByPartNumber(productDto.getPartNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(("Part number in use"));
        }
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
    @GetMapping("/{partNumber}")
    public ResponseEntity<Object> getBuscaPartNumber(@PathVariable(value = "partNumber") String partNumber) {
        Optional<ProductModel> productModelOptional = productService.findByPartNumber(partNumber);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part number not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @DeleteMapping("/{partyNumber}")
    public ResponseEntity<Object> deletePartNumber(@PathVariable(value = "partNumber") String partNumber) {
        Optional<ProductModel> productModelOptional = productService.findByPartNumber(partNumber);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part number not found");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Part number successfully deleted");
    }

    @PutMapping("/{partNumber}")
    public ResponseEntity<Object> atualizarProduct(@PathVariable(value = "partNumber") String serialNumber,
                                                   @RequestBody @Valid ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productService.findByPartNumber(serialNumber);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part number not found");
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