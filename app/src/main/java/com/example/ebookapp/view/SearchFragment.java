package com.example.ebookapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.example.ebookapp.databinding.FragmentSearchBinding;
import com.example.ebookapp.viewmodel.BookViewModel;
import com.example.ebookapp.viewmodel.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView rv;
    private SearchAdapter adapter;
    private FragmentSearchBinding binding;
    private BookViewModel bookViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        List<BookModel> allBooks = bookViewModel.getBookList();

        binding.rvResults.setHasFixedSize(true);
        binding.rvResults.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));


        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isBook = binding.rbBook.isChecked();
                boolean isAuthor = binding.rbAuthor.isChecked();
                boolean isId = binding.rbId.isChecked();

                ArrayList<BookModel> books = new ArrayList<>();

                if(isBook){

                    books = new ArrayList<>();


                    for(BookModel book : allBooks){

                        if(binding.editTextEntered.getText().toString().equalsIgnoreCase(book.getKitap())){
                            books.add(book);
                        }
                    }
                    if(books.size()!=0){
                        binding.tvResultMessage.setText("Bulunan Kitaplar :");
                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }
                    else{
                        binding.tvResultMessage.setText("Aranılan kitap bulunamadı. ");
                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }

                }
                if(isAuthor){
                    books = new ArrayList<>();

                    for(BookModel book : allBooks){

                        if(binding.editTextEntered.getText().toString().equalsIgnoreCase(book.getYazar())){
                            books.add(book);
                        }
                    }
                    if(books.size()!=0){
                        binding.tvResultMessage.setText("Girilen yazara ait kitaplar : ");
                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }
                    else{
                        binding.tvResultMessage.setText("Girilen yazara ait bir kitap bulunamadı.");
                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }

                }
                if(isId){
                    books = new ArrayList<>();

                    for(BookModel book : allBooks){

                        if(binding.editTextEntered.getText().toString().equals(String.valueOf(book.getISBN()))){

                            books.add(book);
                        }
                    }

                    if(books.size()!=0){
                        binding.tvResultMessage.setText("Girilen ISBN'ye ait kitap :");

                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }
                    else{
                        binding.tvResultMessage.setText("Girilen ISBN'ye ait bir kitap bulunamadı.");
                        adapter = new SearchAdapter(getContext(),books);
                        binding.rvResults.setAdapter(adapter);
                    }

                }
                if(!isBook && !isAuthor && !isId){
                    Toast.makeText(getContext(),"Lütfen bir arama kriteri seçin",Toast.LENGTH_SHORT).show();
                }

            }
        });






        return view;
    }
}