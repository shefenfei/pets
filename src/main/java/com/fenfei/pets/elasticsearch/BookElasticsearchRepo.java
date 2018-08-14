package com.fenfei.pets.elasticsearch;

import com.fenfei.pets.models.BookPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookElasticsearchRepo extends ElasticsearchRepository<BookPost, String> {

}
