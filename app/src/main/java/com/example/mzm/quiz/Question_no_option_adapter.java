package com.example.mzm.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Question_no_option_adapter extends RecyclerView.Adapter<Question_no_option_adapter.Question_no_optionViewHolder> {
    private Context ctx;
    private List<Question> questionlist;
    private OnItemClickListener mListener;

    public Question_no_option_adapter(Context ctx, List<Question> questionlist) {
        this.ctx = ctx;
        this.questionlist = questionlist;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public Question_no_option_adapter.Question_no_optionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.questions_no,null);
        Question_no_optionViewHolder holder = new Question_no_optionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Question_no_option_adapter.Question_no_optionViewHolder holder, int position) {

        final Question question = questionlist.get(holder.getAdapterPosition());

        holder.question_.setText(question.getQuestion());
        holder.image.setImageDrawable(ctx.getResources().getDrawable(question.getImage()));

    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    class Question_no_optionViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView question_;

        public Question_no_optionViewHolder(final View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView2);
            question_ = itemView.findViewById(R.id.q_n_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                            }
                    }
                }
            });

        }
    }
}

