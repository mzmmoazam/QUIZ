package com.example.mzm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Question_list extends AppCompatActivity {

    RecyclerView recyclerView;
    Question_adapter adapter;
    Button submit;
    List<Question> questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        submit = (Button)findViewById(R.id.submit_btn);

        questions = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.questions_list_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String operation = intent.getStringExtra("operation");
        String operation_sign;
        switch (operation) {
            case "Addition":
                operation_sign = "+";
                break;
            case "Subtraction":
                operation_sign = "-";
                break;
            case "Division":
                operation_sign = "/";
                break;
            case "Multiplication":
                operation_sign = "*";
                break;
            default:
                operation_sign = "%";
                break;
        }


        for (int i=0;i<16;i++){
            questions.add(
                   new Question(i+1,R.drawable.questionimg,(99-i)+" "+ operation_sign+" "+ (28-i)+"  :",calculate((99-i),28-i,operation_sign))
            );

        }

        adapter = new Question_adapter(this,questions);
        recyclerView.setAdapter(adapter);


        // on click listener to the submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result=0;
                int unattended=0;
                String[] type_answers = adapter.getTyped_ans_list();
                for(int i=0;i<questions.size();i++){
                    Question q = questions.get(i);
//                    Log.d("###",type_answers[i]+" "+type_answers[i].equals("")+" "+ i);
                    if (type_answers[i]==null || type_answers[i].equals("")){
                        unattended++;
                    }
                    else if (q.getAnswer() == Integer.parseInt(type_answers[i])) {
                            result++;
                    }

                }
//                Toast.makeText(getApplicationContext(),"unatte"+unattempted,Toast.LENGTH_SHORT).show();
                Intent result_activity = new Intent(getApplicationContext(),Result.class);
                result_activity.putExtra("result",""+result);
                result_activity.putExtra("unattended",""+unattended);
                startActivity(result_activity);
                }
        });

    }

    public int calculate(int a, int b,String operation){
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "/":
                return a / b;
            case "*":
                return a * b;
            default:
                return a % b;
        }
    }
}
