package com.example.eventmanagment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Cattering extends Fragment {

    DatabaseReference ref;
    Button btn;


    View r;
    CheckBox chksoup;
    CheckBox chkwonton;
    CheckBox chkfreshjuice;

    CheckBox chkbiryani;
    CheckBox chkqorma;
    CheckBox chkqunna;
    CheckBox chktikka;
    CheckBox chkchinees;

    CheckBox chklabayshereen;
    CheckBox chkkulfi;
    CheckBox chkicecream;

    Spinner spn;
    EditText edt;
    EditText name;
    EditText number;


    String id = "";
    int check = 0;

    ArrayList<CatteringItem> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);
    }


    @Override
    public void onStart() {
        super.onStart();
        list.clear();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        r = inflater.inflate(R.layout.cattering, container, false);
        btn = r.findViewById(R.id.submit);
        ref = FirebaseDatabase.getInstance().getReference("Caterring");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(v);
            }
        });


        chksoup = r.findViewById(R.id.chksoup);
        chkwonton = r.findViewById(R.id.chkwonton);
        chkfreshjuice = r.findViewById(R.id.chkfreshjuice);
        chkbiryani = r.findViewById(R.id.chkbiryani);
        chkqorma = r.findViewById(R.id.chkqorma);
        chktikka = r.findViewById(R.id.chkTikka);
        chkchinees = r.findViewById(R.id.chkchinees);
        chkqunna = r.findViewById(R.id.chkQunna);
        chklabayshereen = r.findViewById(R.id.chklabayshereen);
        chkkulfi = r.findViewById(R.id.chkkulfi);
        chkicecream = r.findViewById(R.id.chkicecream);
        // edt = r.findViewById(R.id.number);
        number = r.findViewById(R.id.phonenumber);


        name = r.findViewById(R.id.name);


        return r;


    }

    public void onclick(View v) {
        if (chksoup.isChecked()) {
            CatteringItem catteringItem = new CatteringItem();


            spn = r.findViewById(R.id.soup);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "soup";
            catteringItem.type = "Starter";
            list.add(catteringItem);


        }

        if (chkwonton.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.wonton);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "wonton";
            catteringItem.type = "Starter";
            list.add(catteringItem);


        }

        if (chkfreshjuice.isChecked()) {
            CatteringItem catteringItem = new CatteringItem();


            spn = r.findViewById(R.id.freshjuice);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Fresh juice";
            catteringItem.type = "Starter";
            list.add(catteringItem);


        }


        if (chkbiryani.isChecked()) {
            CatteringItem catteringItem = new CatteringItem();


            spn = r.findViewById(R.id.biryani);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "biryani";
            catteringItem.type = "Main course";
            list.add(catteringItem);


        }

        if (chkqorma.isChecked()) {
            CatteringItem catteringItem = new CatteringItem();


            spn = r.findViewById(R.id.qorma);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Qorma";
            catteringItem.type = "Main course";
            list.add(catteringItem);


        }


        if (chkqunna.isChecked()) {
            CatteringItem catteringItem = new CatteringItem();


            spn = r.findViewById(R.id.Qunna);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Qunna";
            catteringItem.type = "Main course";
            list.add(catteringItem);


        }


        if (chktikka.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.tikka);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Tikka";
            catteringItem.type = "Main course";
            list.add(catteringItem);


        }

        if (chkchinees.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.chinees);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Chinees";
            catteringItem.type = "Main course";
            list.add(catteringItem);


        }


        if (chklabayshereen.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.labaysheeryn);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Labayshereen";
            catteringItem.type = "Dessert";
            list.add(catteringItem);


        }


        if (chkkulfi.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.kulfi);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Kulfi";
            catteringItem.type = "Dessert";
            list.add(catteringItem);


        }


        if (chkicecream.isChecked()) {

            CatteringItem catteringItem = new CatteringItem();

            spn = r.findViewById(R.id.icecream);
            catteringItem.price = spn.getSelectedItem().toString();
            catteringItem.item = "Ice cream";
            catteringItem.type = "Dessert";
            list.add(catteringItem);


        }


        if (list.size() < 1) {
            Toast.makeText(r.getContext(), "No item selected", Toast.LENGTH_LONG).show();


        } else if (number.getText().toString().isEmpty()) {

            Toast.makeText(r.getContext(), "Phone number not entered", Toast.LENGTH_LONG).show();


        } else if (name.getText().toString().isEmpty()) {


            Toast.makeText(r.getContext(), "Name not entered", Toast.LENGTH_LONG).show();


        } else {

            id = ref.push().getKey();

            for (int i = 0; i < list.size(); i++) {

                CatteringItem a = new CatteringItem(list.get(i).item, list.get(i).type, list.get(i).price);
                Log.i("omer", a.getItem());
                Log.i("omer", a.getPrice());
                Log.i("omer", a.getType());

                ref.child(name.getText().toString()).child("items").child(id).setValue(a);
                id = ref.push().getKey();
                Toast.makeText(getContext(), "updation completed", Toast.LENGTH_SHORT).show();


            }


             ref.child(name.getText().toString()).child("details").child("phonenumber").setValue(number.getText().toString());
            //ref.child("omer").setValue("asif");
            Toast.makeText(getContext(), "updation completed", Toast.LENGTH_SHORT).show();

            Intent myintent = new Intent(getContext(), baseactivity.class);

            startActivity(myintent);


        }


    }


}


