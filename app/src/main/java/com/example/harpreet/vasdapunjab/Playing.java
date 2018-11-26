package com.example.harpreet.vasdapunjab;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.harpreet.vasdapunjab.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Playing extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL =1000;
    final static long TIMEOUT = 10000;
     int progressvalue =0;


     CountDownTimer mcountDown;
     int index=0,thisQuesstion =0,wrongAnswer=0;
     int score=0,totalQuestion,correctAnswer=0;

   //  FirebaseDatabase database;
   //  DatabaseReference questions;

     ProgressBar progressBar;
     RadioGroup radioGroup;
     RadioButton btnA,btnB,btnC,btnD;
     TextView txtScore,txtQuestionNum,question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        //firebase
      //  database = FirebaseDatabase.getInstance();
      //  questions = database.getReference("Questions").child("Definition");

        txtScore = (TextView)findViewById(R.id.textScore);
        txtQuestionNum = (TextView)findViewById(R.id.textTotalQuestion);
        radioGroup = (RadioGroup)findViewById(R.id.answersRadiogroup);
        question_text = (TextView)findViewById(R.id.question_text);


        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        mcountDown.cancel();

        if(index < totalQuestion)
        {
            RadioButton clickButton = (RadioButton)view;
            if(clickButton.getText().equals(Common.questionList.get(index).getCorrectAnswer()))
            {
                //choose correct Answers
                score+=10;
                correctAnswer++;
                showQuestion(++index);
            }

            else
            {
                score-=5;
                wrongAnswer++;
                showQuestion(++index);

            }
            radioGroup.clearCheck();

            txtScore.setText(String.format("%d",score));

        }
    }

    private void showQuestion(int index) {
        if (index < totalQuestion)
        {
            thisQuesstion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuesstion,totalQuestion));
            progressBar.setProgress(0);
            progressvalue=0;


            question_text.setText(Common.questionList.get(index).getQuestion());
            question_text.setVisibility(View.VISIBLE);

            btnA.setText(Common.questionList.get(index).getAnswerA());
            btnB.setText(Common.questionList.get(index).getAnswerB());
            btnC.setText(Common.questionList.get(index).getAnswerC());
            btnD.setText(Common.questionList.get(index).getAnswerD());

            mcountDown.start();
        }

        else
        {
            //if it is final question


            Intent  doneIntent = new Intent(this,Done.class);
            Bundle bundle = new Bundle();
            bundle.putInt("SCORE",score);
            bundle.putInt("TOTAL",totalQuestion);
            bundle.putInt("CORRECT",correctAnswer);
            bundle.putInt("WRONG",wrongAnswer);
            doneIntent.putExtras(bundle);
            startActivity(doneIntent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = Common.questionList.size();

        mcountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long milliSec) {

                progressBar.setProgress(progressvalue);
                progressvalue++;


            }

            @Override
            public void onFinish() {

                mcountDown.cancel();
                showQuestion(++index);
            }
        };

        showQuestion(index);
    }
}
