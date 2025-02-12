package com.springbookserver.xml.xml_dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "author")
public class AuthorXmlDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    @XmlElement
    public Long getId() { return id; }

    @XmlElement
    public String getFirstName() { return firstName; }

    @XmlElement
    public String getMiddleName() { return middleName; }

    @XmlElement
    public String getLastName() { return lastName; }
}

