package com.example.harpreet.vasdapunjab;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    Toolbar toolbar;
    FrameLayout frameLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Layout layout;
    Fragment fragment;
    FirebaseAuth auth;
    TextView name_navigation;
    FirebaseUser firebaseUser;
    String Uemail;
    Button resend_mail;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        FirebaseApp.initializeApp(this);

        resend_mail = findViewById(R.id.resend_mail);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        //email da naam fetch karna
        //name_navigation = findViewById(R.id.name_navigation);
        Uemail = firebaseUser.getEmail().toString();
        //name_navigation.setText(Uemail);


        //tool bar di coding
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ////////

        /// Bottom navigation Bar di coding

       frameLayout = findViewById(R.id.frameLayout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout,new Home());
        ft.commit();


        //Bottom Navigation di onClick di coding


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();


                if(id==R.id.mcq)
                {

                    Intent intent = new Intent(MainActivity.this,NewQuizActivity.class);
                    startActivity(intent);
                }
                else if(id==R.id.ayp)
                {
                    fragment = new Ask_Your_Parents();
                }
                else if(id==R.id.watsnew)
                {
                    fragment = new Whats_New();
                }
                else if(id==R.id.home)
                {
                    fragment = new Home();
                }

                if(fragment!=null)
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout,fragment);
                    ft.commit();
                }

                return true;
            }
        });




        // navigation drawer di coding
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        /////////



    }

    // click lister on navigation drawer

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

             if(id==R.id.settings_id)
        {
            fragment = new Setting();
        }
        else if(id==R.id.feedback_id)
        {
            fragment = new Feedback();
        }

        else if (id==R.id.aboutApp_id)
        {
            fragment = new About_app();
        }
        else if (id==R.id.aboutUs_id)
        {
            fragment = new About_us();
        }
        else if(id==R.id.myStatus_id)
        {
            Toast.makeText(MainActivity.this, "Yet to create", Toast.LENGTH_SHORT).show();
        }
         else if(id==R.id.logout_id)
             {
                 logOutMethod();
             }

        if(fragment!=null)
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout,fragment);
            ft.commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //Home ch C te c++ te Onclick methods
    public void onC(View view) {
        Intent intent = new Intent(this,ContentCourseForC.class);
        startActivity(intent);
    }

    public void onCpp(View view) {
        Intent intent = new Intent(this,ContentCourseForCPP.class);
        startActivity(intent);
    }

    //Verification mail send karn layi method
    public void verify(View view)
    {



        firebaseUser.sendEmailVerification().addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Verification Email Sent To "+Uemail, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Email Sending Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Logout te click karn to baad method
    public void logOutMethod()
    {
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        auth.signOut();
        Intent intent = new Intent(MainActivity.this,First_Screen.class);
        startActivity(intent);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putBoolean("Islogin",false).commit();
        finish();

    }

}
