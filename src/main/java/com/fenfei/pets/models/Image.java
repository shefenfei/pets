package com.fenfei.pets.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Image extends BasePetModel {

    @Id
    private String id;
    private String name;
}
