package com.hiberus.clothing.application.services;

import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.in.GetAllUseCase;
import com.hiberus.clothing.domain.ports.out.GetGarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GetAll implements GetAllUseCase {

    private final GetGarmentPort getGarmentPort;
    @Override
    public List<Garment> getAll() {
        return getGarmentPort.getAll();
    }
}
