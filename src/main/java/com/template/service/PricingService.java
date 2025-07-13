package com.template.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.template.dto.TimeRange;
import com.template.model.ServiceEntity;

@Service
public class PricingService {

    public BigDecimal calculatePrice(ServiceEntity service, LocalDate date, TimeRange slot) {
        BigDecimal basePrice = service.getBasePrice();
        DayOfWeek day = date.getDayOfWeek();

        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return basePrice.multiply(BigDecimal.valueOf(1.5));

        }

        LocalTime start = slot.getStart();
        if (start.isBefore(LocalTime.of(8, 0)) || start.isAfter(LocalTime.of(20, 0))) {
            return basePrice.multiply(BigDecimal.valueOf(0.8));
        }

        return basePrice;
    }
}
