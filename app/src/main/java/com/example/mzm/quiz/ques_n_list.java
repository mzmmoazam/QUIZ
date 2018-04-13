package com.example.mzm.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ques_n_list extends AppCompatActivity implements Question_no_option_adapter.OnItemClickListener{

    RecyclerView recyclerView;
    Question_no_option_adapter adapter;
    List<Question> questions;
    Map<String, String> user_ans;
    Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_n_list);
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

        recyclerView = (RecyclerView)findViewById(R.id.ql_n_option);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        done = (Button)findViewById(R.id.confirm_q_n);

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

        questions = new ArrayList<>();
        user_ans = new HashMap<String, String>();  // add the values from the intent to this list

        for (int i=0;i<16;i++){

            questions.add(
                    new Question(i+1,R.drawable.questionimg,(99-i)+" "+ operation_sign+" "+ (28-i)+"  :",calculate((99-i),28-i,operation_sign))
            );

        }

        adapter = new Question_no_option_adapter(this,questions);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = 0;
                int unattended = 0;
                for(Question q: questions){
                    if (!user_ans.containsKey(q.getId()+"")){
                        unattended++;
                    }
                    else if (q.getAnswer() == Integer.parseInt(user_ans.get(q.getId()+""))){
                        result++;
                    }
                }
//                Toast.makeText(getApplicationContext(),""+result,Toast.LENGTH_SHORT).show();
                Intent result_page = new Intent(getApplicationContext(),Result.class);
                result_page.putExtra("result",result+"");
                result_page.putExtra("unattended",unattended+"");
                startActivity(result_page);
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, Single_question.class);
        Question clickedItem = questions.get(position);
        detailIntent.putExtra("question_id", ""+clickedItem.getId());
        detailIntent.putExtra("question_q", clickedItem.getQuestion());
        detailIntent.putExtra("question_ans", ""+clickedItem.getAnswer());
        startActivityForResult(detailIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode==RESULT_OK){
            String typed_ans = data.getStringExtra("typed_ans");
            String question_id = data.getStringExtra("question_id");
            user_ans.put(question_id,typed_ans);
        }

    }


}
