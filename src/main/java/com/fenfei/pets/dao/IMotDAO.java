package com.fenfei.pets.dao;

import com.fenfei.pets.models.CustomTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMotDAO {

    CustomTemplate queryTemplateByEventId(@Param("id") Integer id);
}
