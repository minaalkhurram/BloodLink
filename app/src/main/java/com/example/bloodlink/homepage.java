package com.example.bloodlink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class homepage extends AppCompatActivity {

    EditText usernameTxt;
    String username,email;

    Button Aplusbtn,Aminusbtn,Bplusbtn,Bminusbtn,Oplusbtn,Ominusbtn,ABplusbtn,ABminusbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void init()
    {
        usernameTxt=findViewById(R.id.usernametxt);
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

        // Check if Intent contains extras
        if (intent != null && intent.getExtras() != null) {
            // Retrieve username and email from the Intent
            username = intent.getStringExtra("username");
            email = intent.getStringExtra("email");
            usernameTxt.setText(username);



        }

    }

}