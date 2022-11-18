package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long Id;
    private String ownerName;
    @NotNull(message = "Owner Email is mandatory")
    private String ownerEmail;
    @Size(min = 10,max=15,message = "Owner phone must consist of more than 10 digits")
    private String phone;
    @NotNull(message = "Password is mandatory")
    private String password;
}
