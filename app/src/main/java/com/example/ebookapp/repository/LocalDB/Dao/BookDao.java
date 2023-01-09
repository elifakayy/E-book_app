package com.example.ebookapp.repository.LocalDB.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ebookapp.repository.LocalDB.DB.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Query("Select * from book")
    List<Book> getAllBooksInRoom();

    @Query("Select * from book Where bookName = :bookName")
    List<Book> getSelectedBook(String bookName);

    @Insert
    void insertBook(Book book);

    @Update
    void updateBook(Book book);

    @Delete
    void deleteBook(Book book);
}
