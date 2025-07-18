package com.example.rentcar.service;

import com.example.rentcar.dto.BookingDto;
import com.example.rentcar.dto.CustomerDto;
import com.example.rentcar.dto.SaveBookingRequest;
import com.example.rentcar.dto.SaveCustomerRequest;

import java.util.List;

public interface BookingService {

    List<BookingDto> findAll();

    BookingDto save(SaveBookingRequest bookingRequest);

    void deleteById(int id);

    BookingDto update(SaveBookingRequest bookingRequest);
}
