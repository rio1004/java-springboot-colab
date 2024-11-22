package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.vo.PageResponseVO;
import com.example.demo.vo.ResponseVO;
import com.example.demo.service.ProductService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<PageResponseVO> getProducts(ProductDto productDto) {
        int page = productDto.getPage() -1;
        int pageSize = productDto.getPageSize();

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
        Page<Product> list = StringUtils.hasText(productDto.getName())
            ? productRepository.findAllByNameContainingIgnoreCase(productDto.getName(), pageable)
            : productRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(new PageResponseVO(HttpStatus.OK.value(), "success" ,list.getContent(), list.getNumberOfElements()));
    }

    @Override
    public ResponseEntity<ResponseVO> addProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());

        if(existingProduct.isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(new ResponseVO(HttpStatus.CONFLICT.value(), "already exist"));

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new ResponseVO(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase()));
    }

    @Override
    public ResponseEntity<ResponseVO> updateProduct(Product product) {
        return productRepository.findById(product.getId()).map(item -> {
            item.setName(product.getName());
            item.setDescription(product.getDescription());
            item.setQuantity(product.getQuantity());
            item.setType(product.getType());
            item.setUnitPrice(product.getUnitPrice());
            productRepository.save(item);
            return ResponseEntity.ok().body(new ResponseVO(HttpStatus.OK.value(), "updated successfully"));
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseVO(HttpStatus.NOT_FOUND.value(), "product not found")));

    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Optional<Product> existingProduct =productRepository.findById(id);
        return existingProduct.map(product -> ResponseEntity.ok().body(product)).orElse(null);
    }

    @Override
    public ResponseEntity<ResponseVO> removeProduct(Integer id) {
        return productRepository.findById(id).map(item -> {
            productRepository.deleteById(item.getId());
            return ResponseEntity.status(HttpStatus.OK.value()).body(new ResponseVO(HttpStatus.NO_CONTENT.value(), "deleted successfully"));
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new ResponseVO(HttpStatus.NOT_FOUND.value(), "not found")));

    }
}
