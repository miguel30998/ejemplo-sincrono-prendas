package com.hiberus.clothing.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Garment {

    public static final String NOT_VALID_ARGUMENTS = "Not valid arguments";
    public static final String REGGEX = "^[R][E][F][-][0-9]{5}[-][0-9]{3}";
    private String id;
    private String product_family;
    private String name;
    private String description;
    private float basePrice;

    public Garment(String id, String product_family, String name, String description, float basePrice){
        if(!checkId(id)||id==null||product_family==null||name==null||description==null||basePrice<=0)
            throw new IllegalArgumentException(NOT_VALID_ARGUMENTS);
        this.id = id;
        this.product_family = product_family;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
    }


    private boolean checkId(String id){
        return id.matches(REGGEX);
    }

}
