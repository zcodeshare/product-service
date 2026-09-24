package com.in2it.cats.productservice.service;

import com.in2it.cats.productservice.dto.ProductRequestDTO;
import com.in2it.cats.productservice.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO request);
    ProductResponseDTO getProductById(String id);
    List<ProductResponseDTO> getAllProducts();
    ProductResponseDTO updateProduct(String id, ProductRequestDTO request);
    void deleteProduct(String id);
}