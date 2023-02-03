package com.hiberus.clothing.infrastructure.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="clothing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GarmentEntity {

    @Id
    private String id;
    private String family;
    private String name;
    private String description;
    private float basePrice;



}
