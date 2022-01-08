package com.example.eventmanagment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PhotographerForUser extends Fragment implements AdapterForHalls.onitemclicklistener {
private DatabaseReference databaseReference;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        AdapterForHalls adapter;


        View r;
        ArrayList<HallDescriptionItem> arrayList = new ArrayList<>();

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);



        }


@Override
public void onAttach(Activity activity) {
        super.onAttach(activity);





        }
@Override
public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                         Bundle savedInstanceState) {

        r = inflater.inflate(R.layout.activity_photographer_for_user, container, false);


        recyclerView =  r.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference("Photographer");

        databaseReference.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        arrayList.clear();

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


        HallDescriptionItem obj = dataSnapshot1.getValue(HallDescriptionItem.class);

        arrayList.add(obj);


        }

        adapter = new AdapterForHalls(arrayList, getContext(),1);

        adapter.notifyDataSetChanged();


        recyclerView.setAdapter(adapter);

        }

@Override
public void onCancelled(DatabaseError databaseError) {
        Log.i("period", databaseError.getMessage());
        }
        });





        return r;




        }

@Override
public void onclick(int postion) {





        }



        }