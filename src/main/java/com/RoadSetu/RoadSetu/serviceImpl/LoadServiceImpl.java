
package com.RoadSetu.RoadSetu.serviceImpl;

import com.RoadSetu.RoadSetu.dto.LoadDto;
import com.RoadSetu.RoadSetu.dto.ResponseDto;
import com.RoadSetu.RoadSetu.entity.CompanyEntity;
import com.RoadSetu.RoadSetu.entity.LoadEntity;
import com.RoadSetu.RoadSetu.repository.CompanyRepository;
import com.RoadSetu.RoadSetu.repository.LoadRepository;
import com.RoadSetu.RoadSetu.service.LoadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Override
    public ResponseDto saveLoadDetails(LoadDto loadDto) {

        ResponseDto responseDto = new ResponseDto();

        try {
            LoadEntity loadEntity = new LoadEntity();

            if (loadDto.getCompanyId() == null
                    || loadDto.getCompanyId().isBlank()) {

                responseDto.setStatusCode(400);
                responseDto.setMessage("Company ID is required");
                return responseDto;
            }

            Optional<CompanyEntity> optionalCompany =
                    companyRepository.findById(loadDto.getCompanyId());

            if (optionalCompany.isEmpty()) {

                responseDto.setStatusCode(404);
                responseDto.setMessage("Company not found");
                return responseDto;
            }

            if (loadDto.getLoadId() != null
                    && !loadDto.getLoadId().isBlank()) {


                Optional<LoadEntity>load = loadRepository.findById(loadDto.getLoadId());

                if (load.isEmpty()) {
                    responseDto.setStatusCode(404);
                    responseDto.setMessage("Load not found");
                    return responseDto;
                }


                LoadEntity load1 = load.get();

                if (!loadDto.getCompanyId().equals(load1.getCompanyId()))
                {
                    responseDto.setStatusCode(403);
                    responseDto.setMessage(
                            "This load does not belong to this company");
                    return responseDto;

                }
                else {
                    load1.setLoadStatus(loadDto.getLoadStatus());
                    LoadEntity savedLoad = loadRepository.save(load1);
                    responseDto.setStatusCode(200);
                    responseDto.setMessage("Load details Updated successfully");
                }

            }

            else {

                loadEntity.setCompanyId(loadDto.getCompanyId());
                loadEntity.setLoadType(loadDto.getLoadType());
                loadEntity.setLoadPrice(loadDto.getLoadPrice());
                loadEntity.setAddress(loadDto.getAddress());
                loadEntity.setLoadWeightTonnes(loadDto.getLoadWeightTonnes());
                loadEntity.setLoadPickupLocation(loadDto.getLoadPickupLocation());
                loadEntity.setLoadDropLocation(loadDto.getLoadDropLocation());
                loadEntity.setLoadStatus("AVAILABLE");

                LoadEntity savedLoad = loadRepository.save(loadEntity);

                responseDto.setStatusCode(200);
                responseDto.setMessage("Load details saved successfully");
            }

        } catch (Exception e) {

            responseDto.setStatusCode(500);
            responseDto.setMessage("Failed to save load details");

        }

        return responseDto;
    }


    @Override
    public List<LoadDto> getLoadByCompany(String companyId) {

        if (companyId == null || companyId.isBlank()) {
            throw new IllegalArgumentException("Company ID is required");
        }
        Optional<CompanyEntity> optionalCompany =
                companyRepository.findById(companyId);

        if (optionalCompany.isEmpty()) {
            throw new RuntimeException("Company not found");
        }

        List<LoadEntity> loadEntities =
                loadRepository.findByCompanyId(companyId);

        List<LoadDto> loadDtos = new ArrayList<>();

        for (LoadEntity loadEntity : loadEntities) {

            LoadDto loadDto = new LoadDto();

            loadDto.setLoadId(loadEntity.getLoadId());
            loadDto.setCompanyId(loadEntity.getCompanyId());
            loadDto.setLoadType(loadEntity.getLoadType());
            loadDto.setLoadPrice(loadEntity.getLoadPrice());
            loadDto.setAddress(loadEntity.getAddress());
            loadDto.setLoadWeightTonnes(
                    loadEntity.getLoadWeightTonnes());
            loadDto.setLoadPickupLocation(
                    loadEntity.getLoadPickupLocation());
            loadDto.setLoadDropLocation(
                    loadEntity.getLoadDropLocation());
            loadDto.setLoadStatus(loadEntity.getLoadStatus());

            loadDtos.add(loadDto);
        }

        return loadDtos;
    }


    @Override
    public List<LoadDto> getAllLoad() {

        List<LoadEntity> loadEntities = loadRepository.findAll();
        List<LoadDto> loadDtos = new ArrayList<>();

        for (LoadEntity loadEntity : loadEntities) {

            LoadDto loadDto = new LoadDto();

            loadDto.setLoadId(loadEntity.getLoadId());
            loadDto.setCompanyId(loadEntity.getCompanyId());
            loadDto.setLoadType(loadEntity.getLoadType());
            loadDto.setLoadPrice(loadEntity.getLoadPrice());
            loadDto.setAddress(loadEntity.getAddress());
            loadDto.setLoadWeightTonnes(
                    loadEntity.getLoadWeightTonnes());
            loadDto.setLoadPickupLocation(
                    loadEntity.getLoadPickupLocation());
            loadDto.setLoadDropLocation(
                    loadEntity.getLoadDropLocation());
            loadDto.setLoadStatus(loadEntity.getLoadStatus());

            loadDtos.add(loadDto);
        }

        return loadDtos;
    }


}
