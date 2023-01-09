package com.example.ebookapp.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ebookapp.R;
import com.example.ebookapp.databinding.FragmentLoginBinding;
import com.example.ebookapp.utils.UtilsVariables;
import com.example.ebookapp.viewmodel.BookViewModel;
import com.example.ebookapp.viewmodel.LoginViewModel;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private LoginViewModel mLoginViewModel;
    private FragmentLoginBinding binding;
    private BookViewModel bookViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        mLoginViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (!UtilsVariables.isComeFromRegister && firebaseUser != null) {

                    bookViewModel.getAllBooks();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.imgToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_registerFragment2);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.etEmail.getText().toString().trim();
                String sifre = binding.etPassword.getText().toString().trim();


                if (email.length() > 0 && sifre.length() > 0) {
                    mLoginViewModel.login(email, sifre);
                    UtilsVariables.isComeFromRegister = false;
                } else {

                    Toast.makeText(getContext(), "Email Address and Password Must Be Entered", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}