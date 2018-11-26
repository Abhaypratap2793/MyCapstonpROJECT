package com.example.harpreet.vasdapunjab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.harpreet.vasdapunjab.Common.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Done extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore,getTxtResultQuestion,txtWrongAnswer;
    ProgressBar DoneprogressBar;

    FirebaseDatabase database;
    DatabaseReference question_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        database = FirebaseDatabase.getInstance();
        question_score = database.getReference("Question_Score");

        txtResultScore = (TextView)findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = (TextView)findViewById(R.id.txtTotalQuestion);
        DoneprogressBar = (ProgressBar)findViewById(R.id.doneProgressBar);
        txtWrongAnswer = (TextView)findViewById(R.id.WrongAnswer);
        btnTryAgain = (Button)findViewById(R.id.btnTryAgain);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Done.this,NewQuizActivity.class);
                startActivity(intent);
            }
        });

        Bundle extra = getIntent().getExtras();
        Log.e("Vlue:",extra+"");
        if(extra != null)
        {
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
           int correctAnswer = extra.getInt("CORRECT");
           int wrongAnswers = extra.getInt("WRONG");

            txtResultScore.setText(String.format("SCORE : %d",score));
            getTxtResultQuestion.setText(String.format("PASSED : %d / %d",correctAnswer,totalQuestion));
            txtWrongAnswer.setText(String.format("Wrong Answers : %d",wrongAnswers));

            DoneprogressBar.setMax(totalQuestion);
            DoneprogressBar.setProgress(correctAnswer);

           // question_score.child(String.format("%s", Common.categoryId))
        }
    }
}
