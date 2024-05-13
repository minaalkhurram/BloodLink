package com.example.bloodlink;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {
    private TextView usernameTxt, emailTxt, contactTxt, bloodTypeTxt, mdTxt;
    private DatabaseReference usersRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase components
        mAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("user");

        // Initialize views
        usernameTxt = findViewById(R.id.usernametxt);
        emailTxt = findViewById(R.id.emailtxt);
        contactTxt = findViewById(R.id.contacttxt);
        bloodTypeTxt = findViewById(R.id.bloodtypetxt);
        mdTxt = findViewById(R.id.mdtxt);

        // Retrieve username from intent
        String username = getIntent().getStringExtra("username");

        // Set user information
        setUserInfo(username);
    }

    private void setUserInfo(String username) {
        // Retrieve user information from Firebase database
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Retrieve user object
                    Users user = dataSnapshot.getValue(Users.class);
                    if (user != null) {
                        // Set user information in TextViews
                        usernameTxt.setText(user.getUser());
                        emailTxt.setText(user.getEmail());
                        contactTxt.setText(String.valueOf(user.getConatactNum()));
                        bloodTypeTxt.setText(user.getBloodType());
                        mdTxt.setText(user.getmedicalDisease() ? "Yes" : "No");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(profileActivity.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}