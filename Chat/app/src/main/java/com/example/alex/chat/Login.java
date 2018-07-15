package com.example.alex.chat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

/**
 * Created by Alex on 7/6/2018.
 */

public class Login extends AppCompatActivity{

    // Screen Elements
    private EditText emailET;
    private EditText passwordET;
    private Button logInB;
    private FirebaseAuth firebaseAuth;
    private Account account;
    private FirebaseUser user;
    private MyToast myToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // getInstance has the path!
        firebaseAuth = FirebaseAuth.getInstance();
        myToast = new MyToast(getApplicationContext());

        //  Declare elements
        emailET = (EditText)findViewById(R.id.Lemail);
        passwordET = (EditText)findViewById(R.id.LpasswordET);
        logInB = (Button)findViewById(R.id.LlogInB);
    }

    public void loginButtonPress(View view) {
        if(emailET.getText().toString().equals("") && passwordET.getText().toString().equals("")) {
            myToast.showMessageWithDuration("email and pass",myToast.LENGTH_SHORT);
        } else if(emailET.getText().toString().equals("")) {
            myToast.showMessageWithDuration("email",myToast.LENGTH_SHORT);
        }  else if(passwordET.getText().toString().equals("")){
            myToast.showMessageWithDuration("pass",myToast.LENGTH_SHORT);
        } else {
            account = new Account();
            account.set_email(emailET.getText().toString().trim());
            account.set_password(passwordET.getText().toString().trim());

            // Sign in user
            firebaseAuth.signInWithEmailAndPassword(account.get_email(), account.get_password());

            // It will be display to the Chat Screen
            Intent chatIntent = new Intent(this, Chat.class);
            startActivity(chatIntent);
        }
    }

    public void launcherRegisterScreen(View view) {
        // Go to Register Screen and Class
        Intent registerIntent = new Intent(this, Register.class);
        startActivity(registerIntent);
    }
}
