package com.example.alex.chat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Alex on 7/6/2018.
 */

public class Account {

    private String _email;
    private String _password;
    private String _confirmPassword;
    private String _id;
    private DatabaseReference databaseReference;
    private CharSequence charSequence;
    private HashMap<String, CharSequence> hashMap;

    // Constructors
    public Account() {
        this._id = "";
        this.databaseReference = FirebaseDatabase.getInstance("https://chat-17f15.firebaseio.com/").getReference();
    }

    public Account(String username) {
        this._id = "";
        this._email = username;
        this.databaseReference = FirebaseDatabase.getInstance("https://chat-17f15.firebaseio.com/").getReference();
    }

    public Account(String username, String password) {
        this._id = "";
        this._email = username;
        this._password = password;
        this.databaseReference = FirebaseDatabase.getInstance("https://chat-17f15.firebaseio.com/").getReference();
    }

    public Account(String username, String password, String confirmPassword) {
        this._id = "";
        this._email = username;
        this._password = password;
        this._confirmPassword = confirmPassword;
        this.databaseReference = FirebaseDatabase.getInstance("https://chat-17f15.firebaseio.com/").getReference();
            // Match db
//        this.databaseReference = matchDatabaseReference(this.databaseReference);
    }

    // Getters and Setters
    public String get_email() {return _email;}

    public void set_email(String _email) {this._email = _email;}

    public String get_password() {return _password;}

    public void set_password(String _password) {this._password = _password;}

    public String get_confirmPassword() {return _confirmPassword;}

    public void set_confirmPassword(String _confirmPassword) {this._confirmPassword = _confirmPassword;}

    public String get_id() {return _id;}

    public void set_id(String _id) {this._id = _id;}

    public DatabaseReference getDatabaseReference() {return databaseReference;}

    public DatabaseReference matchDatabaseReference(DatabaseReference dbRef) {
        // Match db
        return dbRef;
    }
}
