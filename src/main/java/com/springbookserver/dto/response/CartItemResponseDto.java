package com.springbookserver.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
