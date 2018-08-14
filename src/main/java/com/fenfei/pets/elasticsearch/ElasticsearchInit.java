package com.fenfei.pets.elasticsearch;

import com.fenfei.pets.models.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ElasticsearchInit {

    @Autowired
    private UserElasticsearchRepo elasticsearchRepo;

    @PostConstruct
    public void init() {
        Iterable<UserPost> userPosts = elasticsearchRepo.findAll();
        if (!userPosts.iterator().hasNext()) {
            for (int i = 0; i < 20; i++) {
                UserPost post = new UserPost();
                post.setUserId(String.valueOf(i));
                post.setUsername("用户" + i);
                post.setInfo("这是用户" + i + "的信息");
                elasticsearchRepo.save(post);
            }
        }
    }
}
