package com.hiberus.users.application.services;

import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.ports.in.UpgradeDowngradeQuantityUseCase;
import com.hiberus.users.domain.ports.out.GarmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UpgradeDowngradeQuantity implements UpgradeDowngradeQuantityUseCase {
    private final GarmentPort garmentPort;


    @Override
    public boolean upgrade(Garment garment) {
        garment.setQuantity(garment.getQuantity()+1);
        return garmentPort.createGarment(garment);
    }

    @Override
    public boolean downgrade(Garment garment) {
        garment.setQuantity(garment.getQuantity()-1);
        return garmentPort.createGarment(garment);
    }
}
