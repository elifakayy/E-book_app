package com.example.ebookapp.viewmodel;

import android.content.Context;
import android.os.Bundle;
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
import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.example.ebookapp.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context mContext;
    private List<BookModel> bookList;

    public BookAdapter(Context mContext, List<BookModel> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    public static final class BookViewHolder extends RecyclerView.ViewHolder{
        public ImageView bookImage;
        public TextView bookName;
        public Button buttonLook;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookImage = itemView.findViewById(R.id.imageViewBookCardBookImage);
            this.bookName = itemView.findViewById(R.id.textViewBookCardBookName);
            this.buttonLook = itemView.findViewById(R.id.buttonBookCardLook);

        }
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.books_card_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Glide.with(mContext)
                .load(bookList
                        .get(position)
                        .getResim())
                .into(holder.bookImage);
        holder.bookName.setText(bookList.get(position).getKitap());
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        holder.buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_bookDetailsFragment,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


}
