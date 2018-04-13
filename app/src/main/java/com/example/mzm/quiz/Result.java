package com.example.mzm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView correct,incorrect,score,unattended_;
    Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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
        int result = Integer.parseInt(intent.getStringExtra("result"));
        int unattended = Integer.parseInt(intent.getStringExtra("unattended"));


        correct = (TextView)findViewById(R.id.correct);
        incorrect = (TextView)findViewById(R.id.incorrect);
        score = (TextView)findViewById(R.id.score);
        unattended_ = (TextView) findViewById(R.id.unattended);

        correct.setText(correct.getText()+" "+result);
        incorrect.setText(incorrect.getText()+" "+(16-result-unattended));
        score.setText(score.getText()+" "+((result*100.0)/16));
        unattended_.setText(unattended_.getText()+" "+(unattended));

        close = (Button) findViewById(R.id.close_btn_last);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
