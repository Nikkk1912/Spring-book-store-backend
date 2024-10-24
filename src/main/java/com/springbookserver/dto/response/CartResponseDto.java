package com.springbookserver.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartResponseDto {

    private Long id;
    private Long userOwnerId;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private List<CartItemResponseDto> items;
}
