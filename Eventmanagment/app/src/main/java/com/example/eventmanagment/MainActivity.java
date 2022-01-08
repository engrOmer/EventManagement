package com.example.eventmanagment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String  TAG = "MainActivity";
EditText user;
EditText pass;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
btn = (Button) findViewById(R.id.map);
user= findViewById(R.id.username);
pass = findViewById(R.id.password);


btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(user.getText().toString().equals("admin@gmail.com")&&pass.getText().toString().equals("admin"))
        {
            Intent myintent = new Intent(MainActivity.this,baseactivity.class);
            startActivity(myintent);




        }else if(user.getText().toString().equals("user@gmail.com")&&pass.getText().toString().equals("user")){


            Intent myintent = new Intent(MainActivity.this,BasicActivityForUser.class);
            startActivity(myintent);


        }


    }
});

    }


}
