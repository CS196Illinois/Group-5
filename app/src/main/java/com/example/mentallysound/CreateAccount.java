package com.example.mentallysound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public Button initialSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initialSignUp = (Button) findViewById(R.id.signUpButton2);

        initialSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize an firestore instance connected to "users" collection
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                //Create a Map of all the data of an user
                Map<String, Object> user = new HashMap<>();
                mAuth = FirebaseAuth.getInstance();
                EditText inputName = (EditText) findViewById(R.id.textName);
                EditText inputEmail = (EditText) findViewById(R.id.textEmail);
                EditText inputPassword = (EditText) findViewById(R.id.textPassword);

                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                user.put("id", 2);
                user.put("name", name);
                user.put("email", email);
                user.put("password", password);

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "account registered", Toast.LENGTH_SHORT).show();;
                    }
                });

                // Add a new document with a generated ID
                db.collection("users").add(user);
                Intent intent = new Intent (CreateAccount.this, QuestionsStart.class);
                startActivity(intent);
            }
        });
    }
}