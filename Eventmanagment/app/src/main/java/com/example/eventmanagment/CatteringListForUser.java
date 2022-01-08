package com.example.eventmanagment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CatteringListForUser extends Fragment {
    private DatabaseReference databaseReference;
    View r;
    ArrayList<String> arrayList = new ArrayList<>();
    public ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        r = inflater.inflate(R.layout.activity_cattering_list_for_user, container, false);
        listView = r.findViewById(R.id.listofcaterers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(getContext(), CaterersDetails.class);

                myintent.putExtra("name", arrayList.get(position));
                startActivity(myintent);


            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("Caterring");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                    String value = dataSnapshot1.getKey();

                    Map<String, Object> map = (Map<String, Object>) dataSnapshot1.getValue();
                    Log.d("names", "Value is: " + map.keySet());
                    String a = map.keySet().toString().replace("[", "");
                    String b = a.replace("]", "");


                    arrayList.add(value);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);

                    listView.setAdapter(adapter);


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("period", databaseError.getMessage());
            }
        });


        return r;


    }


}
