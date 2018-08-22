package com.fenfei.pets.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomTemplate implements Serializable {

    private int id;
    private String content;
}
