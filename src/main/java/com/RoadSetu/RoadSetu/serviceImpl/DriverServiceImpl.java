package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.DriverDetailsDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.repository.DriverRepository;
import com.RoadSetu.RoadSetu.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public ResponseDto saveDriverDetails(DriverDetailsDto driverDetailsDto) {
        ResponseDto response = new ResponseDto();
        try{
            if(driverDetailsDto.getDriverId() == null || driverDetailsDto.getDriverId().isEmpty())
            {
                //Add New Driver
                DriverEntity driverEntity = new DriverEntity();

                driverEntity.setDriverId(driverEntity.getDriverId());
                driverEntity.setDriverName(driverDetailsDto.getDriverName());
                driverEntity.setDriverMobileNumber(driverDetailsDto.getDriverMobileNumber());
                driverEntity.setDriverLicense(driverDetailsDto.getDriverLicense());
                driverEntity.setDriverNative(driverDetailsDto.getDriverNative());
                driverEntity.setDriverTruck(driverDetailsDto.getDriverTruck());

                driverRepository.save(driverEntity);
                response.setMessage("Driver Created Successfully");
                response.setStatusCode(200);
            }
            else {

                Optional<DriverEntity>optionalDriverEntity = driverRepository.findByDriverId(driverDetailsDto.getDriverId());
                if(optionalDriverEntity.isPresent())
                {
                    DriverEntity driverEntity = new DriverEntity();
                    driverEntity.setDriverTruck(driverDetailsDto.getDriverTruck());
                    driverEntity.setDriverMobileNumber(driverDetailsDto.getDriverMobileNumber());

                    driverRepository.save(driverEntity);
                    response.setStatusCode(200);
                    response.setMessage("Driver Details Updated Successfully");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public List<DriverDetailsDto> getDriverDetails(String ownerId) {

        try{
            ResponseDto response = new ResponseDto();

            if(ownerId == null || ownerId.isEmpty())
            {
                response.setMessage("Id is Null");
            }
            List<DriverEntity> driverEntity = driverRepository.findAllByOwnerOwnerId(ownerId);

            List<DriverDetailsDto>driverDetailsDtos = new ArrayList<>();

                 for(DriverEntity driverEntities : driverEntity)
                 {
                     DriverDetailsDto driverDetailsDto = new DriverDetailsDto();
                     driverDetailsDto.setDriverId(driverEntities.getDriverId());
                     driverDetailsDto.setDriverName(driverEntities.getDriverName());
                     driverDetailsDto.setDriverMobileNumber(driverEntities.getDriverMobileNumber());
                     driverDetailsDto.setDriverNative(driverEntities.getDriverNative());
                     driverDetailsDto.setDriverTruck(driverEntities.getDriverTruck());

                     driverDetailsDtos.add(driverDetailsDto);
                 }

                 return driverDetailsDtos;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
