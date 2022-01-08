package com.example.eventmanagment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Bill extends AppCompatActivity {
    TextView name;
    TextView seats;
    TextView price;

    TextView n;
    TextView p;
    TextView c;


    TextView t;
    String mail;
    String number;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterForBill adapter;
    Double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);


        getSupportActionBar().setTitle("Bill");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)));


        t = (TextView) findViewById(R.id.totalprice);


        name = (TextView) findViewById(R.id.name);
        seats = (TextView) findViewById(R.id.seats);
        price = (TextView) findViewById(R.id.price);

        n = (TextView) findViewById(R.id.naam);
        p = (TextView) findViewById(R.id.daam);
        c = (TextView) findViewById(R.id.coost);


        if (!app.photographeritem.name.equals("0")) {

            n.setText(app.photographeritem.name);
            p.setText(app.photographeritem.seats);
            c.setText(app.photographeritem.price);
            total = Double.valueOf(c.getText().toString());

            t.setText(String.valueOf(total));

        }






        if (!app.hallDescriptionItem.name.equals("0")) {

            name.setText(app.hallDescriptionItem.name);
            seats.setText(app.hallDescriptionItem.seats);
            price.setText(app.hallDescriptionItem.price);
            total = total+ Double.valueOf(price.getText().toString());
            t.setText(String.valueOf(total));


        }
        if (app.catererlist.size() > 0) {
            for (int i = 0; i < app.catererlist.size(); i++) {

                Double d = Double.valueOf(app.catererlist.get(i).type);
                Double e = Double.valueOf(app.catererlist.get(i).price);
                Double r = d * e;
                total = total + r;
                app.catererlist.get(i).type = String.valueOf(r);

            }


            recyclerView = findViewById(R.id.recycler);
            layoutManager = new LinearLayoutManager(this);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);


            adapter = new AdapterForBill(app.catererlist, this);
            recyclerView.setAdapter(adapter);


            t.setText(String.valueOf(total));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuitem) {
        switch (menuitem.getItemId()) {
            case R.id.clear:
                app.catererlist.clear();
                app.hallDescriptionItem.name = "0";
                app.photographeritem.name = "0";
                Intent in = new Intent(this, BasicActivityForUser.class);
                startActivity(in);


                // ProjectsActivity is my 'home' activity
                return true;
        }
        return (super.onOptionsItemSelected(menuitem));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.clear, menu);
        return true;

    }

    public void onclickdone(View v) {


        final Dialog epicdialogue = new Dialog(this);
        epicdialogue.setCancelable(false);
        epicdialogue.setCanceledOnTouchOutside(false);
        epicdialogue.setContentView(R.layout.prompt);
        epicdialogue.show();

        Button ok = (Button) epicdialogue.findViewById(R.id.ok);

        final EditText email = (EditText) epicdialogue.findViewById(R.id.email);
        final EditText phone = (EditText) epicdialogue.findViewById(R.id.phone);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number = phone.getText().toString();
                mail = email.getText().toString();
                //    sendmail();
              //  sendsms();
                epicdialogue.dismiss();
                //progresstest();

            }
        });

        Button cancel = (Button) epicdialogue.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicdialogue.dismiss();
            }
        });


    }

    public void sendsms() {


        final ProgressDialog
                dialog = new ProgressDialog(Bill.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("sherali95cs@gmail.com", "silentbutdeadly");
                    sender.sendMail("Booking On The Go",
                            "Your Bill is "+total,
                            "sherali95cs@gmail.com",
                            mail);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();



    }
}
