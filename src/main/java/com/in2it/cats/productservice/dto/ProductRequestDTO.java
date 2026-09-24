package com.in2it.cats.productservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Product price must be greater than 0")
    private BigDecimal price;
    @NotNull(message = "Product category is required")
    private String category;
    @NotNull(message = "Product stock is required")
    @Min(value = 0, message = "Product stock cannot be negative")
    private Integer stock;
}
