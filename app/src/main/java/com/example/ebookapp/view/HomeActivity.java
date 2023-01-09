package com.example.ebookapp.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.repository.LocalDB.DB.BookDatabase;
import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private static boolean a = false;
    private String TAG = "REQUEST";

    private ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {

                } else {
                    Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                }
            });

    //Yazma ve okuma izinlerinin veriliği verilmediğini dinleyen listenerlar
    private ActivityResultLauncher<String> mPermissionResultRead = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    //bookViewModel.dowloadBooktoLocal(firebaseDir,pd,book);
                    mPermissionResult.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("GELDİACTİVİTY","Activity geldi");

        //Yazma ve okuma izinlerinin başlatıldığı yer
        mPermissionResultRead.launch(Manifest.permission.READ_EXTERNAL_STORAGE);


        //Yerel veritabanında bulunan tüm kitaplar alınıyor.
        getBooksFromLocalDB();

        //Ana ekran gerekli ui bileşenleri ayarlanıyor.
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentCV);

        NavigationUI.setupWithNavController(bottomNav,navHostFragment.getNavController());

    }

    //Kitapları roomdan alan kod
    private void getBooksFromLocalDB() {
        class getAllBooks extends AsyncTask<Void, Void, List<Book>> {

            @Override
            protected List<Book> doInBackground(Void... voids) {
                List<Book> bookList = BookDatabase.getInstance(getApplicationContext())
                        .bookDao().getAllBooksInRoom();
                return bookList;
            }

            @Override
            protected void onPostExecute(List<Book> bookList) {
                super.onPostExecute(bookList);
                BooksInRoom.booksInRoom.addAll(bookList);

            }
        }

        getAllBooks gb = new getAllBooks();
        gb.execute();
    }

    @Override
    public void onBackPressed() {
        if(a){
            super.onBackPressed();
        }else{}
    }
}