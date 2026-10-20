package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.DriverDetailsDto;
import com.RoadSetu.RoadSetu.dto.LoginDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import com.RoadSetu.RoadSetu.entity.TruckEntity;
import com.RoadSetu.RoadSetu.enums.DriverStatus;
import com.RoadSetu.RoadSetu.enums.TruckStatus;
import com.RoadSetu.RoadSetu.repository.DriverRepository;
import com.RoadSetu.RoadSetu.repository.OwnerRepository;
import com.RoadSetu.RoadSetu.repository.TruckRepository;
import com.RoadSetu.RoadSetu.service.DriverService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TruckRepository truckRepository;


    @Transactional
    @Override
    public ResponseDto saveDriverDetails(DriverDetailsDto driverDetailsDto) {

        ResponseDto response = new ResponseDto();

        // 1. Create a new driver
        if (driverDetailsDto.getDriverId() == null
                || driverDetailsDto.getDriverId().isEmpty()) {

            OwnerEntity owner = ownerRepository
                    .findById(driverDetailsDto.getOwnerId())
                    .orElseThrow(() ->
                            new RuntimeException("Owner Not Found"));

            DriverEntity driverEntity = new DriverEntity();

            driverEntity.setDriverName(driverDetailsDto.getDriverName());
            driverEntity.setDriverEmailId(driverDetailsDto.getDriverEmailId());
            driverEntity.setPassword(driverDetailsDto.getPassword());
            driverEntity.setDriverMobileNumber(
                    driverDetailsDto.getDriverMobileNumber());
            driverEntity.setDriverLicense(
                    driverDetailsDto.getDriverLicense());
            driverEntity.setDriverNative(driverDetailsDto.getDriverNative());
            driverEntity.setRole("DRIVER");
            driverEntity.setOwner(owner);
            driverEntity.setDriverStatus(DriverStatus.AVAILABLE);

            driverRepository.save(driverEntity);

            response.setStatusCode(200);
            response.setMessage("Driver Created Successfully");

            return response;
        }

        // 2. Find existing driver
        DriverEntity driverEntity = driverRepository
                .findByDriverId(driverDetailsDto.getDriverId())
                .orElseThrow(() ->
                        new RuntimeException("Driver Not Found"));

        // 3. Update basic driver details
        driverEntity.setDriverName(driverDetailsDto.getDriverName());
        driverEntity.setDriverMobileNumber(
                driverDetailsDto.getDriverMobileNumber());
        driverEntity.setDriverLicense(
                driverDetailsDto.getDriverLicense());
        driverEntity.setDriverNative(driverDetailsDto.getDriverNative());

        // 4. If no truck was selected, update profile only
        if (driverDetailsDto.getTruckId() == null
                || driverDetailsDto.getTruckId().isEmpty()) {

            driverRepository.save(driverEntity);

            response.setStatusCode(200);
            response.setMessage("Driver Details Updated Successfully");

            return response;
        }

        // 5. Find selected truck
        TruckEntity truck = truckRepository
                .findById(driverDetailsDto.getTruckId())
                .orElseThrow(() ->
                        new RuntimeException("Truck Not Found"));

        // 6. Validate owner
        if (!driverEntity.getOwner().getOwnerId()
                .equals(truck.getOwner().getOwnerId())) {

            throw new RuntimeException(
                    "Driver and truck must belong to the same owner");
        }

        // 7. Check whether truck is already mapped to another driver
        if (truck.getDriver() != null
                && !truck.getDriver().getDriverId()
                .equals(driverEntity.getDriverId())) {

            throw new RuntimeException(
                    "This truck is already mapped to another driver.");
        }

        // 8. Check whether driver is already mapped to another truck
        Optional<TruckEntity> existingTruck =
                truckRepository.findByDriver_DriverId(
                        driverEntity.getDriverId());

        if (existingTruck.isPresent()
                && !existingTruck.get().getTruckId()
                .equals(truck.getTruckId())) {

            throw new RuntimeException(
                    "This driver is already mapped to another truck.");
        }

        // 9. If already mapped to this truck, allow profile update
        if (existingTruck.isPresent()
                && existingTruck.get().getTruckId()
                .equals(truck.getTruckId())) {

            driverRepository.save(driverEntity);

            response.setStatusCode(200);
            response.setMessage(
                    "Driver details updated successfully. Existing truck assignment retained.");

            return response;
        }

        // 10. Validate driver availability for a NEW assignment
        if (driverEntity.getDriverStatus() != DriverStatus.AVAILABLE) {

            throw new RuntimeException(
                    "This driver is not available for assignment.");
        }

        // 11. Validate truck status for a NEW assignment
        if (truck.getTruckStatus() != TruckStatus.EMPTY) {

            throw new RuntimeException(
                    "This truck is not empty and cannot be assigned.");
        }

        // 12. Map driver to truck
        truck.setDriver(driverEntity);
        driverEntity.setDriverStatus(DriverStatus.ASSIGNED);

        truckRepository.save(truck);
        driverRepository.save(driverEntity);

        response.setStatusCode(200);
        response.setMessage("Driver assigned to truck successfully");

        return response;
    }

    @Override
    public List<DriverDetailsDto> getDriverDetails(String ownerId) {

        try {
            ResponseDto response = new ResponseDto();

            if (ownerId == null || ownerId.isEmpty()) {
                response.setMessage("Id is Null");
            }
            List<DriverEntity> driverEntity = driverRepository.findAllByOwnerOwnerId(ownerId);

            List<DriverDetailsDto> driverDetailsDtos = new ArrayList<>();

            for (DriverEntity driverEntities : driverEntity) {
                DriverDetailsDto driverDetailsDto = new DriverDetailsDto();
                driverDetailsDto.setDriverLicense(driverEntities.getDriverLicense());
                driverDetailsDto.setOwnerId(driverEntities.getOwner().getOwnerId());
                driverDetailsDto.setDriverId(driverEntities.getDriverId());
                driverDetailsDto.setDriverName(driverEntities.getDriverName());
                driverDetailsDto.setDriverMobileNumber(driverEntities.getDriverMobileNumber());
                driverDetailsDto.setDriverNative(driverEntities.getDriverNative());
                driverDetailsDtos.add(driverDetailsDto);
            }

            return driverDetailsDtos;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DriverDetailsDto> getAvailableDriver(String ownerId) {

        if (ownerId == null || ownerId.isEmpty()) {
            throw new RuntimeException("Owner Id must not be null");
        }

        List<DriverEntity> driverEntities =
                driverRepository.findAllByOwnerOwnerIdAndDriverStatus(
                        ownerId,
                        DriverStatus.AVAILABLE);

        List<DriverDetailsDto> driverDetailsDtos = new ArrayList<>();

        for (DriverEntity driverEntity : driverEntities) {

            DriverDetailsDto driverDetailsDto = new DriverDetailsDto();

            driverDetailsDto.setDriverId(driverEntity.getDriverId());
            driverDetailsDto.setOwnerId(driverEntity.getOwner().getOwnerId());
            driverDetailsDto.setDriverName(driverEntity.getDriverName());
            driverDetailsDto.setDriverLicense(driverEntity.getDriverLicense());
            driverDetailsDto.setDriverMobileNumber(driverEntity.getDriverMobileNumber());
            driverDetailsDto.setDriverNative(driverEntity.getDriverNative());

            driverDetailsDtos.add(driverDetailsDto);
        }

        return driverDetailsDtos;
    }
    @Override
    public ResponseDto driverLogin(
            LoginDto loginDto) {


        ResponseDto response =
                new ResponseDto();



        try{


            DriverEntity driver =
                    driverRepository
                            .findByDriverEmailId(
                                    loginDto.getEmail()
                            )
                            .orElseThrow(
                                    ()->new RuntimeException(
                                            "Driver Not Found"
                                    )
                            );




            if(driver.getPassword()==null ||
                    !driver.getPassword()
                            .equals(loginDto.getPassword())){


                response.setStatusCode(401);

                response.setMessage(
                        "Invalid email or password"
                );

                return response;
            }
            response.setStatusCode(200);

            response.setMessage(
                    "Login Successfully"
            );

            response.setOwnerId(driver.getOwner().getOwnerId());
            response.setDriverId(driver.getDriverId());

            response.setRole(
                    "DRIVER"
            );

        }
        catch(Exception e){


            response.setStatusCode(500);

            response.setMessage(
                    e.getMessage()
            );

        }
        return response;

    }

    @Override
    public DriverDetailsDto getDriverProfile(String driverId) {
        try{

            DriverDetailsDto driverDetailsDto = new DriverDetailsDto();
            Optional<DriverEntity>driverEntity = driverRepository.findByDriverId(driverId);

            if(driverEntity.isPresent())
            {
                DriverEntity driver = driverEntity.get();

                driverDetailsDto.setDriverEmailId(driver.getDriverEmailId());
                driverDetailsDto.setDriverMobileNumber(driver.getDriverMobileNumber());
                driverDetailsDto.setDriverName(driver.getDriverName());
                driverDetailsDto.setDriverLicense(driver.getDriverLicense());


                Optional<TruckEntity>truckEntity = truckRepository.findByDriver_DriverId(driverId);

                if(truckEntity.isPresent())
                {
                    TruckEntity truck = truckEntity.get();

                    driverDetailsDto.setTruckId(truck.getTruckId());
                    driverDetailsDto.setTruckNumber(truck.getTruckNumber());
                }
                else {
                    driverDetailsDto.setTruckId(null);
                    driverDetailsDto.setTruckNumber("Currently no truck assigned to you");
                }
            }
            return driverDetailsDto;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
