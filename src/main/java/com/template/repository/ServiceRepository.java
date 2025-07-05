package com.template.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.template.dto.ServiceType;
import com.template.model.ServiceEntity;

public interface ServiceRepository extends MongoRepository<ServiceEntity, String> {
    List<ServiceEntity> findByType(ServiceType type);

}
