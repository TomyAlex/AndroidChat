package com.example.alex.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


/**
 * Created by Alex on 7/6/2018.
 */

public class Register extends AppCompatActivity {

    // Screen Elements
    private EditText emailET;
    private EditText passwordET;
    private EditText confirmPassET;
    private Button registerB;
    private TextView logInTV;
    private Account account;
    private FirebaseAuth firebaseAuth;
    private MyToast myToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        firebaseAuth = FirebaseAuth.getInstance();
        myToast = new MyToast(getApplicationContext());
        account = new Account();

        emailET = (EditText)findViewById(R.id.RemailET);
        passwordET = (EditText)findViewById(R.id.RpasswordET);
        confirmPassET = (EditText)findViewById(R.id.RconfirmPassET);
        registerB = (Button) findViewById(R.id.RregisterNow);
        logInTV = (TextView)findViewById(R.id.RlogInTV);
    }

    public void launcherLoginScreen(View view) {
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
    }

    public void registerNow(View view) {

        createSignInOutAuth(emailET,passwordET);

        signInAccountAuth(emailET,passwordET);

        account.set_email(emailET.getText().toString());
        account.set_password(passwordET.getText().toString());
        account.set_confirmPassword(confirmPassET.getText().toString());

        firebaseAuth.signOut();
    }

    private void createSignInOutAuth(EditText emailET, EditText passwordET) {
        firebaseAuth.createUserWithEmailAndPassword(emailET.getText().toString().trim(), passwordET.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            myToast.showMessageWithDuration("Authentication succeed.", myToast.LENGTH_SHORT);
                        } else {
                            // If sign in fails, display a message to the user.
                            myToast.showMessageWithDuration("Authentication failed.", myToast.LENGTH_SHORT);
                        }
                    }
                });
        firebaseAuth.signOut();
    }

    private void signInAccountAuth(final EditText emailET, final EditText passwordET) {
        firebaseAuth.signInWithEmailAndPassword(emailET.getText().toString().trim(), passwordET.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update Database with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            account.set_id(user.getUid().toString());
                            account.getDatabaseReference().child(account.get_id());
                            account.getDatabaseReference().child(account.get_id()).child("Email:").setValue(emailET.getText().toString());
                            account.getDatabaseReference().child(account.get_id()).child("Password").setValue(passwordET.getText().toString());
                            account.getDatabaseReference().child(account.get_id()).child("Confirm Password").setValue(confirmPassET.getText().toString());
                            myToast.showMessageWithDuration("Sign in succeed.", myToast.LENGTH_SHORT);
                        } else {
                            // If sign in fails, display a message to the user.
                            myToast.showMessageWithDuration("Sign in fail.", myToast.LENGTH_SHORT);
                        }
                    }
                });
    }
}