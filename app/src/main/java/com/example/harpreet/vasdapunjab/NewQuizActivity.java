package com.example.harpreet.vasdapunjab;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class NewQuizActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.QuizbottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.action_category:
                        selectedFragment = CategoryFragment.newInstance();
                        break;


                    case R.id.action_ranking:
                        selectedFragment = RankingFragment.newInstance();
                        break;
                }
                FragmentTransaction Category_transaction = getSupportFragmentManager().beginTransaction();
                Category_transaction.replace(R.id.quiz_frameLayout,selectedFragment);
                Category_transaction.commit();


                return true;
            }

        });

setDefaultFragment();

    }

    private void setDefaultFragment() {

        FragmentTransaction Category_transaction = getSupportFragmentManager().beginTransaction();
        Category_transaction.replace(R.id.quiz_frameLayout,CategoryFragment.newInstance());
        Category_transaction.commit();
    }
}
