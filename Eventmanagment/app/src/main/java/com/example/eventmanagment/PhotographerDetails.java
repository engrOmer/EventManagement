package com.example.eventmanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhotographerDetails extends AppCompatActivity {
    TextView name;

    TextView seats;
    TextView price;
    TextView phonenumber;

    ImageView first;
    ImageView second;
    CircleImageView logoimage;


    String lon;
    String lat;
    String firstimage;
    String secondimage;
    String logo;


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_details);
        btn =(Button) findViewById(R.id.book);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                app.photographeritem.name = name.getText().toString();
                app.photographeritem.price = price.getText().toString();
                app.photographeritem.seats = seats.getText().toString();
                Log.i("billdetails",app.photographeritem.name);

                Toast.makeText(PhotographerDetails.this, "Booked", Toast.LENGTH_LONG).show();


            }
        });


        logoimage = (CircleImageView) findViewById(R.id.logo);
        first = (ImageView) findViewById(R.id.firstimage);
        second = (ImageView) findViewById(R.id.secondimage);


        name = findViewById(R.id.name);
        seats = findViewById(R.id.seats);
        price = findViewById(R.id.price);
        phonenumber = findViewById(R.id.number);

        name.setText(getIntent().getStringExtra("name"));
        price.setText(getIntent().getStringExtra("price"));
        seats.setText(getIntent().getStringExtra("seats"));
        phonenumber.setText(getIntent().getStringExtra("phonenumber"));
        lon =  getIntent().getStringExtra("lon");


        lat =  getIntent().getStringExtra("lat");
      //  app.lat = lat;
       // app.lon = lon;

        firstimage =  getIntent().getStringExtra("firstimage");
        secondimage =  getIntent().getStringExtra("secondimage");
        logo = getIntent().getStringExtra("logo");

        Picasso.with(this).load(firstimage).fit().centerCrop().into(first, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {

            }
        });

        Picasso.with(this).load(logo).fit().centerCrop().into(logoimage, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {

            }
        });



        Picasso.with(this).load(secondimage).fit().centerCrop().into(second, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {

            }
        });


    }



}

