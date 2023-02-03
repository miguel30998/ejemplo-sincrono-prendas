package com.hiberus.clothing.infrastructure.adapter.in.web.controller;

import com.google.gson.Gson;
import com.hiberus.clothing.domain.model.Garment;
import com.hiberus.clothing.domain.ports.in.GetAllUseCase;
import com.hiberus.clothing.domain.ports.in.GetByFamilyUseCase;
import com.hiberus.clothing.domain.ports.in.GetByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClothingControllerImpl implements ClothingController {

    public static final String DOESNT_EXIST = "Doesnt exist";
    private final GetByIdUseCase getByIdUseCase;
    private final GetByFamilyUseCase getByFamilyUseCase;
    private final GetAllUseCase getAllUseCase;


    private Gson gson = new Gson();

    @Override
    public ResponseEntity<String> getGarmentById(String id){
        Garment garment;
        try {
            garment = getByIdUseCase.getGarment(id);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if(garment==null){
            return new ResponseEntity<>(DOESNT_EXIST, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getGarmentByFamily(String family){
        List<Garment> listClothing;
        try{
            listClothing = getByFamilyUseCase.getClothing(family);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(listClothing), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getClothing() {
        List<Garment> listClothing;
        try{
            listClothing = getAllUseCase.getAll();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(listClothing), HttpStatus.OK);
    }
}
