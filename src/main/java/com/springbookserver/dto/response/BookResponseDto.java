package com.springbookserver.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BookResponseDto implements Serializable {

    private Long id;
    private String title;
    private List<AuthorResponseDto> authors;
    private List<GenreResponseDto> genres;
    private BigDecimal price;
    private int stock;
    private String coverImageFile;
}
