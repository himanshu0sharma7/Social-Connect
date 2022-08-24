package com.example.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreate extends AppCompatActivity {
    private Button craetebtn;
    private EditText email, userpassword, userName;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    boolean tf = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Create Account");

        setContentView(R.layout.activity_account_create);
        firebaseAuth = FirebaseAuth.getInstance();
        craetebtn = findViewById(R.id.createaccountwithbtn);
        userpassword = findViewById(R.id.userpassword);
        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.emailid);
        userName = findViewById(R.id.takename);


        craetebtn.setOnClickListener(view -> {
            String ususerlenght = email.getText().toString().trim();
            String passwordlenght = userpassword.getText().toString().trim();
            String NameofUser = userName.getText().toString().trim();


            if (passwordlenght.isEmpty()) {
                userpassword.setError("Fill the passwords");
                userpassword.requestFocus();
            } else if (ususerlenght.isEmpty()) {
                email.setError("Fill the passwords");
                email.requestFocus();
            } else if (passwordlenght.length() <= 7) {
                userpassword.setError("Your password must be 8 (Letters , Numbers , and other) mixing");
                userpassword.requestFocus();
            } else if (NameofUser.isEmpty()) {
                userName.setError("Full fill this");
                userName.requestFocus();
            } else if (email.length() == 0) {
                userName.setError("Full fill this");
                userName.requestFocus();
            } else if (!passwordlenght.isEmpty() && !ususerlenght.isEmpty()) {
                Toast.makeText(AccountCreate.this, userName.getText().toString(), Toast.LENGTH_SHORT).show();
                firebaseAuth.createUserWithEmailAndPassword(ususerlenght, passwordlenght)
                        .addOnCompleteListener(task -> {
                            progressBar.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                User user1 = new User(ususerlenght, passwordlenght, NameofUser);
                                FirebaseDatabase.getInstance().getReference("user").child("UserInfo")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user1).addOnCompleteListener(task1 -> {

                                    if (task1.isSuccessful()) {
                                        Toast.makeText(AccountCreate.this, "Successful", Toast.LENGTH_SHORT).show();
                                        Intent mainpage = new Intent(AccountCreate.this, twitpage.class);
                                        startActivity(mainpage);
                                        progressBar.setVisibility(view.GONE);
                                        finish();
                                    } else {
                                        Toast.makeText(AccountCreate.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                Toast.makeText(AccountCreate.this, "Failed to register", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(view.GONE);
                            }
                        });


            }
        });

    }


}