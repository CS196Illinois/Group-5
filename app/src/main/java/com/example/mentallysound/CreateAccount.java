package com.example.mentallysound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {

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
                user.put("id", 2);
                user.put("name", "user");
                user.put("email", "asdfadsfadsf");
                user.put("password", "asdifiaodsfuioadsoifudas");

                // Add a new document with a generated ID
                db.collection("users").add(user);
                Intent intent = new Intent (CreateAccount.this, QuestionsStart.class);
                startActivity(intent);
            }
        });
    }
}