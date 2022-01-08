
package com.example.eventmanagment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity implements View.OnKeyListener {

    TextView txt;
    TextView email;
    TextView pass;
    private FirebaseAuth firebaseAuth;
    ImageView imageView1;
    ImageView imageView;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    ProgressBar progressBar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Booking On The Go");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)));
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        email = (TextView) findViewById(R.id.name);
        pass= (TextView) findViewById(R.id.password);
        txt = (TextView) findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();
        relativeLayout = (RelativeLayout) findViewById(R.id.rela);
        pass.setTransformationMethod(new PasswordTransformationMethod());
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        imageView = (ImageView) findViewById(R.id.formBack);
        imageView1 = (ImageView) findViewById(R.id.logo);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });



        linearLayout.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardhide();
            }
        });
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(login.this,signup.class);
                startActivity(myintent);

            }
        });

        email.setOnKeyListener(this);

        pass.setOnKeyListener(this);



    }


    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.INVISIBLE);





    }

    public void onclick(View v)
    {
        progressBar.setVisibility(View.VISIBLE);


           final String e = email.getText().toString();
           final String p = pass.getText().toString();

           if(e.equals("admin@gmail.com")&&p.equals("admin"))
           {
               Intent myintent = new Intent(login.this, baseactivity.class);
               startActivity(myintent);



           }
           else
           {
    registration();
           }

       }



    public void registration() {

        final String e = email.getText().toString();
        final String p = pass.getText().toString();

        if(e.equals("user")&&p.equals("user"))

        {


            Intent myintent = new Intent(login.this, BasicActivityForUser.class);
            startActivity(myintent);

        }
        progressBar.setVisibility(View.INVISIBLE);

        try {
            firebaseAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {



                                    Intent myintent = new Intent(login.this, BasicActivityForUser.class);
                                    startActivity(myintent);


                                }
                    else {


                      //  Toast.makeText(login.this, "Invalid ID or Pass", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

        catch (Exception ex)
        {
            Toast.makeText(login.this, "Enter Something", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }


    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(keyCode==event.KEYCODE_ENTER)
        {
            registration();

        }


        return false;
    }
    public void keyboardhide()
    {
        InputMethodManager imm =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
        System.exit(0);



        //Execute your code here
        Toast.makeText(this,"Back pressed", Toast.LENGTH_SHORT).show();

    }

}

