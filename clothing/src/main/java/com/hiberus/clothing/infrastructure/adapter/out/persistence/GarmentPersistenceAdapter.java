package com.hiberus.clothing.infrastructure.adapter.out.persistence;

import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.out.GetGarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GarmentPersistenceAdapter implements GetGarmentPort {

    private final GarmentRepository garmentRepository;
    private final GarmentMapper garmentMapper;
    @Override
    public Garment getGarment(String id) {
        GarmentEntity entity = garmentRepository.getById(id);
        if(entity!=null)
            return garmentMapper.mapToDomainEntity(entity);
        else
            return null;
    }

    @Override
    public List<Garment> getClothingByFamily(String family) {
        List<GarmentEntity> entities = garmentRepository.getByFamily(family);
        return garmentMapper.mapToDomainList(entities);
    }

    @Override
    public List<Garment> getAll() {
        return garmentMapper.mapToDomainList(garmentRepository.findAll());
    }
}
