package com.example.ebookapp.repository.LocalDB.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ebookapp.repository.LocalDB.Dao.BookDao;

@Database(entities = Book.class,exportSchema = false,version = 1)
public abstract class BookDatabase extends RoomDatabase {

    private static final String DB_NAME = "book_db";
    private static BookDatabase instance;

    public static synchronized BookDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BookDatabase.class,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }

    public abstract BookDao bookDao();

}
