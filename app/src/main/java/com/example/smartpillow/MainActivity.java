package com.example.smartpillow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; //4 seconds
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //start timer and take actions after timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent msgIntent = new Intent(MainActivity.this, MsgActivity.class);
                startActivity(msgIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
