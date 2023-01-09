package com.example.ebookapp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.example.ebookapp.R;
import com.example.ebookapp.databinding.FragmentBookDetailsBinding;
import com.example.ebookapp.viewmodel.BookViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookDetailsFragment extends Fragment {


    private BottomNavigationView navBar;
    private BookViewModel bookViewModel;
    private int position = 0;
    private FragmentBookDetailsBinding binding;
    private BookModel book;
    private String TAG = "IZINLER";

    private String firebaseDir;
    private ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navBar = getActivity().findViewById(R.id.bottomNav);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navBar.setVisibility(View.INVISIBLE);

        if(getArguments() != null){
            position = getArguments().getInt("position");
            book = bookViewModel.getBookList().get(position);
        }else{
            book = BooksInRoom.bookModel;
        }


        binding = FragmentBookDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //Atamalrı yapalım..
        Glide.with(this).load(book.getResim()).into(binding.imgBookPic);

        binding.tvOzet.setText(book.getOzet());
        binding.tvOzet.setMovementMethod(new ScrollingMovementMethod());
        binding.toolbarBook.setTitle(book.getKitap());
        binding.toolbarBook.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navBar.setVisibility(View.VISIBLE);
                Navigation.findNavController(view).navigate(R.id.action_bookDetailsFragment_to_homePageFragment);
            }
        });

        binding.btnListen.setOnClickListener(view1 -> {

            Bundle bundle = new Bundle();
            bundle.putString("bookName",book.getKitap());
            bundle.putString("bookImage",book.getResim());

            Navigation.findNavController(view).navigate(R.id.action_bookDetailsFragment_to_listeningFragment,bundle);

        });

        binding.btnRead.setOnClickListener(view1 -> {

            downloadBook();

        });

        // Inflate the layout for this fragment
        return view;
    }

    public void downloadBook() {


        firebaseDir = "books/" + book.getId() + ".txt";
        String bookName = book.getKitap();

        pd = new ProgressDialog(getContext());
        pd.setTitle(bookName + ".pdf");
        pd.setMessage("Downloading Please Wait!");
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

        if(BooksInRoom.booksInRoom.size() > 0){

            boolean girdiMi = false;

            for(Book book: BooksInRoom.booksInRoom) {

                if(book.getBookName().equals(bookName)){
                    pd.dismiss();

                    Bundle bundle = new Bundle();
                    bundle.putString("bookName",bookName);
                    girdiMi = true;
                    //Kitap room dan alınacak demek... Bu durumda okuma fragmentine geçiş yapabiliriz.
                    Navigation.findNavController(getView()).navigate(R.id.action_bookDetailsFragment_to_readingFragment,bundle);
                }
            }

            if(!girdiMi) bookViewModel.dowloadBooktoLocal(firebaseDir,pd,book);


        }else{

            bookViewModel.dowloadBooktoLocal(firebaseDir,pd,book);

        }

    }

}