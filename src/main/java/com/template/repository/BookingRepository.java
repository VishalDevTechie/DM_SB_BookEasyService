package com.template.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.template.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
	
	List<Booking> findByServiceIdAndDate(String serviceId, LocalDate date);

	List<Booking> findByUserId(String userId);
}
