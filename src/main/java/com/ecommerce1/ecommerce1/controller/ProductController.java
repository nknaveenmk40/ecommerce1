package com.ecommerce1.ecommerce1.controller;

import com.ecommerce1.ecommerce1.common.ApiResponse;
import com.ecommerce1.ecommerce1.dto.ProductDto;
import com.ecommerce1.ecommerce1.model.Category;
import com.ecommerce1.ecommerce1.repository.Categoryrepository;
import com.ecommerce1.ecommerce1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    Categoryrepository categoryrepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryrepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false,"category does not exist"),HttpStatus.NOT_FOUND);
        }
        productService.createProduct(productDto,optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true,"product has been added"),HttpStatus.CREATED);

    }
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK) ;
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestParam ("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryrepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been updated"),HttpStatus.CREATED);

        }

    }
