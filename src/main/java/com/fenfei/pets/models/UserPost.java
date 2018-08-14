package com.fenfei.pets.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "username" , type = "post" , indexStoreType = "fs" , shards = 5, replicas = 1)
@Data
@ToString
public class UserPost {

    @Id
    private String userId;

    private String username;
    private String info;

}
