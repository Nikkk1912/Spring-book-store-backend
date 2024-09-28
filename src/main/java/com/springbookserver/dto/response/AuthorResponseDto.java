package com.springbookserver.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorResponseDto implements Serializable {
    private Long id;
    private String fistName;
    private String middleName;
    private String lastName;
}
