package com.fenfei.pets.dao;

import com.fenfei.pets.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<Book , Long> {

    List<Book> findBookByAuthor(String author);

}
