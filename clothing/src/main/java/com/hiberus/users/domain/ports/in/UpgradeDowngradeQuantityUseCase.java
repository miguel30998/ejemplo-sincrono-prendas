package com.hiberus.users.domain.ports.in;

import com.hiberus.users.domain.model.Garment;

public interface UpgradeDowngradeQuantityUseCase {

    boolean upgrade(Garment garment);
    boolean downgrade(Garment garment);
}
