package com.fenfei.pets.drools;

import com.fenfei.pets.models.CustomTemplate;

public interface DroolRuleService {

    String findRule(String messgae , Integer status);

    CustomTemplate queryById(int id);
}
