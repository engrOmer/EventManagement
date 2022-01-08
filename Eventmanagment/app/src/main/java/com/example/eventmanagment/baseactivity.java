package com.example.eventmanagment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class baseactivity extends AppCompatActivity {
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseactivity);


        BottomNavigationView bottomview = findViewById(R.id.navbar);
        bottomview.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationheader,new Halldescrpition()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            switch(item.getItemId())
            {


                case R.id.Hall:
                {
                    item.setChecked(true);
                    fragment = new Halldescrpition();
                    break;

                }
                case R.id.Catering:
                {
                    item.setChecked(true);
                   fragment = new Cattering();
                    break;
                }
                case R.id.photgrapher:
                {
                    item.setChecked(true);
                    fragment = new PhotographerDiscription();
                    break;



                }


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.navigationheader,fragment).commit();


            return true;



        }
    };

}
