
package com.RoadSetu.RoadSetu.dto;

import lombok.Data;

@Data
public class CompanyDto {

    // Company Details
    private String companyId;
    private String companyName;
    private String companyType;
    private String gstNumber;
    private String panNumber;
    private String companyEmail;
    private String mobileNumber;

    // Contact Person Details
    private String contactPersonName;
    private String designation;
    private String contactMobileNumber;
    private String contactEmail;

    // Company Address
    private String country;
    private String state;
    private String district;
    private String fullAddress;
    private String pinCode;

    // Document References
    private String gstCertificate;
    private String panCardDocument;
    private String businessRegistrationCertificate;

    // Login Details
    private String emailId;
    private String password;
    private String confirmPassword;

    private Boolean active;
}
