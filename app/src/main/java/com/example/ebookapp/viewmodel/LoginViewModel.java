package com.example.ebookapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ebookapp.repository.RemoteDB.FirebaseLogin;

import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    //Getting the data from dbs performs via model classes in View Model so FirebaseLogin will use here.
    private FirebaseLogin fLogin;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;
    private MutableLiveData<Boolean> isComeFromRegister;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        fLogin = new FirebaseLogin(application);
        userLiveData = fLogin.getUserLiveData();
        loggedOutLiveData = fLogin.getLoggedOutLiveData();
        isComeFromRegister = fLogin.getIsComeFromRegister();
    }

    public void login(String email, String sifre){ fLogin.login(email,sifre); }

    public void logOut() {
        fLogin.logOut();
    }

    public MutableLiveData<Boolean> getIsComeFromRegister(){ return isComeFromRegister;}

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }
}
