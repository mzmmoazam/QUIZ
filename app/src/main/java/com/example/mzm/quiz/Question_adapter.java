package com.example.mzm.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Question_adapter extends RecyclerView.Adapter<Question_adapter.QuestionViewHolder> {

    private Context ctx;
    private List<Question> questionlist;
    private String [] typed_ans_list;



    public Question_adapter(Context ctx, List<Question> questionlist) {
        this.ctx = ctx;
        this.questionlist = questionlist;
        this.typed_ans_list = new String[questionlist.size()];
    }


    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.questions,null);
        QuestionViewHolder holder = new QuestionViewHolder(view,new MyCustomEditTextListener());
        return holder;
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, final int position) {

        final Question question = questionlist.get(holder.getAdapterPosition());

        holder.question.setText(question.getQuestion());
        holder.image.setImageDrawable(ctx.getResources().getDrawable(question.getImage()));
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.typed_ans.setText(typed_ans_list[holder.getAdapterPosition()]);


    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    public String[] getTyped_ans_list() {
        return typed_ans_list;
    }


    class QuestionViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView question;
        EditText typed_ans;
        MyCustomEditTextListener myCustomEditTextListener;

        public QuestionViewHolder(final View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView);
            question = itemView.findViewById(R.id.question_title);
            typed_ans = itemView.findViewById(R.id.typed_ans);
            this.myCustomEditTextListener = myCustomEditTextListener;
            this.typed_ans.addTextChangedListener(myCustomEditTextListener);
            question.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    itemView.setBackgroundColor(R.color.clicked);
                    return false;
                }
            });

        }
    }

    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            typed_ans_list[position] = charSequence.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }



}
