package com.in2it.cats.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomErrorResponseDTO {

    private final String errorCode;
    private final Object errorMsg;
    private final String errorDescription;
}
