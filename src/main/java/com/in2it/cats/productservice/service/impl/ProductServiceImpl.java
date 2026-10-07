package com.in2it.cats.productservice.service.impl;

import com.in2it.cats.productservice.dto.ProductRequestDTO;
import com.in2it.cats.productservice.dto.ProductResponseDTO;
import com.in2it.cats.productservice.entity.Product;
import com.in2it.cats.productservice.exception.ProductNotFoundException;
import com.in2it.cats.productservice.repository.ProductRepository;
import com.in2it.cats.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        Product savedProduct = productRepository.save(product);

        ProductResponseDTO response = ProductResponseDTO.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .category(savedProduct.getCategory())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .build();
        return response;
    }

    @Override
    public ProductResponseDTO getProductById(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id : "+id));

        ProductResponseDTO response = ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
        return response;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts(){

        List<ProductResponseDTO> response = productRepository.findAll()
                .stream()
                .map(product -> ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .category(product.getCategory())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build()
                ).toList();
        return response;
    }

    @Override
    public ProductResponseDTO updateProduct(String id, ProductRequestDTO request){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : "+id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Product updatedProduct = productRepository.save(product);

        ProductResponseDTO response = ProductResponseDTO.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .category(updatedProduct.getCategory())
                .price(updatedProduct.getPrice())
                .stock(updatedProduct.getStock())
                .build();
        return response;
    }

    @Override
    public void deleteProduct(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id : "+id));

        productRepository.delete(product);
    }
}