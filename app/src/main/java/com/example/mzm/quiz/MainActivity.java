package com.example.mzm.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Login_btn , Cancel_btn;
    TextView Attempts_left;
    EditText username , password;

    int no_of_attempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create button objects and textview objects


        // attaching a function to the login button
        Login_btn = (Button)findViewById(R.id.Login_btn);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Attempts_left = (TextView) findViewById(R.id.Attempts_left);
        Cancel_btn = (Button)findViewById(R.id.Cancel_btn);

        if (no_of_attempts<3 && no_of_attempts>0){
            Attempts_left.setVisibility(View.VISIBLE);
        }


        Login_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this,"Loading....",Toast.LENGTH_SHORT).show();

                    // to next screen
                    Intent second_screen = new Intent(MainActivity.this,Second_screen.class);
                    startActivity(second_screen);
                }
                else{
                    no_of_attempts--;
                    Attempts_left.setText("Attempts left :" + no_of_attempts);
                    Attempts_left.setVisibility(View.VISIBLE);
                    if (no_of_attempts<=0){
                        Login_btn.setEnabled(false);
                    }
                }
            }
        });
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
