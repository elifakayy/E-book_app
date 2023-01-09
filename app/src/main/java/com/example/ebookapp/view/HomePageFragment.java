package com.example.ebookapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.example.ebookapp.databinding.FragmentHomePageBinding;
import com.example.ebookapp.viewmodel.BookViewModel;
import com.example.ebookapp.viewmodel.Category;
import com.example.ebookapp.viewmodel.HomeAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class HomePageFragment extends Fragment {
    List<BookModel> thriller = new ArrayList<>();
    List<BookModel> thriller2 = new ArrayList<>();
    List<BookModel> thriller3 = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    private RecyclerView rv;
    private HomeAdapter adapter;
    private FragmentHomePageBinding binding;
    private BookViewModel bookViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        thriller.addAll(bookViewModel.getBookList());


        categoryList.add(new Category("Bu Haftanın En Popülerleri", thriller));

        HashSet<BookModel> mixed = new HashSet<>();

        for(int i=0; i<thriller.size(); i++){
            mixed.add(thriller.get(i));
        }

        for(BookModel b : mixed){
            thriller2.add(b);
        }

        HashSet<BookModel> mixed2 = new HashSet<>();

        for(int i=0; i<thriller.size(); i++){
            mixed2.add(thriller.get(i));
        }
        thriller3.add(thriller.get(5));
        thriller3.add(thriller.get(6));
        thriller3.add(thriller.get(2));
        thriller3.add(thriller.get(0));


        categoryList.add(new Category("Senin İçin Önerilenler", thriller2));
        categoryList.add(new Category("En Çok Arananlar", thriller3));


        setViews(categoryList);



        return view;

    }

    private void setViews(List<Category> categoryList) {
        rv = binding.rvHome;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(getContext(), categoryList);
        rv.setAdapter(adapter);
    }
}