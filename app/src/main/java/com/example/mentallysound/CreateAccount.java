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
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.perfmark.Tag;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    public class Checker {
        private boolean check;
        public  Checker(boolean check) {
            this.check = check;

        }
        public boolean getCheck() {
            return this.check;
        }
        public void setCheck(boolean a) {
            this.check = a;
        }
    }

    FirebaseAuth mAuth;
    public Button initialSignUp;

    //check to see if the email is valid
    public static boolean isEmailGood(String emailToCheck) {
        String expToMatch = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$";
        //^(\.?[a-z0-9]){5,}@[a-z0-9A-Z]{1,}\.(com||edu||org||io){1}$
        Pattern pattern = Pattern.compile(expToMatch, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailToCheck);
        return matcher.matches();
    }
    private interface OnCompleteCallback {
         void onCallback(String result);
    }
    public void readData(CollectionReference reference, String email,final OnCompleteCallback onCompleteCallback) {
        final String[] result = {""};
        //check to see if the email already exists in the database
        Query query = reference.whereEqualTo("email", email);
        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().size() > 0) {
                                result[0] = "invalid";
                                onCompleteCallback.onCallback(result[0]);
                            } else {
                                result[0] = "valid";
                                onCompleteCallback.onCallback(result[0]);
                            }
                        } else {
                            result[0] = "invalid";
                            onCompleteCallback.onCallback(result[0]);
                        }
                    }


                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initialSignUp = (Button) findViewById(R.id.signUpButton2);

        //if the user is already signed in.
        /*
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(CreateAccount.this, QuestionsStart.class);
            startActivity(intent);
        }*/

        initialSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize an firestore instance connected to "users" collection
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference reference = db.collection("users");
                final Checker checkEmailInDb = new Checker(false);

                //Create a Map of all the data of an user
                final Map<String, Object> user = new HashMap<>();
                mAuth = FirebaseAuth.getInstance();

                final EditText inputName = (EditText) findViewById(R.id.textName);
                final EditText inputEmail = (EditText) findViewById(R.id.textEmail);
                final EditText inputConfirmEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
                final EditText inputPassword = (EditText) findViewById(R.id.textPassword);

                final String name = inputName.getText().toString().trim();
                final String email = inputEmail.getText().toString().trim();
                final String confirmEmail = inputConfirmEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                readData(reference, email, new OnCompleteCallback() {
                    @Override
                    public void onCallback(String result) {
                        //checking to see if the two emails inputted are the same
                        if (!(confirmEmail.equals(email))) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Confirmation Email...")
                                    .setContentText("The two emails you inputted are not the same. Password must be >5 characters.")
                                    .show();
                            inputConfirmEmail.getText().clear();
                        } else if (!(isEmailGood(email))) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Email Invalid")
                                    .setContentText("Please enter a valid email address. Password must be >5 characters.")
                                    .show();
                            inputEmail.getText().clear();
                            inputConfirmEmail.getText().clear();
                        } else if (name.trim().equals("")) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Invalid Name")
                                    .setContentText("Please enter your name.")
                                    .show();
                            inputName.getText().clear();
                        } else if (checkEmailInDb.getCheck() == true) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Email In Records")
                                    .setContentText("You may already have an account. Try signing in.")
                                    .show();
                            inputEmail.getText().clear();
                            inputConfirmEmail.getText().clear();
                        } else if (result.equals("invalid")) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Email In Records.")
                                    .setContentText("Please enter a valid email address. Password must be >5 characters.")
                                    .show();
                            inputEmail.getText().clear();
                            inputConfirmEmail.getText().clear();
                        } else if (password.length() < 6) {
                            new SweetAlertDialog(CreateAccount.this, SweetAlertDialog.ERROR_TYPE)
                              .setTitleText("Invalid Password.")
                              .setContentText("Password must be >5 characters.")
                              .show();
                            inputPassword.getText().clear();
                        } else {
                            //if the emails are valid continue
                            user.put("id", 2);
                            user.put("name", name);
                            user.put("email", email);
                            user.put("password", password);

                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(getApplicationContext(), "Account Registered", Toast.LENGTH_SHORT).show();
                                }
                            });

                            // Add a new document with a generated ID
                            db.collection("users").add(user);
                            Intent intent = new Intent(CreateAccount.this, QuestionsStart.class);
                            startActivity(intent);
                        }
                    }
                });
                }

        });
    }
}