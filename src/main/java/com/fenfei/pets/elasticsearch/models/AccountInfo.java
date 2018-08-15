package com.fenfei.pets.elasticsearch.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 跟数据库的对应关系
 * 在ES中     index --- > DB
 *           type -----> table
 *           document ----> row
 */
@Document(indexName = "pets" ,
        type = "accountinfo" ,
        shards = 1, replicas = 0 ,
        refreshInterval = "-1")
@Data
public class AccountInfo {

    @Id
    private String id;

    @Field
    private String accountName;

    @Field
    private String nickName;


}
