package com.springbookserver.dto.reques;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthorRequestDto {

    @NotBlank(message = "FirstName is mandatory")
    @Size(max = 255, message = "FirstName can`t exceed 255 characters")
    private String firstName;

    @Size(max = 255, message = "MiddleName can`t exceed 255 characters")
    private String middleName;

    @NotBlank(message = "LastName is mandatory")
    @Size(max = 255, message = "LastName can`t exceed 255 characters")
    private String lastName;
}
