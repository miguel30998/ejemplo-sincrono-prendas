package com.hiberus.clothing.domain.ports.out;

import com.hiberus.clothing.domain.model.Garment;

import java.util.List;

public interface GetGarmentPort {

    Garment getGarment(String id);
    List<Garment> getClothingByFamily(String family);

    List<Garment> getAll();
}
