package com.example.ebookapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebookapp.repository.LocalDB.DB.BooksInRoom;
import com.example.ebookapp.databinding.FragmentLibraryBinding;
import com.example.ebookapp.viewmodel.SearchAdapterLibrary;


public class LibraryFragment extends Fragment {

    private FragmentLibraryBinding binding;
    private SearchAdapterLibrary adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentLibraryBinding.inflate(inflater,container,false);
        View view = binding.getRoot();



        binding.rvCurrentlyReading.setHasFixedSize(true);
        binding.rvCurrentlyReading.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));

        adapter = new SearchAdapterLibrary(getContext(), BooksInRoom.booksInRoom);
        binding.rvCurrentlyReading.setAdapter(adapter);

        binding.rvDownloaded.setHasFixedSize(true);
        binding.rvDownloaded.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        adapter = new SearchAdapterLibrary(getContext(), BooksInRoom.booksInRoom);
        binding.rvDownloaded.setAdapter(adapter);

        return view;
    }


}