package com.fenfei.pets.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "test.books" , type = "book" , indexStoreType = "fs" , shards = 5, replicas = 1)
@Data
@ToString
public class BookPost {

    @Id
    private String bookid;

    private String bookname;
    private String authors;
    private String info;
    private String comment;
    private Date year_publish;

}
