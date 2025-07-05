package com.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.dto.BookingRequestDTO;
import com.template.dto.TimeRange;
import com.template.model.Booking;
import com.template.repository.BookingRepository;
import com.template.repository.ServiceRepository;
import com.template.repository.UserRepository;

@Service
public class BookingService  {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

	Booking bookService(String userId, BookingRequestDTO request) {

		List<Booking> existingBookings = bookingRepository.findByServiceIdAndDate(request.getServiceId(),
				request.getDate());

		for (Booking booking : existingBookings) {
			if (overlaps(booking.getTimeRange(), request.getTimeRange())) {
				Booking bookingres = new Booking();
		        booking.setStatusMessage("Selected time slot is not available");
		        return bookingres;				
			}
		}
		return null;

	}

	private boolean overlaps(TimeRange a, TimeRange b) {
		return !(a.getEnd().isBefore(b.getStart()) || b.getEnd().isBefore(a.getStart()));
	}
}
