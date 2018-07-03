package com.fenfei.pets.controller;

import com.fenfei.pets.models.Book;
import com.fenfei.pets.service.IBookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
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

}
