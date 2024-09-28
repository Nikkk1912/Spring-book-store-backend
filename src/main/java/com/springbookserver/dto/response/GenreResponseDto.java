package com.springbookserver.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class GenreResponseDto implements Serializable {
    private Long id;
    private String genre;
}
