package com.example.mzm.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Second_screen extends AppCompatActivity {

    TextView _view;
    Button profile, _quiz, option_yes, option_no;
    RadioGroup category_list;
    RadioButton selected_radio_btn;
    String user_name="",user_email="",user_addr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
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


        // add intents to the buttons

        profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Edit_profile = new Intent(Second_screen.this, Profile.class);
                Edit_profile.putExtra("name",user_name);
                Edit_profile.putExtra("email",user_email);
                Edit_profile.putExtra("paddress",user_addr);
                startActivityForResult(Edit_profile,1);
            }
        });


        _quiz = (Button) findViewById(R.id.quiz);
        _view = (TextView) findViewById(R.id.second_screen_tv);
        option_yes = (Button) findViewById(R.id.option_yes);
        option_no = (Button) findViewById(R.id.option_no);

        category_list = (RadioGroup) findViewById(R.id.category_list);


        _quiz.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                profile.setVisibility(View.GONE);
                category_list.setVisibility(View.VISIBLE);
                option_yes.setVisibility(View.VISIBLE);
                option_no.setVisibility(View.VISIBLE);
                _view.setText("Choose one category ");
                _quiz.setVisibility(View.GONE);

            }
        });


        option_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected_radio_id = category_list.getCheckedRadioButtonId();
                if (selected_radio_id != -1) {
                    selected_radio_btn = (RadioButton) findViewById(selected_radio_id);
                    Toast.makeText(getApplicationContext(), "Loading....", Toast.LENGTH_SHORT).show();
                    Intent quiz_y_activity = new Intent(getApplicationContext(), ques_n_list.class);
                    quiz_y_activity.putExtra("operation", selected_radio_btn.getText());
                    startActivity(quiz_y_activity);
                } else {
                    Toast.makeText(getApplicationContext(), "Choose on Caategory ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        option_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected_radio_id = category_list.getCheckedRadioButtonId();
                if (selected_radio_id != -1) {
                    selected_radio_btn = (RadioButton) findViewById(selected_radio_id);
                    Toast.makeText(getApplicationContext(), "Loading....", Toast.LENGTH_SHORT).show();
                    Intent quiz_n_activity = new Intent(getApplicationContext(), Question_list.class);
                    quiz_n_activity.putExtra("operation", selected_radio_btn.getText());
                    startActivity(quiz_n_activity);
                } else {
                    Toast.makeText(getApplicationContext(), "Choose on Caategory ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode==RESULT_OK){
            user_name = data.getStringExtra("name");
            user_email = data.getStringExtra("email");
            user_addr = data.getStringExtra("paddress");

        }
        }
}
