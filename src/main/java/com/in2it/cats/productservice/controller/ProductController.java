package com.in2it.cats.productservice.controller;

import com.in2it.cats.productservice.dto.ProductRequestDTO;
import com.in2it.cats.productservice.dto.ProductResponseDTO;
import com.in2it.cats.productservice.dto.ResponseDTO;
import com.in2it.cats.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "${product.create}")
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO request) {

        ProductResponseDTO data = productService.createProduct(request);
        ResponseDTO response = new ResponseDTO(true, data, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "${product.getById}")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable String id) {

        ProductResponseDTO data = productService.getProductById(id);
        ResponseDTO response = new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${product.getAll}")
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllProducts() {

        List<ProductResponseDTO> data = productService.getAllProducts();

        ResponseDTO response = new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${product.update}")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequestDTO request) {

        ProductResponseDTO data = productService.updateProduct(id, request);
        ResponseDTO response = new ResponseDTO(true, data, null);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "${product.delete}")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable String id) {

        productService.deleteProduct(id);
        ResponseDTO response = new ResponseDTO(true, null, null);

        return ResponseEntity.ok(response);
    }
}