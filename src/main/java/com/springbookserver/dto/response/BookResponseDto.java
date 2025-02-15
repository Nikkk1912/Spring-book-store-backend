package com.springbookserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto implements Serializable {

    private Long id;
    private String title;
    private List<AuthorResponseDto> authors;
    private List<GenreResponseDto> genres;
    private BigDecimal price;
    private int stock;
    private String coverImageFile;
}
