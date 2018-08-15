package com.fenfei.pets.controller;

import com.fenfei.pets.elasticsearch.models.AccountInfo;
import com.fenfei.pets.elasticsearch.service.ESAccountInfoService;
import com.fenfei.pets.models.BookPost;
import com.fenfei.pets.models.UserPost;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/es")
@Slf4j
public class ElasticsearchController {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ESAccountInfoService accountInfoService;

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


    /**
     * 获取es数据
     * @param accountName
     * @return
     */
    @RequestMapping(value = "/esAccountInfoName" , method = RequestMethod.GET)
    public Map<String, Object> queryAccountInfoByAccountName(String accountName) {
        Map<String , Object> map = new HashMap<>();
        AccountInfo accountInfo = accountInfoService.queryAccountInfoName(accountName);
        map.put("data" , accountInfo);
        return map;
    }


    /**
     * 保存数据到es
     * @param account
     * @return
     */
    @RequestMapping(value = "/saveAccountInfo" , method = RequestMethod.POST)
    public Map<String , Object> saveAccountInfo(@RequestBody AccountInfo account) {
        Map<String , Object> map = new HashMap<>();
        AccountInfo accountInfo = accountInfoService.saveAccountInfo(account);
        map.put("data" , accountInfo);
        return map;
    }
}
