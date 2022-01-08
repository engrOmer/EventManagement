package com.example.eventmanagment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BasicActivityForUser extends AppCompatActivity {
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_for_user);
        BottomNavigationView bottomview = findViewById(R.id.navbar);
        bottomview.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationheader, new HallsForUser()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem) {
        switch (menuitem.getItemId()) {
            case R.id.bill:
                if ((!app.catererlist.isEmpty()) || (!app.hallDescriptionItem.name.equals("0")) || (!app.photographeritem.name.equals("0"))) {
                    Intent in = new Intent(this, Bill.class);
                    startActivity(in);


                } else {


                    Toast.makeText(this, "Please Book something", Toast.LENGTH_LONG).show();
                }
                // ProjectsActivity is my 'home' activity
                return true;
        }
        return (super.onOptionsItemSelected(menuitem));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.finalize, menu);
        return true;

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {


                case R.id.Hall: {
                    item.setChecked(true);
                    fragment = new HallsForUser();
                    break;

                }
                case R.id.Catering: {
                    item.setChecked(true);
                    fragment = new CatteringListForUser();
                    break;
                }
                case R.id.photgrapher: {
                    item.setChecked(true);
                    fragment = new PhotographerForUser();
                    break;


                }


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.navigationheader, fragment).commit();


            return true;


        }
    };


}
