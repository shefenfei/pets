package com.fenfei.pets.models.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserVo {
    @Id
    private String userId;

    private String username;
    private String info;
}
