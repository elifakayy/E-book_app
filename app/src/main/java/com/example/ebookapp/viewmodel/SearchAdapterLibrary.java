package com.example.ebookapp.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ebookapp.repository.LocalDB.DB.Book;
import com.example.ebookapp.R;
import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.repository.LocalDB.model.BookModel;

import java.util.List;

public class SearchAdapterLibrary extends RecyclerView.Adapter<com.example.ebookapp.viewmodel.SearchAdapter.SearchViewHolder>{
    private Context mContext;
    private List<Book> bookList;


    public SearchAdapterLibrary(Context mContext, List<Book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }


    public static final class SearchViewHolder extends RecyclerView.ViewHolder{
        public ImageView bookImage;
        public TextView bookName;
        public Button buttonLook;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookImage = itemView.findViewById(R.id.imageViewBookCardBookImage);
            this.bookName = itemView.findViewById(R.id.textViewBookCardBookName);
            this.buttonLook = itemView.findViewById(R.id.buttonBookCardLook);
        }
    }

    @NonNull
    @Override
    public com.example.ebookapp.viewmodel.SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new com.example.ebookapp.viewmodel.SearchAdapter.SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.books_card_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.ebookapp.viewmodel.SearchAdapter.SearchViewHolder holder, int position) {
        Glide.with(mContext).load(bookList.get(position).getBookImageURL()).into(holder.bookImage);
        holder.bookName.setText(bookList.get(position).getBookName());
        int pozisyon = position;
        holder.buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BooksInRoom.bookModel = new BookModel(1,bookList.get(pozisyon).getBookName(),);
//                Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_bookDetailsFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


}