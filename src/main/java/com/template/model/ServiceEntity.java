package com.template.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.template.dto.AvailabilitySlot;
import com.template.dto.ServiceType;

@Document(collection = "services")
public class ServiceEntity {

	 @Id
	    private String id;
	    private String title;
	    private String description;
	    private BigDecimal basePrice;
	    private List<AvailabilitySlot> availabilitySlots;
	    private ServiceType type;
	    private String createdByUserId;

	    // Getters and setters

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public BigDecimal getBasePrice() {
	        return basePrice;
	    }

	    public void setBasePrice(BigDecimal basePrice) {
	        this.basePrice = basePrice;
	    }

	    public List<AvailabilitySlot> getAvailabilitySlots() {
	        return availabilitySlots;
	    }

	    public void setAvailabilitySlots(List<AvailabilitySlot> availabilitySlots) {
	        this.availabilitySlots = availabilitySlots;
	    }

	    public ServiceType getType() {
	        return type;
	    }

	    public void setType(ServiceType type) {
	        this.type = type;
	    }

	    public String getCreatedByUserId() {
	        return createdByUserId;
	    }

	    public void setCreatedByUserId(String createdByUserId) {
	        this.createdByUserId = createdByUserId;
	    }

}
