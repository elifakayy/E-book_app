package com.example.ebookapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ebookapp.R;
import com.example.ebookapp.databinding.FragmentListeningBinding;
import com.example.ebookapp.viewmodel.BookViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ListeningFragment extends Fragment {


    private FragmentListeningBinding binding;
    private String bookImageURL;
    private String bookName;
    private int counter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        counter = 0;


        binding = FragmentListeningBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        bookImageURL = getArguments().getString("bookImage");
        bookName = getArguments().getString("bookName");

        binding.tvBookName.setText(bookName);

        Glide.with(getContext()).load(bookImageURL).into(binding.imageViewBookPhoto);

        binding.ivFaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Oynatma hızı artırıldı.",Toast.LENGTH_LONG).show();
            }
        });
        binding.ivSlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Oynatma hızı azaltıldı.",Toast.LENGTH_LONG).show();
            }
        });

        binding.toolbarListening.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().findViewById(R.id.bottomNav).setVisibility(View.VISIBLE);
                Navigation.findNavController(view).navigate(R.id.action_listeningFragment_to_homePageFragment);

            }
        });



        binding.ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counter%2==0){
                    counter++;
                    Toast.makeText(getContext(),"Oynatma duraklatıldı.",Toast.LENGTH_SHORT).show();
                    binding.ivStop.setVisibility(View.INVISIBLE);
                    binding.ivPlay.setVisibility(View.VISIBLE);
                }

            }
        });

        binding.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(counter%2!=0){
                    counter++;
                    Toast.makeText(getContext(),"Oynatma başlatıldı.",Toast.LENGTH_SHORT).show();
                    binding.ivPlay.setVisibility(View.INVISIBLE);
                    binding.ivStop.setVisibility(View.VISIBLE);
                }

            }
        });

        binding.ivTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Belirtilen süreye kadar zamanlayıcı ayarlandı.",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}