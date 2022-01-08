package com.example.eventmanagment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotographerDiscription extends Fragment {
    Spinner spinner1;
    LinearLayout layout;
    View r;
    Button btn;
    ImageView logo;
    ImageView btnTag;
    ImageView firstimage;
    ImageView secondimage;
    EditText name;
    Button submit;
    String id = "";
    Uri selected;
    Uri select;
    Spinner spn;
    EditText price;
    EditText phonenumber;
    ProgressDialog dialog;



    int count =0;
    DatabaseReference databaseReference;
    DatabaseReference dr;
    StorageReference storageReference;
    ProgressBar progressBar;
    String address ="";
    private StorageTask mstorage;


    StorageReference filerefernece;


    Uri LogoUri;
    HallDescriptionItem item = new HallDescriptionItem();

    ArrayList<Uri> list=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displaylocation();
        setHasOptionsMenu(true);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        r = inflater.inflate(R.layout.activity_photographer_discription, container, false);


        submit = r.findViewById(R.id.submit);
        name = r.findViewById(R.id.name);
        phonenumber = r.findViewById(R.id.phonenumber);
        price = r.findViewById(R.id.price);
        spn = r.findViewById(R.id.spinner);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Uploading");
        dialog.setMessage("Please wait");


        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);







        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // displaylocation();

                submit(v);
            }
        });
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference= FirebaseDatabase.getInstance().getReference("Photographer");
        id = databaseReference.push().getKey();






        logo = r.findViewById(R.id.logoimage);

        firstimage = r.findViewById(R.id.firstimage);

        secondimage = r.findViewById(R.id.secondimage);

        secondimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 2);



            }
        });



        firstimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 3);





            }
        });




        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);


            }
        });



        spinner1 = r.findViewById(R.id.spinner);
        List<Integer> spinnerArray = new ArrayList<>();
        spinnerArray.add(2000);
        spinnerArray.add(3000);
        spinnerArray.add(4000);
        spinnerArray.add(5000);
        spinnerArray.add(6000);
        spinnerArray.add(7000);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                getContext(),
                android.R.layout.simple_spinner_item,
                spinnerArray
        );
        spinner1.setAdapter(adapter);


        layout = r.findViewById(R.id.images);
        return r;
        //setContentView(R.layout.main);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 1:
                    LogoUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), LogoUri);
                        logo.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
                case 2:
                    selected = data.getData();
                    list.add(selected);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selected);
                        secondimage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;

                case 3:
                    select = data.getData();
                    list.add(select);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), select);
                        firstimage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;





            }
    }



    public void submit(View v) {




        if (name.getText().toString().isEmpty()) {

            Toast.makeText(r.getContext(), "Name not entered", Toast.LENGTH_LONG).show();

        } else if (price.getText().toString().isEmpty()) {

            Toast.makeText(r.getContext(), "price not entered", Toast.LENGTH_LONG).show();

        } else if (phonenumber.getText().toString().isEmpty()) {
            Toast.makeText(r.getContext(), "phonenumber not entered", Toast.LENGTH_LONG).show();


        } else {
            String e = LogoUri.toString();
            Log.i("uri", e);
            Log.i("omer", "logo uploaded");
            filerefernece = storageReference.child(System.currentTimeMillis() + ".png");
            dialog.show();


            mstorage = filerefernece.putFile(LogoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    Log.i("omer", "logo uploaded");

                    item.setName(name.getText().toString());
                    item.setLogourl(taskSnapshot.getDownloadUrl().toString());
                    item.setPrice(price.getText().toString());
                    item.setSeats(spn.getSelectedItem().toString());
                    item.setLat("0");
                    item.setLon("0");
                    item.setPhonenumber(phonenumber.getText().toString());

                    upload_image_one();


                }
            });






        }
    }

    public void upload_image_one()
    {


        filerefernece = storageReference.child(System.currentTimeMillis() + ".png");


        mstorage = filerefernece.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("omer", "firstimage");
                item.setFirstimage(taskSnapshot.getDownloadUrl().toString());
                upload_image_two();


            }
        });



    }

    public void upload_image_two()
    {


        filerefernece = storageReference.child(System.currentTimeMillis() + ".png");


        mstorage = filerefernece.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("omer", "second image");
                item.setSecondimage(taskSnapshot.getDownloadUrl().toString());
                String key = databaseReference.push().getKey();

                databaseReference.child(key).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(getContext(), "upload Successful", Toast.LENGTH_SHORT).show();



                            Intent myintent = new Intent(getContext(),baseactivity.class);
                            startActivity(myintent);
                            dialog.dismiss();




                        }
                        else
                        {
                            Toast.makeText(getContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();


                        }


                    }
                });





            }
        });



    }

    public void displaylocation()
    {


        Log.i("omer",app.lat);



    }



}

