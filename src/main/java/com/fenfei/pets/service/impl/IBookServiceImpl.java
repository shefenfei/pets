package com.fenfei.pets.service.impl;

import com.fenfei.pets.dao.IPetDAO;
import com.fenfei.pets.elasticsearch.BookElasticsearchRepo;
import com.fenfei.pets.models.Book;
import com.fenfei.pets.models.BookPost;
import com.fenfei.pets.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookService")
public class IBookServiceImpl implements IBookService {

    @Autowired
    private BookElasticsearchRepo elasticsearchRepo;

    @Autowired
    private IPetDAO petDAO;

    @Override
    public List<Book> bookList(String author) {
        List<Book> books = new ArrayList<>();
        Iterable<BookPost> bookPosts = elasticsearchRepo.findAll();
        for (BookPost bookPost : bookPosts) {
            Book book = new Book();
            book.setId(Long.valueOf(bookPost.getBookid()));
            book.setName(bookPost.getBookname());
            book.setDescription(bookPost.getInfo());
            books.add(book);
        }

        petDAO.selectBookById(1);
        return books;
    }
}
