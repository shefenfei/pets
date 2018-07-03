package com.fenfei.pets.service.impl;

import com.fenfei.pets.models.Book;
import com.fenfei.pets.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class IBookServiceImpl implements IBookService {


    @Override
    public List<Book> bookList(String author) {
        List<Book> books = new ArrayList<>() ;
        for (int i=0; i< 5; i ++) {
            Book book = new Book();
            book.setName("book" + i);
            book.setId((long) i);
            books.add(book);
        }
        return books;
    }
}
