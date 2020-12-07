package com.example.mentallysound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignIn extends AppCompatActivity {
    public Button signUp;
    public Button signIn;
    EditText mEmail,mPassword;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUp = (Button) findViewById(R.id.signUpButton);
        signIn = (Button) findViewById(R.id.signInButton2);
        fAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.editTextTextEmailAddress3);
        mPassword = findViewById(R.id.editTextTextPassword2);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SignIn.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText inputEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress3);
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mEmail.setError("Password is required");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "Logged in.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), QuestionsStart.class));
                        } else {
                            Log.d("SignIn", task.getException().getMessage());
                            Toast.makeText(SignIn.this, "Error: Email or password is incorrect, or user does not already exist.", Toast.LENGTH_SHORT).show();
                            mEmail.getText().clear();
                            mPassword.getText().clear();
                        }
                    }
                });

                /*
                if (CreateAccount.isEmailGood(email)) {
                    Intent intent = new Intent (SignIn.this, QuestionsStart.class);
                    startActivity(intent);
                } else {
                    new SweetAlertDialog(SignIn.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Email Invalid")
                            .setContentText("Please enter a valid email address.")
                            .show();
                }*/
            }
        });
    }
}