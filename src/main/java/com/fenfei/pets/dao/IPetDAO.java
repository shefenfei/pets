package com.fenfei.pets.dao;

import com.fenfei.pets.models.Book;
import org.springframework.stereotype.Repository;


@Repository
public interface IPetDAO {
    Book selectBookById(int bookId);
}
