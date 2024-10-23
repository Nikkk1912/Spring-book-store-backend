package com.springbookserver.dto.reques;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenreRequestDto {

    @NotBlank(message = "Genre is mandatory")
    @Size(max = 255, message = "Genre can`t exceed 255 characters")
    private String genre;
}
