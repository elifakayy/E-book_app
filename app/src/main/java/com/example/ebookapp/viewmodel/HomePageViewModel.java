package com.example.ebookapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ebookapp.repository.RemoteDB.FirebaseLogin;
import com.google.firebase.auth.FirebaseUser;

public class HomePageViewModel extends AndroidViewModel {
    private FirebaseLogin fLogin;
    private MutableLiveData<Boolean> loggedOutLiveData;


    private MutableLiveData<FirebaseUser> userLiveData;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        fLogin = new FirebaseLogin(application);
        loggedOutLiveData = fLogin.getLoggedOutLiveData();
        userLiveData = fLogin.getUserLiveData();

    }

    public void logOut() {
        fLogin.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }
}
