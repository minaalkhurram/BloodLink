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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class chatActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private DatabaseReference messagesReference;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editTextMessage = findViewById(R.id.editTextMessage);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null)
        {
            currentUserEmail = currentUser.getEmail();
        }
        else
        {

            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        messagesReference = database.getReference("messages");

        findViewById(R.id.buttonSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage()
    {
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {

            long timestamp = System.currentTimeMillis();

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            String formattedTime = sdf.format(new Date(timestamp));
            Message message = new Message(messageText, currentUserEmail, formattedTime);


            messagesReference.push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if (task.isSuccessful()) {
                        // Message sent successfully
                        editTextMessage.setText(""); // Clear the input field
                        Toast.makeText(chatActivity.this, "Message sent successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Error sending message
                        Toast.makeText(chatActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            // If message text is empty
            Toast.makeText(chatActivity.this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
