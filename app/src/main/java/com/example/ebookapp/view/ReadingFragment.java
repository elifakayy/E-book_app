package com.example.ebookapp.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.R;
import com.example.ebookapp.databinding.FragmentReadingBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFragment extends Fragment {

    private Book bookLocal;
    private FragmentReadingBinding binding;
    private String book;
    private BottomNavigationView navBar;
    private String[] fonts = {"indigo_daisy.tff","lato.tff"};
    //,"merriweather.tff","roboto_regular.tff","robotomono.tff","thenautigal.tff"
    private float TEXT_SİZE = 10f;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bookName = getArguments().getString("bookName");

        for(Book book: BooksInRoom.booksInRoom){

            if(book.getBookName().equals(bookName)) bookLocal = book;
        }

        //binding.constraintLayout2.setVisibility(View.VISIBLE);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public String getFileText() throws IOException {

        File file = new File(bookLocal.getFileAdress()); //saves in Android/
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

        } finally {
            br.close();
        }
        return sb.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReadingBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //Async ile yapılabilir kitap yerel cihazdan alınıp textview içinde gösteriliyor.
        try {
            binding.tvBook.setText(getFileText());
            binding.tvBook.setMovementMethod(new ScrollingMovementMethod());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arr = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,fonts);
        binding.fontStylesListView.setAdapter(arr);

        binding.fontStylesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Typeface typeface;
                if(i==0){
                    typeface = ResourcesCompat.getFont(getContext(),R.font.indigo_daisy);
                    binding.tvBook.setTypeface(typeface);
                }else if(i == 1){
                    typeface = ResourcesCompat.getFont(getContext(),R.font.lato);
                    binding.tvBook.setTypeface(typeface);
                }


            }
        });

        binding.btnArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TEXT_SİZE < 30){
                    TEXT_SİZE+=2f;
                    binding.tvBook.setTextSize(TEXT_SİZE);
                }
            }
        });

        binding.btnAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TEXT_SİZE > 10){
                    TEXT_SİZE-=2f;
                    binding.tvBook.setTextSize(TEXT_SİZE);
                }
            }
        });


        binding.toolbarReading.setTitle(bookLocal.getBookName());
        binding.toolbarReading.setNavigationOnClickListener(task ->{
            navBar = getActivity().findViewById(R.id.bottomNav);
            navBar.setVisibility(View.VISIBLE);
            Navigation.findNavController(getView()).navigate(R.id.action_readingFragment_to_homePageFragment);

        });

        binding.fabTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(),TranslateActivity.class));

            }
        });

        binding.fabDic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),DictionaryActivity.class));
            }
        });
        return view;
    }

}