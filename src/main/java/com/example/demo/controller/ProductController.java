package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.vo.PageResponseVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(path = "/v1/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponseVO> getAllProducts(ProductDto productDto){
        return productService.getProducts(productDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getAllProducts(@PathVariable("id") Integer id){
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<ResponseVO> addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping
    public ResponseEntity<ResponseVO> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseVO> removeProduct(@PathVariable("id") Integer id){
        return productService.removeProduct(id);
    }
}
