package com.example.rentcar.endpoint;

import com.example.rentcar.dto.CustomerDto;
import com.example.rentcar.dto.SaveCustomerRequest;
import com.example.rentcar.dto.SaveVehicleRequest;
import com.example.rentcar.dto.VehicleDto;
import com.example.rentcar.repository.VehicleRepository;
import com.example.rentcar.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VehicleEndpoint {

    private final VehicleService vehicleService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> addVehicle(@RequestBody @Valid SaveVehicleRequest vehicleRequest,
                                                 @RequestParam("image") MultipartFile multipartFile) {
        return ResponseEntity.ok(vehicleService.save(vehicleRequest, multipartFile));
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") int id) {
        vehicleService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/vehicles")
    public ResponseEntity<VehicleDto> updateBook(@RequestBody SaveVehicleRequest vehicleRequest,
                                                  @RequestParam("image") MultipartFile multipartFile) {
        return ResponseEntity.ok(vehicleService.update(vehicleRequest, multipartFile));
    }

}