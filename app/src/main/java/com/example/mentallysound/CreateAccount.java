package com.example.mentallysound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.proto.TargetGlobal;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public Button initialSignUp;

    //check to see if the email is valid
    public static boolean isEmailGood(String emailToCheck) {
        String expToMatch = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$";
        Pattern pattern = Pattern.compile(expToMatch, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }

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
                EditText inputConfirmEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
                EditText inputPassword = (EditText) findViewById(R.id.textPassword);

                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String confirmEmail = inputConfirmEmail.getText().toString();
                String password = inputPassword.getText().toString();

                //checking to see if the two emails inputted are the same
                if (!(confirmEmail.equals(email))) {
                    new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Confirmation Email...")
                            .setContentText("The two emails you inputted are not the same.")
                            .show();
                } else if (!(isEmailGood(email))) {
                    new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Email Invalid")
                            .setContentText("Please enter a valid email address.")
                            .show();
                } else { //if the emails are valid continue

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

                /*
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
                 */
            }
        });
    }
}