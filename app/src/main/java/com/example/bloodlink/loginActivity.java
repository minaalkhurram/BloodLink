package com.example.bloodlink;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class loginActivity extends AppCompatActivity {


    EditText mailTxt,passTxt;
    Button loginBtn,signupBtn;

    FirebaseDatabase FBdatabase;
    DatabaseReference dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        init();


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(loginActivity.this, signupActivity.class);
                startActivity(intent);

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateEmail()&&!validatePassword())
                {

                }
                else {
                    checkUser();
                }


            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        private void init()
        {

            mailTxt=findViewById(R.id.mailTxt);
            passTxt=findViewById(R.id.passTxt);
            loginBtn=findViewById(R.id.loginbtn);
            signupBtn=findViewById(R.id.signUpbtn);
        }

        public boolean validateEmail()
        {
            String email=mailTxt.getText().toString();
            if(email.isEmpty())
            {
                mailTxt.setError("Username Cant be empty");
                return false;
            }
            else
                return true;

        }
        public boolean validatePassword()
        {
            String pass=passTxt.getText().toString();
            if(pass.isEmpty())
            {
               passTxt.setError("Password Cant be empty");
                return false;
            }
            else
                return true;
        }
    public void checkUser() {
        String myemail = mailTxt.getText().toString();
        String pass = passTxt.getText().toString();

        FBdatabase = FirebaseDatabase.getInstance();
        dbReference = FBdatabase.getReference("user");
        Query checkUserDatabase = dbReference.orderByChild("email").equalTo(myemail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passDb = userSnapshot.child("password").getValue(String.class);
                        if (passDb != null && passDb.equals(pass)) {
                            // Password matches, log the user in
                            Intent intent = new Intent(loginActivity.this, homepage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Password doesn't match
                            passTxt.setError("Incorrect password");
                        }
                    }
                } else {
                    // User does not exist
                    mailTxt.setError("User does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(loginActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}