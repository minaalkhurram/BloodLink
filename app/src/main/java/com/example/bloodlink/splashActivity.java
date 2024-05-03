package com.example.bloodlink;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.Intent;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(splashActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}
