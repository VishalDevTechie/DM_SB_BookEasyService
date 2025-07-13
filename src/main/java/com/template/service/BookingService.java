package com.template.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.template.dto.BookingRequestDTO;
import com.template.dto.BookingStatus;
import com.template.dto.TimeRange;
import com.template.model.Booking;
import com.template.model.ServiceEntity;
import com.template.repository.BookingRepository;
import com.template.repository.ServiceRepository;
import com.template.repository.UserRepository;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PricingService pricingService;

	public Booking createBooking(BookingRequestDTO dto, String userId) {
		Optional<ServiceEntity> serviceOpt = serviceRepository.findById(dto.getServiceId());

		if (serviceOpt.isEmpty()) {
			throw new RuntimeException("Service not found");
		}

		ServiceEntity service = serviceOpt.get();

		// 1. Check for double booking
		boolean alreadyBooked = bookingRepository.existsByServiceIdAndDateAndTimeRange(dto.getServiceId(),
				dto.getDate(), dto.getTimeRange());

		if (alreadyBooked) {
			throw new RuntimeException("Time slot already booked");
		}

		// 2. Calculate price
		BigDecimal price = pricingService.calculatePrice(service, dto.getDate(), dto.getTimeRange());

		// 3. Create booking
		Booking booking = new Booking();
		booking.setUserId(userId);
		booking.setServiceId(dto.getServiceId());
		booking.setDate(dto.getDate());
		booking.setTimeRange(dto.getTimeRange());
		booking.setStatus(BookingStatus.CONFIRMED);
		booking.setPrice(price);
		
		return bookingRepository.save(booking);

	}

    public List<Booking> getUserBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
//	private boolean overlaps(TimeRange a, TimeRange b) {
//		return !(a.getEnd().isBefore(b.getStart()) || b.getEnd().isBefore(a.getStart()));
//	}
    
}
