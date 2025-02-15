package com.springbookserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto implements Serializable {
    private Long id;
    private String fistName;
    private String middleName;
    private String lastName;
}
