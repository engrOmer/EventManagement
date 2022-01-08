package com.example.eventmanagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterForCateterer extends RecyclerView.Adapter<AdapterForCateterer.RecyclerViewHolder> {


    ArrayList<CatteringItem> arrayList1 = new ArrayList<>();
    Context ctx;

    AdapterForCateterer(ArrayList<CatteringItem> arrayList, Context ctx) {
        this.arrayList1 = arrayList;
        this.ctx = ctx;


    }


    @NonNull
    @Override
    public AdapterForCateterer.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_for_caterers, parent, false);
        AdapterForCateterer.RecyclerViewHolder recyclerViewHolder= new AdapterForCateterer.RecyclerViewHolder(view,arrayList1,ctx);


        return recyclerViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForCateterer.RecyclerViewHolder holder, int position) {


   holder.name.setText(arrayList1.get(position).item);
        holder.price.setText(arrayList1.get(position).price);


    }



    @Override
    public int getItemCount() {
        return arrayList1.size();
    }



    public class RecyclerViewHolder extends RecyclerView.ViewHolder   {
        ArrayList<CatteringItem> arrayList = new ArrayList<>();
        TextView name , price;
         CheckBox chk;
        Context ctx;

        RecyclerViewHolder(View view, ArrayList<CatteringItem> arrayList, Context ctx) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
          chk = (CheckBox) view.findViewById(R.id.chk);



            this.arrayList = arrayList;
            this.ctx = ctx;


        }


    }




}