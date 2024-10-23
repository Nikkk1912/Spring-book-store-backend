package com.springbookserver.dto.reques;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title can`t exceed 255 characters")
    private String title;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    @NotNull(message = "Stock is mandatory")
    private int stock;

    @NotNull(message = "Author id is mandatory")
    private List<Long> authorIds;

    @NotNull(message = "Genre id is mandatory")
    private List<Long> genreIds;

}
