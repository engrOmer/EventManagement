package com.example.eventmanagment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CaterersDetails extends AppCompatActivity {
    private DatabaseReference databaseReference;
    ArrayList<CatteringItem> arraymaincourse = new ArrayList<>();
    ArrayList<CatteringItem> arraydessert = new ArrayList<>();
    ArrayList<CatteringItem> arraystarter = new ArrayList<>();

    RecyclerView recyclerViewmaincourse;
    RecyclerView recyclerViewdessert;
    RecyclerView recyclerViewstarter;

    RecyclerView.LayoutManager layoutManager;
    AdapterForCateterer adapter;
    TextView number;
    Spinner spinner1;
    TextView n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_details);
        number = (TextView) findViewById(R.id.number);
        n = (TextView) findViewById(R.id.name);


        spinner1 = (Spinner) findViewById(R.id.spinner);
        List<Integer> spinnerArray = new ArrayList<>();
        spinnerArray.add(150);
        spinnerArray.add(200);
        spinnerArray.add(250);
        spinnerArray.add(300);
        spinnerArray.add(350);
        spinnerArray.add(400);

        ArrayAdapter<Integer> adap = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerArray
        );
        spinner1.setAdapter(adap);


        String name = getIntent().getStringExtra("name");
        n.setText(name);


        databaseReference = FirebaseDatabase.getInstance().getReference("Caterring");

        databaseReference.child(name).child("items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                arraymaincourse.clear();
                arraydessert.clear();
                arraystarter.clear();


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())  {

                    CatteringItem obj = dataSnapshot1.getValue(CatteringItem.class);

                    if (obj.getType().equals("Dessert")) {
                        arraydessert.add(obj);


                    } else if (obj.getType().equals("Main course")) {
                        arraymaincourse.add(obj);

                    } else if (obj.getType().equals("Starter")) {
                        arraystarter.add(obj);


                    }

                }


//
//                adapter = new AdapterForCateterer(arraydessert, CaterersDetails.this);
//
//                adapter.notifyDataSetChanged();
//
//
//                recyclerViewdessert.setAdapter(adapter);
//
//
//
//
//                adapter = new AdapterForCateterer(arraystarter, CaterersDetails.this);
//
//                adapter.notifyDataSetChanged();
//
//
//                recyclerViewstarter.setAdapter(adapter);
//
//
//
                recyclerViewmaincourse = findViewById(R.id.maincourse);
                layoutManager = new LinearLayoutManager(getApplicationContext());

                recyclerViewmaincourse.setHasFixedSize(true);
                recyclerViewmaincourse.setLayoutManager(layoutManager);


                adapter = new AdapterForCateterer(arraymaincourse, CaterersDetails.this);
                recyclerViewmaincourse.setAdapter(adapter);


                recyclerViewdessert = findViewById(R.id.dessert);
                layoutManager = new LinearLayoutManager(getApplicationContext());

                recyclerViewdessert.setHasFixedSize(true);
                recyclerViewdessert.setLayoutManager(layoutManager);


                adapter = new AdapterForCateterer(arraydessert, CaterersDetails.this);


                recyclerViewdessert.setAdapter(adapter);


                recyclerViewstarter = findViewById(R.id.starter);
                layoutManager = new LinearLayoutManager(getApplicationContext());

                recyclerViewstarter.setHasFixedSize(true);
                recyclerViewstarter.setLayoutManager(layoutManager);


                adapter = new AdapterForCateterer(arraystarter, CaterersDetails.this);
                recyclerViewstarter.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("period", databaseError.getMessage());
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference("Caterring");

        databaseReference.child(name).child("details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String num = dataSnapshot1.getValue().toString();


                    number.setText(num);
                    Log.i("omerasif", num);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("period", databaseError.getMessage());
            }
        });


//
//
//

//
//


    }


    public void onclick(View v) {
        app.catererlist.clear();


        int check = 0;
        if (arraymaincourse.size() > 0) {

            for (int i = 0; i < arraymaincourse.size(); i++) {
                LinearLayout linear = (LinearLayout) recyclerViewmaincourse.getChildAt(i);
                CheckBox chk = (CheckBox) linear.getChildAt(0);
                if (chk.isChecked()) {
                    TextView t = (TextView) linear.getChildAt(1);
                    Log.i("omerasif123", t.getText().toString());

                    TextView p = (TextView) linear.getChildAt(2);
                    Log.i("omerasif123", p.getText().toString());

                    CatteringItem c = new CatteringItem();
                    c.item = t.getText().toString();
                    c.price = p.getText().toString();
                    c.type = spinner1.getSelectedItem().toString();
                    app.catererlist.add(c);

                    check = 1;
                }

            }
        }

        if (arraydessert.size() > 0) {

            for (int i = 0; i < arraydessert.size(); i++) {
                LinearLayout linear = (LinearLayout) recyclerViewdessert.getChildAt(i);
                CheckBox chk = (CheckBox) linear.getChildAt(0);
                if (chk.isChecked()) {
                    TextView t = (TextView) linear.getChildAt(1);
                    Log.i("omerasif123", t.getText().toString());


                    TextView p = (TextView) linear.getChildAt(2);
                    Log.i("omerasif123", p.getText().toString());


                    CatteringItem c = new CatteringItem();
                    c.item = t.getText().toString();
                    c.price = p.getText().toString();
                    c.type = spinner1.getSelectedItem().toString();
                    app.catererlist.add(c);

                    check = 1;

                }

            }
        }


        if (arraystarter.size() > 0) {

            for (int i = 0; i < arraystarter.size(); i++) {
                LinearLayout linear = (LinearLayout) recyclerViewstarter.getChildAt(i);
                CheckBox chk = (CheckBox) linear.getChildAt(0);
                if (chk.isChecked()) {
                    TextView t = (TextView) linear.getChildAt(1);
                    Log.i("omerasif123", t.getText().toString());


                    TextView p = (TextView) linear.getChildAt(2);
                    Log.i("omerasif123", p.getText().toString());

                    CatteringItem c = new CatteringItem();
                    c.item = t.getText().toString();
                    c.price = p.getText().toString();
                    c.type = spinner1.getSelectedItem().toString();
                    app.catererlist.add(c);
                    check = 1;

                }

            }
        }


        if (check == 1) {


            Toast.makeText(this, "Booked", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Choose something", Toast.LENGTH_LONG).show();


        }


    }


}



