package com.example.mzm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Single_question extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();
        final int question_id = Integer.parseInt(intent.getStringExtra("question_id"));
        int question_ans = Integer.parseInt(intent.getStringExtra("question_ans"));
        String question_q = intent.getStringExtra("question_q");

        TextView question = (TextView) findViewById(R.id.single_question);
        question.setText(question_q);


        Random random_no = new Random();
        int x = random_no.nextInt(question_ans);
        RadioButton option1 = (RadioButton) findViewById(R.id.option_1);
        RadioButton option2 = (RadioButton) findViewById(R.id.option_2);
        RadioButton option3 = (RadioButton) findViewById(R.id.option_3);
        RadioButton option4 = (RadioButton) findViewById(R.id.option_4);

        option1.setText("" + x);
        option2.setText("" + (x - question_ans));
        option3.setText("" + (x + question_ans));
        option4.setText("" + question_ans);


        final RadioGroup options = (RadioGroup) findViewById(R.id.options);
        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected_radio_id = options.getCheckedRadioButtonId();
                Log.d("###", "" + selected_radio_id);
                if (selected_radio_id != -1) {
                    RadioButton selected_radio_btn = (RadioButton) findViewById(selected_radio_id);
                    Intent send_ans = new Intent(getApplicationContext(), ques_n_list.class);
                    send_ans.putExtra("typed_ans", "" + selected_radio_btn.getText());
                    send_ans.putExtra("question_id", "" + question_id);
                    setResult(RESULT_OK, send_ans);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please choose an option",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
