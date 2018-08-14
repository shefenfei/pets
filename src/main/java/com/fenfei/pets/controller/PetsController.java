package com.fenfei.pets.controller;

import com.fenfei.pets.clients.PetClient;
import com.fenfei.pets.configs.UserProperties;
import com.fenfei.pets.dao.ReadingListRepository;
import com.fenfei.pets.models.Book;
import com.fenfei.pets.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.MapperListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/pets")
@Slf4j
public class PetsController {

    @Autowired
    private PetClient petClient;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ReadingListRepository repository;

    volatile String name;
    transient String name1;

    @Resource
    private UserProperties userProperties;

    @Resource
    private IBookService iBookService;

    @PostMapping("/getAll")
    @ResponseBody
    public Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", "ok");
        String resp = petClient.labelByMemberId("134680196341989367");
        System.out.println(resp);
        return map;
    }

    public Map<String, Object> addPets() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("1001", "eventName", "入场");
        hashOperations.put("1002", "eventName", "aaa");

        hashOperations.get("1001" , "eventName");
        MapperListener mapperListener;
        return null;
    }


    @GetMapping("/del")
    @ResponseBody
    public Map<String, Object> del() {
        Map<String, Object> resp = new HashMap<>();
        Set<String> keys = redisTemplate.keys("*");
        resp.put("data", keys);
        return resp;
    }


    @GetMapping("/delGroup")
    @ResponseBody
    public Map<String, Object> delGroup() {
        Map<String, Object> resp = new HashMap<>();

        Set<String> keys = redisTemplate.keys("events:*");

        redisTemplate.delete(keys);

        Set<String> keys1 = redisTemplate.keys("events:*");
        resp.put("data", keys1);

        return resp;
    }

    @GetMapping("/books")
    @ResponseBody
    public Map<String, Object> readingBookList() {
        String password = userProperties.getPassword();
        String username = userProperties.getUsername();
        String info = userProperties.getInfo();
        String profile = userProperties.getProfile();
        String dev_fileter = userProperties.getEnv();

        log.info("用户名 = > {}", username);
        log.info("密码 = > {}", password);
        log.info("信息 = > {}", info);
        log.info("环境 = > {}", profile);
        log.info("filter = > {}", dev_fileter);

        Map<String, Object> resp = new HashMap<>();
        List<Book> books = iBookService.bookList("shefenfei");
//        List<Book> books = bookService.findBookByAuthor("shefenfei");
        resp.put("data", books);
        return resp;
    }

    @GetMapping("/addBook")
    @ResponseBody
    public Map<String, Object> addBook() {
        Map<String, Object> resp = new HashMap<>();
        Book book = new Book();
        book.setAuthor("shefenfei");
        book.setDescription("好书");
        book.setId(1l);
        book.setName("java编程思想");


//        Book save = bookService.save(book);

//        resp.put("data" , save);
        log.info("PET Controller 打印 : {}" , "打印PetController 日志");
        return resp;
    }

}
