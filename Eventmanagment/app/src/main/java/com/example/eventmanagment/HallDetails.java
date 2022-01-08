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

public class HallDetails extends AppCompatActivity {
    TextView name;
    TextView add;
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
    Button locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_details);
        btn = (Button) findViewById(R.id.book);

        locate = (Button) findViewById(R.id.locate);

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent = new Intent(HallDetails.this, LocatePosition.class);
                startActivity(myintent);

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                app.hallDescriptionItem.name = name.getText().toString();
                app.hallDescriptionItem.price = price.getText().toString();
                app.hallDescriptionItem.seats = seats.getText().toString();
                Log.i("billdetails", app.hallDescriptionItem.name);

                Toast.makeText(HallDetails.this, "Booked", Toast.LENGTH_LONG).show();


            }
        });


        logoimage = (CircleImageView) findViewById(R.id.logo);
        first = (ImageView) findViewById(R.id.firstimage);
        second = (ImageView) findViewById(R.id.secondimage);

        add = findViewById(R.id.add);
        name = findViewById(R.id.name);
        seats = findViewById(R.id.seats);
        price = findViewById(R.id.price);
        phonenumber = findViewById(R.id.number);

        name.setText(getIntent().getStringExtra("name"));
        add.setText(getIntent().getStringExtra("address"));
        price.setText(getIntent().getStringExtra("price"));
        seats.setText(getIntent().getStringExtra("seats"));
        phonenumber.setText(getIntent().getStringExtra("phonenumber"));
        lon = getIntent().getStringExtra("lon");


        lat = getIntent().getStringExtra("lat");
        app.lat = lat;
        app.lon = lon;

        firstimage = getIntent().getStringExtra("firstimage");
        secondimage = getIntent().getStringExtra("secondimage");
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

    public void onsubmit(View v) {


    }


}
