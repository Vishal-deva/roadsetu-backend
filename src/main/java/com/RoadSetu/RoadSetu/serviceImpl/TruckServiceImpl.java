    package com.RoadSetu.RoadSetu.serviceImpl;

    import com.RoadSetu.RoadSetu.dto.ResponseDto;
    import com.RoadSetu.RoadSetu.dto.TruckDetailsDto;
    import com.RoadSetu.RoadSetu.entity.OwnerEntity;
    import com.RoadSetu.RoadSetu.entity.TruckEntity;
    import com.RoadSetu.RoadSetu.repository.OwnerRepository;
    import com.RoadSetu.RoadSetu.repository.TruckRepository;
    import com.RoadSetu.RoadSetu.service.TruckService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;


    @Service
    public class TruckServiceImpl implements TruckService {


        @Autowired
        private OwnerRepository ownerRepository;

        @Autowired
        private TruckRepository truckRepository;

        @Override
        public ResponseDto saveTruckDetails(TruckDetailsDto truckDetailsDto) {

            ResponseDto responseDto = new ResponseDto();

            try {

                OwnerEntity ownerEntity = ownerRepository
                        .findById(truckDetailsDto.getOwnerId())
                        .orElseThrow(() -> new RuntimeException("Owner Not Found"));

                // CREATE CASE
                if (truckDetailsDto.getTruckId() == null || truckDetailsDto.getTruckId().isEmpty()) {

                    TruckEntity truckEntity = new TruckEntity();
                    truckEntity.setTruckName(truckDetailsDto.getTruckName());
                    truckEntity.setTruckType(truckDetailsDto.getTruckType());
                    truckEntity.setTruckNumber(truckDetailsDto.getTruckNumber());
                    truckEntity.setOwner(ownerEntity);
                    truckEntity.setFuelType(truckDetailsDto.getFuelType());
                    truckEntity.setRcNumber(truckDetailsDto.getRcNumber());
                    truckEntity.setTonCapacity(truckDetailsDto.getToneCapacity());

                    truckRepository.save(truckEntity);

                    responseDto.setMessage("Truck Created Successfully");
                    responseDto.setStatusCode(200);
                }

                // UPDATE CASE (optional but important)
                else {
                    TruckEntity truckEntity = truckRepository.findById(truckDetailsDto.getTruckId())
                            .orElseThrow(() -> new RuntimeException("Truck Not Found"));

                    truckEntity.setTruckName(truckDetailsDto.getTruckName());
                    truckEntity.setTruckType(truckDetailsDto.getTruckType());
                    truckEntity.setTruckNumber(truckDetailsDto.getTruckNumber());
                    truckEntity.setFuelType(truckDetailsDto.getFuelType());
                    truckEntity.setRcNumber(truckDetailsDto.getRcNumber());
                    truckEntity.setTonCapacity(truckDetailsDto.getToneCapacity());

                    truckRepository.save(truckEntity);

                    responseDto.setMessage("Truck Updated Successfully");
                    responseDto.setStatusCode(200);
                }

            } catch (Exception e) {
                responseDto.setMessage("Error: " + e.getMessage());
                responseDto.setStatusCode(500);
            }

            return responseDto;
        }

        @Override
        public List<TruckDetailsDto> getTruckDetails(String ownerId) {

            try {

                if (ownerId == null || ownerId.isEmpty()) {
                    throw new RuntimeException("Owner Id must not be null");
                }

                List<TruckEntity> truckEntities =
                        truckRepository.findAllByOwnerOwnerId(ownerId);

                List<TruckDetailsDto> truckDetailsDtoList = new ArrayList<>();

                for (TruckEntity truckEntity : truckEntities) {

                    TruckDetailsDto truckDetailsDto = new TruckDetailsDto();

                    truckDetailsDto.setTruckId(truckEntity.getTruckId());
                    truckDetailsDto.setTruckName(truckEntity.getTruckName());
                    truckDetailsDto.setTruckType(truckEntity.getTruckType());
                    truckDetailsDto.setTruckNumber(truckEntity.getTruckNumber());
                    truckDetailsDto.setFuelType(truckEntity.getFuelType());
                    truckDetailsDto.setRcNumber(truckEntity.getRcNumber());
                    truckDetailsDto.setToneCapacity(truckEntity.getTonCapacity());

                    truckDetailsDtoList.add(truckDetailsDto);
                }

                return truckDetailsDtoList;

            } catch (Exception e) {
                throw new RuntimeException("Error while fetching truck details: "
                        + e.getMessage());
            }
        }
    }