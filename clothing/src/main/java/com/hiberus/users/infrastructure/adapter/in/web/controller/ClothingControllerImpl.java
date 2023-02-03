package com.hiberus.users.infrastructure.adapter.in.web.controller;

import com.google.gson.Gson;
import com.hiberus.users.domain.model.Garment;
import com.hiberus.users.domain.model.Size;
import com.hiberus.users.domain.ports.in.*;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import com.hiberus.users.infrastructure.DTO.GarmentIdentifierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClothingControllerImpl implements ClothingController {

    public static final String DOESNT_EXIST = "Doesnt exist";
    private final CreateGarmentUseCase createGarmentUseCase;
    private final DeleteGarmentUseCase deleteGarmentUseCase;
    private final GetClothingUseCase getClothingUseCase;
    private final GetGarmentByIDUseCase getGarmentByIDUseCase;

    private final UpgradeDowngradeQuantityUseCase upgradeDowngradeQuantityUseCase;
    private Gson gson = new Gson();

    @Override
    public ResponseEntity<String> createGarment(GarmentDTO garmentDTO){
        Garment garment;
        try {
            garment = new Garment(garmentDTO.getName(),garmentDTO.getQuantity(),garmentDTO.getSize());
            if(getGarmentByIDUseCase.getGarment(garmentDTO.getName()+garmentDTO.getSize()) != null){
                return new ResponseEntity<>(gson.toJson("Already exists"), HttpStatus.NOT_FOUND);
            }
            createGarmentUseCase.createGarment(garment);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteGarment(GarmentIdentifierDTO garmentDTO){
        try {
            Garment garment = getGarmentByIDUseCase.getGarment(garmentDTO.getName()+garmentDTO.getSize());
            if(garment == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            deleteGarmentUseCase.deleteGarment(garmentDTO.getName(),garmentDTO.getSize());
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson("Deleted "+garmentDTO.getName()+" Size: "+garmentDTO.getSize()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getClothing() {
        List<Garment> list;
        try {
            list = getClothingUseCase.getClothing();
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(list), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getClothing(String name, Size size) {
        Garment garment;
        try {
            garment = getGarmentByIDUseCase.getGarment(name+size);
            if(garment == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> upgradeQuantity(GarmentIdentifierDTO garmentIdentifierDTO) {
        Garment garment;
        boolean succesfull;
        try {
            garment = getGarmentByIDUseCase.getGarment(garmentIdentifierDTO.getName()+garmentIdentifierDTO.getSize());
            if(garment == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            succesfull = upgradeDowngradeQuantityUseCase.upgrade(garment);
            if(!succesfull){
                return new ResponseEntity<>(gson.toJson("Error upgrading"), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> downgradeQuantity(GarmentIdentifierDTO garmentIdentifierDTO) {
        Garment garment;
        boolean succesfull;
        try {
            garment = getGarmentByIDUseCase.getGarment(garmentIdentifierDTO.getName()+garmentIdentifierDTO.getSize());
            if(garment == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            if(garment.getQuantity() == 0){
                return new ResponseEntity<>(gson.toJson("Not enough"), HttpStatus.BAD_REQUEST);
            }
            succesfull = upgradeDowngradeQuantityUseCase.downgrade(garment);
            if(!succesfull){
                return new ResponseEntity<>(gson.toJson("Error upgrading"), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> buy(String garmentIdentifier) {
        Garment garment;
        boolean succesfull;
        try {
            garment = getGarmentByIDUseCase.getGarment(garmentIdentifier);
            if(garment == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            if(garment.getQuantity() == 0){
                return new ResponseEntity<>(gson.toJson("Not enough"), HttpStatus.BAD_REQUEST);
            }
            succesfull = upgradeDowngradeQuantityUseCase.downgrade(garment);
            if(!succesfull){
                return new ResponseEntity<>(gson.toJson("Error upgrading"), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(garment), HttpStatus.OK);
    }
}
