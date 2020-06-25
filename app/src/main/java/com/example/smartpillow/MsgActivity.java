package com.example.smartpillow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;


public class MsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        if(!isConnected())
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Alert")
                    .setMessage("Please Turn on Wifi to proceed...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent loginIntent = new Intent(MsgActivity.this, SignUp.class);
                            startActivity(loginIntent);
                        }
                    }).show();
        }
        else
        {
            Intent loginIntent = new Intent(MsgActivity.this,SignUp.class);
            startActivity(loginIntent);
        }
    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }
}