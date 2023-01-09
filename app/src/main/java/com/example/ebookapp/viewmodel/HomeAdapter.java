package com.example.ebookapp.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ebookapp.repository.LocalDB.model.BookModel;
import com.example.ebookapp.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MainViewHolder> {

    private Context mContext;
    private List<Category> categoryList;

    public HomeAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryName;
        public RecyclerView bookRecyclerView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = itemView.findViewById(R.id.textViewHomeCardKategoryName);
            this.bookRecyclerView = itemView.findViewById(R.id.rvHomeCard);
        }
    }
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.categoryName.setText(categoryList.get(position).getCategoryName());
        setCatItemRecycler(holder.bookRecyclerView,categoryList.get(position).getBookList());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    private void setCatItemRecycler(RecyclerView recyclerView, List<BookModel> bookList){

        BookAdapter bookRvAdapter = new BookAdapter(mContext,bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(bookRvAdapter);
    }


}
