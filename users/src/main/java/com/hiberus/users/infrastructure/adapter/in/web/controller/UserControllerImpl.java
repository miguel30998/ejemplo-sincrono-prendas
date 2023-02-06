package com.hiberus.users.infrastructure.adapter.in.web.controller;

import com.google.gson.Gson;
import com.hiberus.users.domain.model.User;
import com.hiberus.users.domain.ports.in.*;
import com.hiberus.users.infrastructure.DTO.GarmentDTO;
import com.hiberus.users.infrastructure.DTO.PurchasesDTO;
import com.hiberus.users.infrastructure.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    public static final String DOESNT_EXIST = "Doesnt exist";
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUsersUseCase;
    private final GetUserByIDUseCase getUserByIDUseCase;

    private final UpdateNameUseCase updateNameUseCase;

    private final GetClothUseCase getClothUseCase;
    private Gson gson = new Gson();

    @Override
    public ResponseEntity<String> createUser(UserDTO userDTO){
        User user;
        try {
            user = new User(userDTO.getDni(),userDTO.getName());
            if(getUserByIDUseCase.getUser(userDTO.getDni()) != null){
                return new ResponseEntity<>(gson.toJson("Already exists"), HttpStatus.NOT_FOUND);
            }
            createUserUseCase.createUser(user);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteUser(String id){
        try {
            User user = getUserByIDUseCase.getUser(id);
            if(user == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            deleteUserUseCase.deleteUser(id);

        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(gson.toJson("Deleted "+id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getUsers() {
        List<User> list;
        try {
            list = getUsersUseCase.getUsers();
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(list), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getUserById(String id) {
        User user;
        try {
            user = getUserByIDUseCase.getUser(id);
            if(user == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateName(UserDTO userDTO) {
        User user;
        boolean succesfull;
        try {
            user = getUserByIDUseCase.getUser(userDTO.getDni());
            if(user == null){
                return new ResponseEntity<>(gson.toJson("Not found"), HttpStatus.NOT_FOUND);
            }
            succesfull = updateNameUseCase.updateName(user,userDTO.getName());
            if(!succesfull){
                return new ResponseEntity<>(gson.toJson("Error updating"), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<String> buy(PurchasesDTO purchasesDTO) {
        User user;
        GarmentDTO garmentDTO;
        try {
            user = getUserByIDUseCase.getUser(purchasesDTO.getDni());
            if(user == null){
                return new ResponseEntity<>(gson.toJson("User does not exist"), HttpStatus.NOT_FOUND);
            }
            garmentDTO= getClothUseCase.getCloth(purchasesDTO.getClothId());
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gson.toJson(garmentDTO), HttpStatus.OK);
    }

}
