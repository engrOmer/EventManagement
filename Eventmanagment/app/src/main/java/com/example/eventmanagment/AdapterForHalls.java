package com.example.eventmanagment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterForHalls extends RecyclerView.Adapter<AdapterForHalls.RecyclerViewHolder> {
    public onitemclicklistener listner;
    int check;

    ArrayList<HallDescriptionItem> arrayList1 = new ArrayList<>();
    Context ctx;

    AdapterForHalls(ArrayList<HallDescriptionItem> arrayList, Context ctx, int check) {

        this.check = check;
        this.arrayList1 = arrayList;
        this.ctx = ctx;


    }


    @NonNull
    @Override
    public AdapterForHalls.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_for_hall, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, arrayList1, ctx);


        return recyclerViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForHalls.RecyclerViewHolder holder, int position) {

        if (check == 1) {

            holder.photos.setText("    Pictures");

        }

        HallDescriptionItem data = arrayList1.get(position);
        holder.name.setText(data.name);
        holder.price.setText(data.price);
        holder.seats.setText(data.seats);

        Picasso.with(ctx).load(data.logourl).fit().centerCrop().into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ArrayList<HallDescriptionItem> arrayList = new ArrayList<>();
        TextView name, price, seats, photos;
        CircleImageView img;

        Context ctx;

        RecyclerViewHolder(View view, ArrayList<HallDescriptionItem> arrayList, Context ctx) {
            super(view);
            view.setOnClickListener(this);

            photos = (TextView) view.findViewById(R.id.pictures);


            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            seats = (TextView) view.findViewById(R.id.seats);
            img = (CircleImageView) view.findViewById(R.id.logo);


            this.arrayList = arrayList;
            this.ctx = ctx;


        }


        public void onClick(View v) {
            //listner.onclick(getAdapterPosition());
            if (check == 1) {

                HallDescriptionItem item = arrayList.get(getAdapterPosition());
                Intent myintent = new Intent(ctx, PhotographerDetails.class);
                myintent.putExtra("name", item.name);
                myintent.putExtra("price", item.price);
                myintent.putExtra("seats", item.seats);
                myintent.putExtra("lon", item.lon);
                myintent.putExtra("lat", item.lat);
                myintent.putExtra("phonenumber", item.phonenumber);
                myintent.putExtra("firstimage", item.firstimage);
                myintent.putExtra("secondimage", item.secondimage);
                myintent.putExtra("logo", item.logourl);

                ctx.startActivity(myintent);


            } else {
                HallDescriptionItem item = arrayList.get(getAdapterPosition());
                Intent myintent = new Intent(ctx, HallDetails.class);
                myintent.putExtra("name", item.name);
                myintent.putExtra("address", item.address);
                myintent.putExtra("price", item.price);
                myintent.putExtra("seats", item.seats);
                myintent.putExtra("lon", item.lon);
                myintent.putExtra("lat", item.lat);
                myintent.putExtra("phonenumber", item.phonenumber);
                myintent.putExtra("firstimage", item.firstimage);
                myintent.putExtra("secondimage", item.secondimage);
                myintent.putExtra("logo", item.logourl);

                ctx.startActivity(myintent);
            }


        }
    }

    public interface onitemclicklistener {
        void onclick(int postion);


    }


}