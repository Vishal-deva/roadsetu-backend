package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.dto.TruckDetailsDto;
import com.RoadSetu.RoadSetu.entity.DriverEntity;
import com.RoadSetu.RoadSetu.entity.OwnerEntity;
import com.RoadSetu.RoadSetu.entity.TruckEntity;
import com.RoadSetu.RoadSetu.enums.DriverStatus;
import com.RoadSetu.RoadSetu.enums.TruckStatus;
import com.RoadSetu.RoadSetu.repository.DriverRepository;
import com.RoadSetu.RoadSetu.repository.OwnerRepository;
import com.RoadSetu.RoadSetu.repository.TruckRepository;
import com.RoadSetu.RoadSetu.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TruckServiceImpl implements TruckService {


    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private TruckRepository truckRepository;

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public ResponseDto saveTruckDetails(TruckDetailsDto truckDetailsDto) {

        ResponseDto responseDto = new ResponseDto();

        try {

            OwnerEntity ownerEntity = ownerRepository
                    .findById(truckDetailsDto.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Owner Not Found"));


            // CREATE TRUCK
            if (truckDetailsDto.getTruckId() == null ||
                    truckDetailsDto.getTruckId().isEmpty()) {


                TruckEntity truckEntity = new TruckEntity();

                truckEntity.setTruckName(truckDetailsDto.getTruckName());
                truckEntity.setTruckNumber(truckDetailsDto.getTruckNumber());
                truckEntity.setTruckType(truckDetailsDto.getTruckType());
                truckEntity.setFuelType(truckDetailsDto.getFuelType());
                truckEntity.setRcNumber(truckDetailsDto.getRcNumber());
                truckEntity.setTonCapacity(truckDetailsDto.getToneCapacity());
                truckEntity.setOwner(ownerEntity);

                // Default status
                truckEntity.setTruckStatus(TruckStatus.AVAILABLE);


                truckRepository.save(truckEntity);


                responseDto.setMessage("Truck Created Successfully");
                responseDto.setStatusCode(200);

            }


            // UPDATE TRUCK
            else {


                TruckEntity truckEntity = truckRepository
                        .findById(truckDetailsDto.getTruckId())
                        .orElseThrow(() ->
                                new RuntimeException("Truck Not Found"));


                // Owner validation
                if(!truckEntity.getOwner()
                        .getOwnerId()
                        .equals(truckDetailsDto.getOwnerId())) {

                    throw new RuntimeException(
                            "Truck does not belong to this owner"
                    );
                }



                // Update truck details

                truckEntity.setTruckName(truckDetailsDto.getTruckName());
                truckEntity.setTruckNumber(truckDetailsDto.getTruckNumber());
                truckEntity.setTruckType(truckDetailsDto.getTruckType());
                truckEntity.setFuelType(truckDetailsDto.getFuelType());
                truckEntity.setRcNumber(truckDetailsDto.getRcNumber());
                truckEntity.setTonCapacity(truckDetailsDto.getToneCapacity());
                truckEntity.setCurrentLocation(
                        truckDetailsDto.getCurrentLocation()
                );


                // Update truck status

                if(truckDetailsDto.getTruckStatus()!=null)
                {
                    truckEntity.setTruckStatus(
                            truckDetailsDto.getTruckStatus()
                    );
                }



                // Remove driver from truck

                if((truckDetailsDto.getDriverId()==null ||
                        truckDetailsDto.getDriverId().isEmpty())
                        &&
                        truckEntity.getDriver()!=null)
                {

                    DriverEntity oldDriver =
                            truckEntity.getDriver();

                    oldDriver.setDriverStatus(
                            DriverStatus.AVAILABLE
                    );

                    truckEntity.setTruckStatus(TruckStatus.AVAILABLE);
                    truckRepository.save(truckEntity);
                    driverRepository.save(oldDriver);
                    truckEntity.setDriver(null);
                }



                // Assign driver

                else if(truckDetailsDto.getDriverId()!=null &&
                        !truckDetailsDto.getDriverId().isEmpty())
                {
                    DriverEntity newDriver = driverRepository.findById(
                                            truckDetailsDto.getDriverId()
                                    )
                                    .orElseThrow(() ->
                                            new RuntimeException(
                                                    "Driver Not Found"
                                            ));

                    // Already assigned check
                    if(newDriver.getDriverStatus()
                            == DriverStatus.ASSIGNED
                            &&
                            (truckEntity.getDriver()==null ||
                                    !truckEntity.getDriver()
                                            .getDriverId()
                                            .equals(newDriver.getDriverId())))
                    {

                        throw new RuntimeException(
                                "Driver already assigned to another truck"
                        );
                    }



                    // Release old driver

                    if(truckEntity.getDriver()!=null &&
                            !truckEntity.getDriver()
                                    .getDriverId()
                                    .equals(newDriver.getDriverId()))
                    {

                        DriverEntity oldDriver =
                                truckEntity.getDriver();

                        oldDriver.setDriverStatus(
                                DriverStatus.AVAILABLE
                        );


                        driverRepository.save(oldDriver);
                    }



                    // Assign new driver

                    newDriver.setDriverStatus(
                            DriverStatus.ASSIGNED
                    );
                    truckEntity.setTruckStatus(TruckStatus.ON_TRIP);
                    driverRepository.save(newDriver);


                    truckEntity.setDriver(newDriver);

                }



                truckRepository.save(truckEntity);


                responseDto.setMessage(
                        "Truck Updated Successfully"
                );

                responseDto.setStatusCode(200);

            }


        } catch(Exception e){

            responseDto.setMessage(
                    "Error : " + e.getMessage()
            );

            responseDto.setStatusCode(500);
        }


        return responseDto;
    }





    @Override
    public List<TruckDetailsDto> getTruckDetails(String ownerId) {


        if(ownerId==null || ownerId.isEmpty())
        {
            throw new RuntimeException(
                    "Owner Id must not be null"
            );
        }


        List<TruckEntity> truckEntities =
                truckRepository.findAllByOwnerOwnerId(ownerId);



        List<TruckDetailsDto> truckDetailsDtoList =
                new ArrayList<>();


        for(TruckEntity truckEntity : truckEntities)
        {

            TruckDetailsDto dto =
                    new TruckDetailsDto();


            dto.setTruckId(
                    truckEntity.getTruckId()
            );

            dto.setTruckName(
                    truckEntity.getTruckName()
            );

            dto.setTruckNumber(
                    truckEntity.getTruckNumber()
            );

            dto.setTruckType(
                    truckEntity.getTruckType()
            );

            dto.setFuelType(
                    truckEntity.getFuelType()
            );

            dto.setRcNumber(
                    truckEntity.getRcNumber()
            );

            dto.setToneCapacity(
                    truckEntity.getTonCapacity()
            );


            dto.setOwnerId(
                    truckEntity.getOwner()
                            .getOwnerId()
            );


            dto.setTruckStatus(
                    truckEntity.getTruckStatus()
            );



            // Driver details

            if(truckEntity.getDriver()!=null)
            {

                dto.setDriverId(
                        truckEntity.getDriver()
                                .getDriverId()
                );


                dto.setDriverName(
                        truckEntity.getDriver()
                                .getDriverName()
                );


                dto.setDriverMobileNumber(
                        truckEntity.getDriver()
                                .getDriverMobileNumber()
                );

            }


            truckDetailsDtoList.add(dto);

        }


        return truckDetailsDtoList;

    }

    @Override
    public List<TruckDetailsDto> getAvailableTruck(String ownerId) {


        List<TruckEntity> trucks =
                truckRepository.findAllByOwnerOwnerIdAndTruckStatus(
                        ownerId,
                        TruckStatus.AVAILABLE
                );


        List<TruckDetailsDto> response =
                new ArrayList<>();


        for(TruckEntity truck : trucks)
        {

            TruckDetailsDto dto = new TruckDetailsDto();


            dto.setTruckId(
                    truck.getTruckId()
            );

            dto.setTruckName(
                    truck.getTruckName()
            );

            dto.setTruckNumber(
                    truck.getTruckNumber()
            );

            dto.setTruckStatus(
                    truck.getTruckStatus()
            );


            dto.setOwnerId(
                    truck.getOwner().getOwnerId()
            );


            response.add(dto);

        }


        return response;
    }

}