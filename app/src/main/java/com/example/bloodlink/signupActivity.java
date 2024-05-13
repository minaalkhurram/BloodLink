package com.example.bloodlink;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signupActivity extends AppCompatActivity {
EditText emailtxt,passtxt, contacttxt, agetxt,usernametxt;
Button submitBtn;

RadioGroup myRg;
RadioButton btnYes,btnNo;

Spinner mySpinner;

FirebaseDatabase FBdatabase;
DatabaseReference dbReference;

boolean YesBtn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        init();

        Spinner bloodTypeSpinner = findViewById(R.id.bloodTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.blood_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTypeSpinner.setAdapter(adapter);

        RadioGroup myRg = findViewById(R.id.radioGroup);
        myRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Check which radio button is selected
                if (checkedId == R.id.radioButton1) {
                    YesBtn = true;
                } else if (checkedId == R.id.radioButton2) {
                    YesBtn = false;
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Firebase initialization
                FBdatabase = FirebaseDatabase.getInstance();
                dbReference = FBdatabase.getReference("user");

                // Get user input values
                String email = emailtxt.getText().toString();
                String username = usernametxt.getText().toString();
                String password = passtxt.getText().toString();
                String bloodtype = mySpinner.getSelectedItem().toString();
                String ageString = agetxt.getText().toString();

                String contactString = contacttxt.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(bloodtype) || TextUtils.isEmpty(ageString) || TextUtils.isEmpty(contactString)) {
                    Toast.makeText(signupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isValidEmail(email)) {
                    Toast.makeText(signupActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageString);
                if (age <= 0 || age >= 200) {
                    Toast.makeText(signupActivity.this, "Please enter a valid age ", Toast.LENGTH_SHORT).show();
                    return;
                }

                long contactNumber = Long.parseLong(contactString);


                    // Check if the username already exists
                    dbReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                Toast.makeText(signupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                            } else {

                                Users newUser = new Users(email, username, bloodtype, password, YesBtn, age, contactNumber);

                                dbReference.child(username).setValue(newUser)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Operation succeeded
                                                Toast.makeText(signupActivity.this, "User data added successfully", Toast.LENGTH_SHORT).show();

                                                // Navigate to login activity
                                                Intent intent = new Intent(signupActivity.this, aboutus.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Operation failed
                                                Toast.makeText(signupActivity.this, "Failed to add user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Error handling
                            Toast.makeText(signupActivity.this, "Error checking username: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
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
        emailtxt=findViewById(R.id.emailtxt);
        passtxt=findViewById(R.id.passtxt);
        submitBtn=findViewById(R.id.submitbtn);
        agetxt=findViewById(R.id.ageTxt);
        contacttxt=findViewById(R.id.Cntxt);
        mySpinner=findViewById(R.id.bloodTypeSpinner);
        myRg=findViewById(R.id.radioGroup);
        btnYes=findViewById(R.id.radioButton1);
        btnNo=findViewById(R.id.radioButton2);
        usernametxt=findViewById(R.id.usernametxt);

    }
    private boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        // Validate email using pattern
        return email.matches(emailPattern);
    }
}