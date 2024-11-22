package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.vo.PageResponseVO;
import com.example.demo.vo.ResponseVO;
import org.springframework.http.ResponseEntity;


public interface ProductService {

    ResponseEntity<PageResponseVO> getProducts(ProductDto productDto);

    ResponseEntity<Product> getProductById(Integer id);

    ResponseEntity<ResponseVO> addProduct(Product product);

    ResponseEntity<ResponseVO> updateProduct(Product product);

    ResponseEntity<ResponseVO> removeProduct(Integer id);

}
