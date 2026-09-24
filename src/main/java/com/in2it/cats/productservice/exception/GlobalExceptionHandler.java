package com.in2it.cats.productservice.exception;


import com.in2it.cats.productservice.constant.ProductConstants;
import com.in2it.cats.productservice.dto.CustomErrorResponseDTO;
import com.in2it.cats.productservice.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFound(ProductNotFoundException exception) {

        CustomErrorResponseDTO errorInfo = new CustomErrorResponseDTO(
                        "PRODUCT_NOT_FOUND",
                        exception.getMessage(),
                        "No product exists with the given id"
                );

        ResponseDTO response = new ResponseDTO(false, null, errorInfo);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        CustomErrorResponseDTO errorInfo = new CustomErrorResponseDTO("VALIDATION_ERROR",
                        ProductConstants.VALIDATION_ERROR,
                        errors.toString()
                );

        ResponseDTO response = new ResponseDTO(false, null, errorInfo);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(
            Exception exception) {

        CustomErrorResponseDTO errorInfo = new CustomErrorResponseDTO(
                        "INTERNAL_SERVER_ERROR",
                        ProductConstants.INTERNAL_SERVER_ERROR,
                        exception.getMessage()
                );

        ResponseDTO response = new ResponseDTO(false, null, errorInfo);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}