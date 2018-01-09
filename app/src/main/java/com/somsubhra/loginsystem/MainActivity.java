package com.somsubhra.loginsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sign_In, sign_Up;
    private EditText username, password;
    private ArrayList<String> userArray = new ArrayList<>();
    private ArrayList<String> passArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_In = findViewById(R.id.sign_In);
        sign_Up = findViewById(R.id.sign_Up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        Toast.makeText(this, "Created By SOMSUBHRA DAS", Toast.LENGTH_LONG).show();

        sign_Up.setOnClickListener(this);
        sign_In.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if ( view.getId() == R.id.sign_In ) {
            signIn(username.getText().toString(), password.getText().toString());
        }
        else if ( view.getId() == R.id.sign_Up ) {
            signUp(username.getText().toString(), password.getText().toString());
        }
    }
    private void signIn(String user, String pass) {
        int index;
        boolean check_User = false;
        if ( user.equals("") || pass.equals("") ) {
            Toast.makeText(this, "Please fill up both the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        for( String i : userArray ) {
            if ( i.equals(user) ) {
                check_User = true;
                index = userArray.indexOf(user);
                if( passArray.get(index).equals(pass) ) {
                    Toast.makeText(this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                    Intent success = new Intent(this, SuccessActivity.class);
                    startActivity(success);
                    break;
                }
                else {
                    Toast.makeText(this, "Wrong Username or password! Remember username and password both are case sensitive", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if ( !check_User ) {
            Toast.makeText(this, "No Such Username. Please Sign UP First!! Remember username and password both are case sensitive", Toast.LENGTH_SHORT).show();
        }
    }
    private void signUp(String user, String pass) {
        boolean alreadyUser = false;
        if ( user.equals("") || pass.equals("") ) {
            Toast.makeText(this, "Please fill up both the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        for ( String i : userArray ) {
            if( user.equalsIgnoreCase(i) ) {
                alreadyUser = true;
                break;
            }
        }
        if ( !alreadyUser ) {
            userArray.add(user);
            passArray.add(pass);
            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Username already present in DataBase", Toast.LENGTH_SHORT).show();
        }

    }
}
