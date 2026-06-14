package com.RoadSetu.RoadSetu.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OwnerDetailsDto {
    private String ownerId;

    @NotBlank(message = "Owner Name is Required")
    private String ownerName;

    @Email(message = "Email Id is Required")
    private String ownerEmailId;

    @NotBlank(message = "Mobile Number is Required")
    private String ownerMobileNumber;

    @NotBlank(message = "Password is Required")
    private String ownerPassword;

    @NotBlank(message = "Company Name is Required")
    private String companyName;
}
