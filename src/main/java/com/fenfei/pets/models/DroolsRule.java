package com.fenfei.pets.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class DroolsRule implements Serializable {

    private String label;
    private String fieldName;
    private String type;
    private Boolean required;
    private Integer sequence;
    private Integer rows;
    private Integer minLength;
    private Integer maxLength;
    private String vtype;
    private String vtypeContent;
    private String vtypeText;
    private String defaultValue;
    private Boolean readOnly;
    private String rule;

}
