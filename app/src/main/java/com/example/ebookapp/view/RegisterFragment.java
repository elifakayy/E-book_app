package com.example.ebookapp.view;

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
import com.example.ebookapp.databinding.FragmentRegisterBinding;
import com.example.ebookapp.utils.UtilsVariables;
import com.example.ebookapp.viewmodel.RegisterViewModel;
import com.google.firebase.auth.FirebaseUser;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();
                String password2 = binding.etRepassword.getText().toString().trim();
                String fullname = binding.etName.getText().toString().trim();

                if (email.length() > 0 && password.length() > 0) {

                    if(password.equals(password2)){
                        UtilsVariables.isComeFromRegister = true;
                        registerViewModel.register(email, password);

                        registerViewModel.getUserLiveData().observe(getActivity(), new Observer<FirebaseUser>() {
                            @Override
                            public void onChanged(FirebaseUser firebaseUser) {
                                registerViewModel.add_user_to_realtimeDB(fullname,email,registerViewModel.getUserLiveData().getValue().getUid());
                            }
                        });
                    }else{
                        Toast.makeText(getContext(),"Password and re-type password didn't matched.",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.imgToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_registerFragment_to_loginFragment);
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