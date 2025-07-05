package com.template.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.dto.ServiceType;
import com.template.model.ServiceEntity;
import com.template.repository.ServiceRepository;

@Service
public class ServiceManagementService {

    @Autowired
    private ServiceRepository serviceRepository;
    
    public ServiceEntity createService(ServiceEntity service) {
        return serviceRepository.save(service);
    }
    
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }
    
    public Optional<ServiceEntity> getServiceById(String id) {
        return serviceRepository.findById(id);
    }
    
    public void deleteService(String id) {
        serviceRepository.deleteById(id);
    }
    
    public ServiceEntity updateService(String id, ServiceEntity updated) {
        updated.setId(id);
        return serviceRepository.save(updated);
    }
    
    public List<ServiceEntity> getServicesByType(ServiceType type) {
        return serviceRepository.findByType(type);
    }
}
