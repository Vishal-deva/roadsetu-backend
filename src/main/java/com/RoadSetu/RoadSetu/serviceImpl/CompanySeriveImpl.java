
package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.CompanyDto;
import com.RoadSetu.RoadSetu.dto.CompanyResponseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.CompanyEntity;
import com.RoadSetu.RoadSetu.repository.CompanyRepository;
import com.RoadSetu.RoadSetu.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanySeriveImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ResponseDto saveCompanyDetails(CompanyDto companyDto) {

        ResponseDto responseDto = new ResponseDto();

        boolean isNewCompany =
                companyDto.getCompanyId() == null
                        || companyDto.getCompanyId().isBlank();

        CompanyEntity companyEntity;

        if (isNewCompany) {

            // CREATE: Create a new company
            companyEntity = new CompanyEntity();
            companyEntity.setActive(true);
            companyEntity.setVerificationStatus("PENDING");

        } else {

            // UPDATE: Find the existing company
            Optional<CompanyEntity> optionalCompany =
                    companyRepository.findById(companyDto.getCompanyId());

            if (optionalCompany.isEmpty()) {
                responseDto.setMessage("Company not found");
                responseDto.setStatusCode(404);
                return responseDto;
            }

            // IMPORTANT: Use the existing database entity
            companyEntity = optionalCompany.get();
        }

        try {

            // Company Details
            companyEntity.setCompanyName(companyDto.getCompanyName());
            companyEntity.setCompanyType(companyDto.getCompanyType());
            companyEntity.setGstNumber(companyDto.getGstNumber());
            companyEntity.setPanNumber(companyDto.getPanNumber());
            companyEntity.setCompanyEmail(companyDto.getCompanyEmail());
            companyEntity.setMobileNumber(companyDto.getMobileNumber());

            // Contact Person Details
            companyEntity.setContactPersonName(
                    companyDto.getContactPersonName());
            companyEntity.setDesignation(companyDto.getDesignation());
            companyEntity.setContactMobileNumber(
                    companyDto.getContactMobileNumber());
            companyEntity.setContactEmail(companyDto.getContactEmail());

            // Company Address
            companyEntity.setCountry(companyDto.getCountry());
            companyEntity.setState(companyDto.getState());
            companyEntity.setDistrict(companyDto.getDistrict());
            companyEntity.setFullAddress(companyDto.getFullAddress());
            companyEntity.setPinCode(companyDto.getPinCode());

            // Document References
            companyEntity.setGstCertificate(
                    companyDto.getGstCertificate());
            companyEntity.setPanCardDocument(
                    companyDto.getPanCardDocument());
            companyEntity.setBusinessRegistrationCertificate(
                    companyDto.getBusinessRegistrationCertificate());

            // Save new or updated company
            companyRepository.save(companyEntity);

            if (isNewCompany) {
                responseDto.setMessage("Company registered successfully");
                responseDto.setStatusCode(201);
            } else {
                responseDto.setMessage("Company updated successfully");
                responseDto.setStatusCode(200);
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to save company details", e);
        }

        return responseDto;
    }

    @Override
    public CompanyResponseDto getCompanyDetails(String companyId) {

        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        try {
            ResponseDto responseDto = new ResponseDto();
            Optional<CompanyEntity>optCompany = companyRepository.findById(companyId);
            if(optCompany.isPresent())
            {
                CompanyEntity companyEntity = optCompany.get();
                companyResponseDto.setCompanyName(companyEntity.getCompanyName());
                companyResponseDto.setCompanyEmail(companyEntity.getCompanyEmail());
                companyResponseDto.setCompanyMobiileNumber(companyEntity.getContactMobileNumber());
                companyResponseDto.setContactPersonName(companyEntity.getContactPersonName());
                companyResponseDto.setDesignation(companyEntity.getDesignation());
                companyResponseDto.setFullAddress(companyEntity.getFullAddress());
            }
            else {

                responseDto.setMessage("Company Not Found");
                responseDto.setStatusCode(404);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return companyResponseDto;
    }
}
