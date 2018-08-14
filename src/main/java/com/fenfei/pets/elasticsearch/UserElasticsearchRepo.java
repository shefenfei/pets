package com.fenfei.pets.elasticsearch;

import com.fenfei.pets.models.UserPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserElasticsearchRepo extends ElasticsearchRepository<UserPost , String> {

}
