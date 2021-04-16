package com.example.baatchit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore database;
    EditText emailBox,passwordBox,nameBox;
    Button signupbtn,oldac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        database =FirebaseFirestore.getInstance();

        emailBox = findViewById(R.id.emailBox);
        nameBox = findViewById(R.id.nameBox);
        passwordBox = findViewById(R.id.passwordBox);
        signupbtn = findViewById(R.id.crtnewacc);
        oldac = findViewById(R.id.oldaccount);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,name;
                email=emailBox.getText().toString();
                password=passwordBox.getText().toString();
                name=nameBox.getText().toString();

                final Users users = new Users();
                users.setName(name);
                users.setEmail(email);
                users.setPass(password);

                oldac.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                    }
                });

                auth.createUserWithEmailAndPassword(email,password ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            database.collection("Users")
                                    .document().set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                }
                            });
                           // Toast.makeText(SignupActivity.this , "Account is created",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}