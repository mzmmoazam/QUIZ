package com.example.mzm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    EditText _name, email, paddress;
    Button save,back;
    String nameVal="",emailVal="",paddressVal="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Get weekly updates!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        _name = (EditText)findViewById(R.id._name);
        email = (EditText)findViewById(R.id.email);
        paddress = (EditText)findViewById(R.id.paddress);

        Intent fromSecondScreen = getIntent();
        nameVal = fromSecondScreen.getStringExtra("name");
        emailVal = fromSecondScreen.getStringExtra("email");
        paddressVal = fromSecondScreen.getStringExtra("paddress");

        if (!nameVal.equals("")){
            _name.setHint(nameVal);
            email.setHint(emailVal);
            paddress.setHint(paddressVal);
        }

        // onclick listener to the save button
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nameVal = _name.getText().toString();
                _name.setHint(nameVal);
                emailVal = email.getText().toString();
                email.setHint(emailVal);
                paddressVal = paddress.getText().toString();
                paddress.setHint(paddressVal);

                _name.setText("");
                email.setText("");
                paddress.setText("");

                Toast.makeText(Profile.this,"Saved..!",Toast.LENGTH_SHORT).show();
                save.setClickable(false);

            }
        });

        back =(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(),Second_screen.class);
                back.putExtra("name",nameVal);
                back.putExtra("email",emailVal);
                back.putExtra("paddress",paddressVal);
                setResult(RESULT_OK,back);
                finish();
            }
        });


    }

}
