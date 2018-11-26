    package com.example.harpreet.vasdapunjab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.harpreet.vasdapunjab.Common.Common;
import com.example.harpreet.vasdapunjab.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class Start extends AppCompatActivity {

    Button btnPlay;
    ImageView imageView;
    FirebaseDatabase sDatabase;
    DatabaseReference questions;
    String questionCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        questionCategory=getIntent().getStringExtra("Cat");

        sDatabase = FirebaseDatabase.getInstance();
        questions = sDatabase.getReference("Questions").child(questionCategory );

        imageView = (ImageView)findViewById(R.id.LogoImage);
        imageView.setImageResource(R.drawable.splash);

        loadQuestion(Common.categoryId);
        btnPlay = (Button)findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnplayIntent = new Intent(Start.this,Playing.class);
                startActivity(btnplayIntent);
            }
        });
    }

        private void loadQuestion(String categoryId) {

                  if(Common.questionList.size() > 0)
                      Common.questionList.clear();


                  questions.orderByChild("categoryId")
                          .addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  //List< Question> td = (List<Question>) dataSnapshot.getValue();
                                  for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                    Question post = postSnapshot.getValue(Question.class);
                                      Log.e("Get Data", post+"");
                                      Common.questionList.add(post);

                                  }


                                  /*for(Question s:td)
                                  {
                                      Common.questionList.add(s);
                                      Log.e("sa",s+"");
                                      //                               }
                                      Log.e("TESTING",td+"");
                                  }*/

                              }


                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {

                              }
                          });

                  //Random List
            Collections.shuffle(Common.questionList);
        }
    }
