package com.example.h.firebaseuserauthentication;

import android.app.Application;
import  com.firebase.client.Firebase;

/**
 * Created by H on 10/23/2017.
 */

public class FireApp extends Application {


    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }


}

