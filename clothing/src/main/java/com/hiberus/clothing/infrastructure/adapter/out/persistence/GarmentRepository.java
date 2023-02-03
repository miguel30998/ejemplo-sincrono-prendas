package com.hiberus.clothing.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarmentRepository extends JpaRepository<GarmentEntity, Long> {
    GarmentEntity getById(String id);
    List<GarmentEntity> getByFamily(String family);

}
