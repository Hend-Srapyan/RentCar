package com.example.rentcar.service.impl;

import com.example.rentcar.dto.SaveCustomerRequest;
import com.example.rentcar.dto.SaveVehicleRequest;
import com.example.rentcar.dto.VehicleDto;
import com.example.rentcar.entity.Customer;
import com.example.rentcar.entity.Vehicle;
import com.example.rentcar.exception.CustomerNotFoundException;
import com.example.rentcar.exception.VehicleNotFoundException;
import com.example.rentcar.mapper.VehicleMapper;
import com.example.rentcar.repository.VehicleRepository;
import com.example.rentcar.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Value("C://Users//Hend//IdeaProjects//rentcar//upload")
    private String uploadPath;

    @Override
    public List<VehicleDto> findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicleMapper.toDtoList(vehicles);
    }

    @SneakyThrows
    @Override
    public VehicleDto save(SaveVehicleRequest vehicleRequest, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            vehicleRequest.setImage(fileName);
        }
        return vehicleMapper.toDto(vehicleRepository.save(vehicleMapper.toEntity(vehicleRequest)));
    }

    @Override
    public void deleteById(int id) {

        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException("Vehicle not found with " + id + " id");
        }
        vehicleRepository.deleteById(id);
    }

    @SneakyThrows
    @Override
    public VehicleDto update(SaveVehicleRequest vehicleRequest, MultipartFile multipartFile) {
        String fileName;
        if(!multipartFile.isEmpty()){
            fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadPath, fileName);
            multipartFile.transferTo(file);
            vehicleRequest.setImage(fileName);
        }
        return vehicleMapper.toDto(vehicleRepository.save(vehicleMapper.toEntity(vehicleRequest)));
    }
}
