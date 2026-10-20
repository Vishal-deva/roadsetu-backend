
package com.RoadSetu.RoadSetu.entity;

import com.RoadSetu.RoadSetu.dto.CompanyDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "companies")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String companyId;

    // Company Details
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

    @Column(length = 1000)
    private String fullAddress;

    private String pinCode;

    // Document References
    private String gstCertificate;
    private String panCardDocument;
    private String businessRegistrationCertificate;

    // Verification
    private String verificationStatus;

    // Status
    private Boolean active;
}
