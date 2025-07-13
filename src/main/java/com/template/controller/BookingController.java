package com.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.template.dto.BookingRequestDTO;
import com.template.model.Booking;
import com.template.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Booking Controller", description = "Booking APIs for User and Admin")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@Operation(summary = "Create a booking", description = "Allows a customer to book a service slot.")
	@PostMapping
	public ResponseEntity<Booking> book(@RequestBody BookingRequestDTO dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

//		String userId = jwt.getSubject();
		Booking booking = bookingService.createBooking(dto, userId);
		return ResponseEntity.ok(booking);
	}

	@Operation(summary = "Get bookings", description = "Returns bookings for the user or all (if admin).")
	@GetMapping
	ResponseEntity<List<Booking>> getMyBookings() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();

		boolean isAdmin = hasRole(authentication, "ADMIN");

		if (isAdmin) {
			return ResponseEntity.ok(bookingService.getAllBookings());
		} else {
			return ResponseEntity.ok(bookingService.getUserBookings(userId));
		}

	}

	private boolean hasRole(Authentication auth, String role) {
		return auth.getAuthorities().stream().anyMatch(granted -> granted.getAuthority().equals("ROLE_" + role));
	}
}
