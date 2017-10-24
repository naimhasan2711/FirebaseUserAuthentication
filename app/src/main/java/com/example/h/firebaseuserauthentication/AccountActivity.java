package com.example.h.firebaseuserauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class AccountActivity extends AppCompatActivity {








    public void buttonClick(View view)
    {



        FirebaseAuth.getInstance().signOut();
        Toast.makeText(AccountActivity.this, "Sign out Successfull", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);




    }
}
