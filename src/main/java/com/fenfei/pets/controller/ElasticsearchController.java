package com.fenfei.pets.controller;

import com.fenfei.pets.models.BookPost;
import com.fenfei.pets.models.UserPost;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/es")
@Slf4j
public class ElasticsearchController {

    @Autowired
    private ElasticsearchTemplate template;

    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    @ResponseBody
    public List<UserPost> search(String id) {
        QueryBuilder queryBuilder = new SimpleQueryStringBuilder(id);
        SearchQuery nativeSearchQueryBuilder
                = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return template.queryForList(nativeSearchQueryBuilder ,UserPost.class);
    }

    @RequestMapping(value = "/searchBookPost" , method = RequestMethod.GET)
    @ResponseBody
    public List<BookPost> searchBookPost(String bookid) {
        QueryBuilder queryBuilder = new SimpleQueryStringBuilder(bookid);
        SearchQuery nativeSearchQueryBuilder
                = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return template.queryForList(nativeSearchQueryBuilder ,BookPost.class);
    }
}
