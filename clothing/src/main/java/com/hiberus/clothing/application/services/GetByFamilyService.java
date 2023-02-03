package com.hiberus.clothing.application.services;

import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.in.GetByFamilyUseCase;
import com.hiberus.clothing.domain.ports.out.GetGarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GetByFamilyService implements GetByFamilyUseCase {

    private final GetGarmentPort getGarmentPort;

    @Override
    public List<Garment> getClothing(String family){
        return getGarmentPort.getClothingByFamily(family);
    }
}
