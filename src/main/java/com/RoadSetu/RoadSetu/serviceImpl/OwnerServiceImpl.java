package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.OwnerDetailsDto;
import com.RoadSetu.RoadSetu.dto.OwnerResponseDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import com.RoadSetu.RoadSetu.repository.OwnerRepository;
import com.RoadSetu.RoadSetu.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    @Override
    public OwnerResponseDto saveOwnerDetails(OwnerDetailsDto ownerDetailsDto) {

        OwnerResponseDto responseDto = new OwnerResponseDto();
        try {

            OwnerEntity ownerEntity = new OwnerEntity();
            if (ownerDetailsDto.getOwnerId() == null) {
                ownerEntity.setOwnerName(ownerDetailsDto.getOwnerName());
                ownerEntity.setOwnerEmailId(ownerDetailsDto.getOwnerEmailId());
                ownerEntity.setOwnerMobileNumber(ownerDetailsDto.getOwnerMobileNumber());
                ownerEntity.setOwnerPassword(ownerDetailsDto.getOwnerPassword());
                ownerEntity.setCompanyName(ownerDetailsDto.getCompanyName());

                ownerEntity = ownerRepository.save(ownerEntity);

                responseDto.setStatusCode(200);
                responseDto.setMessage("Details Created Success!...");
                responseDto.setOwnerId(ownerEntity.getOwnerId());
            } else {

                ownerEntity.setOwnerEmailId(ownerDetailsDto.getOwnerEmailId());
                ownerEntity.setOwnerMobileNumber(ownerDetailsDto.getOwnerMobileNumber());
                ownerEntity.setOwnerPassword(ownerDetailsDto.getOwnerPassword());
                ownerRepository.save(ownerEntity);

                responseDto.setStatusCode(200);
                responseDto.setMessage("Details Updated Success!...");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseDto;
    }

    @Override
    public OwnerDetailsDto getOwnerDetails(String id) {
        try {
            if (id == null || id.isEmpty()) {
                throw new RuntimeException("Id cannot be null");
            }
            OwnerDetailsDto ownerDetailsDto = new OwnerDetailsDto();
            Optional<OwnerEntity> ownEntOpt = ownerRepository.findById(id);
            if (ownEntOpt.isPresent()) {
                OwnerEntity ownerEntity = ownEntOpt.get();
                ownerDetailsDto.setOwnerId(ownerEntity.getOwnerId());
                ownerDetailsDto.setOwnerName(ownerEntity.getOwnerName());
                ownerDetailsDto.setOwnerMobileNumber(ownerEntity.getOwnerMobileNumber());
                ownerDetailsDto.setOwnerEmailId(ownerEntity.getOwnerEmailId());
                ownerDetailsDto.setCompanyName(ownerEntity.getCompanyName());

                return ownerDetailsDto;
            } else {
                throw new RuntimeException("Details Not Found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OwnerResponseDto login(OwnerDetailsDto ownerDetailsDto) {

        OwnerResponseDto responseDto = new OwnerResponseDto();

        try {

            Optional<OwnerEntity> ownerEntity =
                    ownerRepository.findByownerEmailId(
                            ownerDetailsDto.getOwnerEmailId()
                    );

            if (ownerEntity.isPresent()) {

                OwnerEntity ownerValue = ownerEntity.get();

                responseDto.setOwnerId(ownerValue.getOwnerId());
                responseDto.setMessage("Login Successfully");
                responseDto.setStatusCode(200);

            } else {

                responseDto.setMessage("Invalid email or password");
                responseDto.setStatusCode(401);
            }

        } catch (Exception e) {
            responseDto.setMessage("Something went wrong: " + e.getMessage());
            responseDto.setStatusCode(500);
        }

        return responseDto;
    }
}
