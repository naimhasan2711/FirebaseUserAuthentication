package com.example.h.firebaseuserauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mLoginButton;
    private Button mSignupButton;
    private Button mForgotPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = (EditText)findViewById(R.id.emailField);
        mPasswordField = (EditText)findViewById(R.id.passwordField);

        mLoginButton = (Button)findViewById(R.id.signinButton);
        mSignupButton = (Button)findViewById(R.id.signupButton);
        mForgotPassword = (Button)findViewById(R.id.forgotPasswordButton);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {



            }
        };

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();

            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignUp();

            }
        });


        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetPassword();

            }
        });

    }




    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn()
    {

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(MainActivity.this, "Please,Enter your email", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Please, Enter your password", Toast.LENGTH_SHORT).show();
        }

        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_SHORT).show();
                    }

                    if(task.isSuccessful())
                    {
                        Intent intent = new Intent(MainActivity.this,AccountActivity.class);
                        Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                }
            });
        }


    }

    private void startSignUp()
    {

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(MainActivity.this, "Please,Enter your email", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Please, Enter your password", Toast.LENGTH_SHORT).show();
        }

        else{

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()){

                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Sign up successfull", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }



    private void resetPassword()
    {
        String email = mEmailField.getText().toString();

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(MainActivity.this, "Please,Enter your email", Toast.LENGTH_SHORT).show();
        }

        else{

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "A mail has been sent to your email address.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Reset Password failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

}
