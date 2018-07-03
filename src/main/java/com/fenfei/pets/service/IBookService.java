package com.fenfei.pets.service;

import com.fenfei.pets.models.Book;

import java.util.List;

public interface IBookService {

    List<Book> bookList(String author);
}
