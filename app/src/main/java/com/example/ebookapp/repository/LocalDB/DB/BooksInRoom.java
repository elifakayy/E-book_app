package com.example.ebookapp.repository.LocalDB.DB;


import com.example.ebookapp.repository.LocalDB.model.BookModel;

import java.util.ArrayList;
import java.util.List;

//Main activity de doldurulan ve tüm uygulama boyunca kullanılır. Roomda bulunan kitapları tutan listeye sahip
public class BooksInRoom {

    public static List<Book> booksInRoom = new ArrayList<>();

    public static BookModel bookModel = null;

}
