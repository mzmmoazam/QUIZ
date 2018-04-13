package com.example.mzm.quiz;

public class Question {

    private  int answer;
    private int id,image;

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer(){
        return answer;
    }
    private String question;

    public Question(int id, int image, String question, int answer) {
        this.id = id;
        this.image = image;
        this.question = question;
        this.answer = answer;
    }



}
