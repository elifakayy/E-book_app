package com.example.ebookapp.viewmodel;

import com.example.ebookapp.repository.LocalDB.model.BookModel;

import java.util.List;

public class Category {
    private String categoryName;
    private List<BookModel> bookModelList;

    public Category(String categoryName, List<BookModel> bookModelList) {
        this.categoryName = categoryName;
        this.bookModelList = bookModelList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<BookModel> getBookList() {
        return bookModelList;
    }

    public void setBookList(List<BookModel> bookModelList) {
        this.bookModelList = bookModelList;
    }
}
