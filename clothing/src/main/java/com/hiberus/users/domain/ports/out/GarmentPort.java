package com.hiberus.users.domain.ports.out;

import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.model.Size;

import java.util.List;

public interface GarmentPort {



    boolean createGarment(Garment garment);

    boolean deleteGarment(String name, Size size);

    List<Garment> getListClothing();

    Garment getGarment(String id);
}
