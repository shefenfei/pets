package com.fenfei.pets.controller;

import com.fenfei.pets.models.Book;
import com.fenfei.pets.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private IBookService bookService;

    @GetMapping("/get")
    @ResponseBody
    public Map<String , Object> getAll() {
        Map<String , Object> map = new HashMap<>();
        map.put("success" , "ok");
        List<Book> books = bookService.bookList("shefenfei");
        map.put("data", books);
        return map;
    }


    @PostMapping("/getAllBooks")
    @ResponseBody
    public Map<String , Object> getAllBooks() {
        Map<String , Object> map = new HashMap<>();
        List<Book> books = bookService.bookList("shefenfei");
        map.put("data", books);
        return map;
    }


    @PostMapping("/getLogback")
    @ResponseBody
    public Map<String , Object> testLogback() {
        Map<String , Object> map = new HashMap<>();
        map.put("data", "success");
        log.error("出错了要打印 : {}" , log.isErrorEnabled());
        log.info("打印info信息打印 : {}" , log.isInfoEnabled());
        log.info("test logger : {}" , map);
        return map;
    }

}
