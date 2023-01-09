package com.example.ebookapp.repository.LocalDB.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="fileAdress")
    private String fileAdress;

    @ColumnInfo(name="bookImageURL")
    private String bookImageURL;

    @ColumnInfo(name = "bookName")
    private String bookName;

    @ColumnInfo(name = "authorName")
    private String authorName;

    @ColumnInfo(name = "isbn")
    private String isbn;

    @ColumnInfo(name = "bookSummary")
    private String bookSummary;

    public Book(int id, String fileAdress, String bookImageURL, String bookName, String authorName, String isbn, String bookSummary) {
        this.id = id;
        this.fileAdress = fileAdress;
        this.bookImageURL = bookImageURL;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn = isbn;
        this.bookSummary = bookSummary;
    }

    @Ignore
    public Book(String fileAdress, String bookImageURL, String bookName, String authorName, String isbn, String bookSummary) {

        this.fileAdress = fileAdress;
        this.bookImageURL = bookImageURL;
        this.bookName = bookName;
        this.authorName = authorName;
        this.isbn = isbn;
        this.bookSummary = bookSummary;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFileAdress(String fileAdress) {
        this.fileAdress = fileAdress;
    }

    public void setBookImageURL(String bookImageURL) {
        this.bookImageURL = bookImageURL;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public int getId() {
        return id;
    }

    public String getFileAdress() {
        return fileAdress;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getBookSummary() {
        return bookSummary;
    }
}
