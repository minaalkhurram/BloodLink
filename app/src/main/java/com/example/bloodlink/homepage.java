package com.example.bloodlink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class homepage extends AppCompatActivity {

    TextView usernameTxt;
    String username,email;

    Button Aplusbtn,Aminusbtn,Bplusbtn,Bminusbtn,Oplusbtn,Ominusbtn,ABplusbtn,ABminusbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);

             init();
         getExtra();

        Aplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","A+");
                startActivity(intent);

            }
        });


        Aminusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","A-");
                startActivity(intent);
            }
        });

        Bplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","B+");
                startActivity(intent);
            }
        });

        Bminusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","B-");
                startActivity(intent);
            }
        });

        ABplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","AB+");
                startActivity(intent);
            }
        });

        ABminusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","AB-");
                startActivity(intent);
            }
        });

        Oplusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","O+");
                startActivity(intent);
            }
        });

        Ominusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this,RecyclerActivity.class);
                intent.putExtra("bloodtype","O-");
                startActivity(intent);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void init()
    {
        usernameTxt=findViewById(R.id.usernameTxt);
        Aplusbtn=findViewById(R.id.typeAplusbtn);
        Aminusbtn=findViewById(R.id.typeAminusbtn);
        Bplusbtn=findViewById(R.id.typeBplusbtn);
        Bminusbtn=findViewById(R.id.typeBminusbtn);
        Oplusbtn=findViewById(R.id.typeOplusbtn);
        Ominusbtn=findViewById(R.id.typeOminusbtn);
        ABplusbtn=findViewById(R.id.typeABplusbtn);
        ABminusbtn=findViewById(R.id.typeABminusbtn);


    }
    public void getExtra()
    {
        Intent intent = getIntent();


 if (intent != null && intent.getExtras() != null) {
            // Retrieve username and email from the Intent
            username = intent.getStringExtra("username");
     //    email = intent.getStringExtra("email");
            usernameTxt.setText(username);}





    }

}