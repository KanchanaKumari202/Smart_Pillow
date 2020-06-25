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

public class SignUp extends AppCompatActivity {

    EditText name,email,phone,password;

    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

    }

    public void signUpUser(View v){
        dialog.setMessage("Registering... Please wait.");
        dialog.show();

        String Name = name.getText().toString();
        String Email = email.getText().toString();
        String psd = password.getText().toString();

        if(Name.equals("") || Email.equals("") || psd.equals("")){
            dialog.hide();
            Toast.makeText(SignUp.this,"Fields cannot be empty!",Toast.LENGTH_SHORT).show();
        }
        else{
            auth.createUserWithEmailAndPassword(Email, psd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                dialog.hide();
                                Toast.makeText(SignUp.this,"Registration successfull!",Toast.LENGTH_SHORT).show();
                                //FirebaseUser user = auth.getCurrentUser();
                                Intent i = new Intent(SignUp.this, MapsActivity.class);
                                startActivity(i);
                                finish();
                            }

                            else{
                                dialog.hide();
                                Toast.makeText(SignUp.this,"Registration unsuccessfull!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
    }

    public void open_signin(View v){
        Intent i = new Intent(SignUp.this, SignIn.class);
        startActivity(i);
    }
}