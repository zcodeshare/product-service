package com.in2it.cats.productservice.service.impl;

import com.in2it.cats.productservice.dto.ProductRequestDTO;
import com.in2it.cats.productservice.dto.ProductResponseDTO;
import com.in2it.cats.productservice.entity.Product;
import com.in2it.cats.productservice.exception.ProductNotFoundException;
import com.in2it.cats.productservice.repository.ProductRepository;
import com.in2it.cats.productservice.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());

        Product savedProduct = productRepository.save(product);

        ProductResponseDTO response = new ProductResponseDTO();

        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setCategory(savedProduct.getCategory());
        response.setStock(savedProduct.getStock());

        return response;
    }

    @Override
    public ProductResponseDTO getProductById(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        ));

        ProductResponseDTO response = new ProductResponseDTO();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setCategory(product.getCategory());
        response.setStock(product.getStock());

        return response;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> {

                    ProductResponseDTO response =
                            new ProductResponseDTO();

                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setDescription(product.getDescription());
                    response.setPrice(product.getPrice());
                    response.setCategory(product.getCategory());
                    response.setStock(product.getStock());

                    return response;
                })
                .toList();
    }

    @Override
    public ProductResponseDTO updateProduct(
            String id,
            ProductRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id
                        ));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setStock(request.getStock());

        Product updatedProduct = productRepository.save(product);

        ProductResponseDTO response = new ProductResponseDTO();

        response.setId(updatedProduct.getId());
        response.setName(updatedProduct.getName());
        response.setDescription(updatedProduct.getDescription());
        response.setPrice(updatedProduct.getPrice());
        response.setCategory(updatedProduct.getCategory());
        response.setStock(updatedProduct.getStock());

        return response;
    }

    @Override
    public void deleteProduct(String id) {

        if (!productRepository.existsById(id)) {

            throw new ProductNotFoundException(
                    "Product not found with id: " + id
            );
        }

        productRepository.deleteById(id);
    }
}