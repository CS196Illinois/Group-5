package com.example.mentallysound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignIn extends AppCompatActivity {
    public Button signUp;
    public Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signUp = (Button) findViewById(R.id.signUpButton);
        signIn = (Button) findViewById(R.id.signInButton2);

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
                EditText inputEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress3);
                String email = inputEmailAddress.getText().toString();
                if (CreateAccount.isEmailGood(email)) {
                    Intent intent = new Intent (SignIn.this, QuestionsStart.class);
                    startActivity(intent);
                } else {
                    new SweetAlertDialog(SignIn.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Email Invalid")
                            .setContentText("Please enter a valid email address.")
                            .show();
                }
            }
        });
    }
}