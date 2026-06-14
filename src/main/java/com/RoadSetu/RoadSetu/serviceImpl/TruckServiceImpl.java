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

    import javax.swing.text.html.Option;
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
        public TruckDetailsDto getTruckDetails(String ownerId) {

            TruckDetailsDto truckDetailsDto = new TruckDetailsDto();
            try{
                if(ownerId == null || ownerId.isEmpty())
                {
                    throw new RuntimeException("Id must not  be null");
                }
                Optional<TruckEntity> truckEntity = truckRepository.findByOwnerOwnerId(ownerId);
                if(truckEntity.isPresent())
                {
                    TruckEntity  truckEntity1 = truckEntity.get();
                    truckDetailsDto.setTruckId(truckEntity1.getTruckId());
                    truckDetailsDto.setTruckName(truckEntity1.getTruckName());
                    truckDetailsDto.setTruckType(truckEntity1.getTruckType());
                    truckDetailsDto.setRcNumber(truckEntity1.getRcNumber());
                    truckDetailsDto.setFuelType(truckEntity1.getFuelType());
                    truckDetailsDto.setTruckNumber(truckEntity1.getTruckNumber());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return truckDetailsDto;
        }
    }