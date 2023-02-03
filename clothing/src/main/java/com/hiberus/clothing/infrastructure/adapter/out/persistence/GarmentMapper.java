package com.hiberus.clothing.infrastructure.adapter.out.persistence;

import com.hiberus.clothing.domain.model.Garment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GarmentMapper {

    static Garment mapToDomainEntity(GarmentEntity entity) {
        return new Garment(entity.getId(),entity.getFamily(),entity.getName(),entity.getDescription(),entity.getBasePrice());
    }

    static List<Garment> mapToDomainList(List<GarmentEntity> entities){
        List<Garment> list = new ArrayList<>();

        for(GarmentEntity entity: entities){
            list.add(mapToDomainEntity(entity));
        }
        return list;
    }
}
