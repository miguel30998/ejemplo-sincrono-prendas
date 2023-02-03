package com.hiberus.clothing.application.services;

import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.in.GetByIdUseCase;
import com.hiberus.clothing.domain.ports.out.GetGarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class GetByIdService implements GetByIdUseCase {

    private final GetGarmentPort getGarmentPort;

    @Override
    public Garment getGarment(String id){
       return getGarmentPort.getGarment(id);
    }
}
