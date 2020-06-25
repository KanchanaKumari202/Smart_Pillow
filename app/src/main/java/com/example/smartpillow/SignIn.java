package com.example.smartpillow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText emailtext, psdtext;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailtext = (EditText)findViewById(R.id.emailtext);
        psdtext = (EditText)findViewById(R.id.psdtext);

        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public void signInUser(View v){
        dialog.setMessage("Signing in. Please wait.");
        dialog.show();

        String email = emailtext.getText().toString();
        String password = psdtext.getText().toString();

        if(email.equals("") || password.equals("")){
            dialog.hide();
            Toast.makeText(SignIn.this,"Email & Password cannot be empty!",Toast.LENGTH_SHORT).show();
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                dialog.hide();
                                Toast.makeText(SignIn.this,"You have signed in successfully!",Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(SignIn.this, MapsActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                dialog.hide();
                                Toast.makeText(SignIn.this,"Invalid email or password!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }

    public void open_signup(View v){
        Intent i = new Intent(SignIn.this, SignUp.class);
        startActivity(i);
    }
}