package com.hiberus.users.infrastructure.adapter.out.persistence;

import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.model.Size;
import com.hiberus.users.domain.ports.out.GarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GarmentPersistenceAdapter implements GarmentPort {

    private final GarmentRepository garmentRepository;
    private final GarmentMapper garmentMapper;

    @Override
    public boolean createGarment(Garment garment) {
        try{
            GarmentEntity entity = GarmentMapper.mapToEntityDomain(garment);
            garmentRepository.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteGarment(String name, Size size) {
        try{
            garmentRepository.deleteById(name+size);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Garment> getListClothing() {
        try{
            List<GarmentEntity> list = garmentRepository.findAll();
            return GarmentMapper.mapToDomainList(list);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Garment getGarment(String id) {
        try{
            GarmentEntity entity = garmentRepository.findById(id);
            return GarmentMapper.mapToDomainEntity(entity);
        }catch (Exception e){
            return null;
        }
    }
}
