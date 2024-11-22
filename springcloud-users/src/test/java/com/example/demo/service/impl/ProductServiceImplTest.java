package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.vo.PageResponseVO;
import com.example.demo.vo.ResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts_WithNameFilter(){
        ProductDto productDto = new ProductDto();
        productDto.setName("product");
        productDto.setPage(1);
        productDto.setPageSize(2);

        Product product2 = new Product(2, "product 2", "description", 2, 2, 3.0);
        Product product1 = new Product(1, "product 1", "description", 1, 2, 3.0);

        Pageable pageable = PageRequest.of(productDto.getPage()-1, productDto.getPageSize(), Sort.by("id").descending());
        Page<Product> products = new PageImpl<>(Arrays.asList(product1, product2));

        when(productRepository.findAllByNameContainingIgnoreCase(productDto.getName(), pageable)).thenReturn(products);

        ResponseEntity<PageResponseVO> response = productService.getProducts(productDto);

        assertEquals(HttpStatus.OK.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals(2, response.getBody().getTotal());
        assertNotNull(response.getBody().getData());
    }

    @Test
    void testAddProduct_Success() {
        Product product = new Product(1, "product 1", "description", 1, 2, 3.0);

        when(productRepository.findByName(product.getName())).thenReturn(Optional.empty());

        ResponseEntity<ResponseVO> response = productService.addProduct(product);

        verify(productRepository, times(1)).save(product);
        assertEquals(HttpStatus.CREATED.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals(HttpStatus.CREATED.getReasonPhrase(), response.getBody().getMessage());
    }

    @Test
    void testAddProduct_ConflictException() {
        Product product = new Product(1,"name","description",4,5,10.0);

        when(productRepository.findByName(product.getName())).thenReturn(Optional.of(product));

        ResponseEntity<ResponseVO> response = productService.addProduct(product);

        verify(productRepository, times(0)).save(any(Product.class));
        assertEquals(HttpStatus.CONFLICT.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("already exist", response.getBody().getMessage());
    }

    @Test
    void testUpdateProduct_ProductExist() {
        Product oldProduct = new Product(1,"product","description",1,10,1.10);
        Product updatedProduct = new Product(1,"product","description",1,10,1.10);

        when(productRepository.findById(oldProduct.getId())).thenReturn(Optional.of(oldProduct));

        ResponseEntity<ResponseVO> response = productService.updateProduct(updatedProduct);

        verify(productRepository, times(1)).save(updatedProduct);
        assertEquals(HttpStatus.OK.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("updated successfully", response.getBody().getMessage());
    }

    @Test
    void testUpdateProduct_ProductNotFound() {
        Product product = new Product(1,"product","description",1,10,1.10);

        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        ResponseEntity<ResponseVO> response = productService.updateProduct(product);

        verify(productRepository, times(0)).save(any(Product.class));
        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("product not found", response.getBody().getMessage());
    }

    @Test
    void testRemoveProduct_IdNotFound() {
        Integer id = 1;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ResponseVO> response = productService.removeProduct(id);

        verify(productRepository, times(0)).deleteById(id);
        assertEquals(HttpStatus.NOT_FOUND.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("not found", response.getBody().getMessage());
    }

    @Test
    void testRemoveProduct_Success() {
        Product existingProduct = new Product(1,"product","description",1,1,1.10);

        when(productRepository.findById(existingProduct.getId())).thenReturn(Optional.of(existingProduct));

        ResponseEntity<ResponseVO> response = productService.removeProduct(existingProduct.getId());

        verify(productRepository, times(1)).deleteById(existingProduct.getId());
        assertEquals(HttpStatus.NO_CONTENT.value(), Objects.requireNonNull(response.getBody()).getCode());
        assertEquals("deleted successfully", response.getBody().getMessage());
    }

}