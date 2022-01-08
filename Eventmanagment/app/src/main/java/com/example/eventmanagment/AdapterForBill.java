package com.example.eventmanagment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForBill extends RecyclerView.Adapter<AdapterForBill.RecyclerViewHolder> {


        ArrayList<CatteringItem> arrayList1 = new ArrayList<>();
        Context ctx;
        String plates;

        AdapterForBill(ArrayList<CatteringItem> arrayList, Context ctx) {
        this.arrayList1 = arrayList;
        this.plates = plates;
        this.ctx = ctx;


        }


@NonNull
@Override
public AdapterForBill.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_for_bill_caterer, parent, false);
        AdapterForBill.RecyclerViewHolder recyclerViewHolder= new AdapterForBill.RecyclerViewHolder(view,arrayList1,ctx);


        return recyclerViewHolder;


        }

@Override
public void onBindViewHolder(@NonNull AdapterForBill.RecyclerViewHolder holder, int position) {


        holder.name.setText(arrayList1.get(position).item);
        holder.price.setText(arrayList1.get(position).price);
        holder.total.setText(arrayList1.get(position).type);



}



@Override
public int getItemCount() {
        return arrayList1.size();
        }



public class RecyclerViewHolder extends RecyclerView.ViewHolder   {
    ArrayList<CatteringItem> arrayList = new ArrayList<>();
    TextView name , price, total;

    RecyclerViewHolder(View view, ArrayList<CatteringItem> arrayList, Context ctx) {
        super(view);

        name = (TextView) view.findViewById(R.id.item);
        price = (TextView) view.findViewById(R.id.perplate);
        total = (TextView) view.findViewById(R.id.cost);



        this.arrayList = arrayList;


    }


}




}
