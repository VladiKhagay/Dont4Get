package com.dont4get;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dont4get.data_models.boundary.UserBoundary;
import com.dont4get.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private MyApplication app;

    private FirebaseAuth mAuth;

    private EditText email_et;
    private EditText password_et;
    private Button login_btn;

    private TextView signup_here_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // remove top bar
        getSupportActionBar().hide();

//        app = (MyApplication)getApplication();

        mAuth = FirebaseAuth.getInstance();

        email_et = findViewById(R.id.login_email_et);
        password_et = findViewById(R.id.login_password_et);
        login_btn = findViewById(R.id.login_login_btn);
        signup_here_tv = findViewById(R.id.login_signup_btn);

        setUpLoginButton();
        setUpSignUpButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        email_et.getText().clear();
        password_et.getText().clear();
    }

    private void setUpLoginButton(){
        this.login_btn.setOnClickListener(view -> {
            final String email_txt = email_et.getText().toString();
            final String password_txt = password_et.getText().toString();

            if (email_txt.isEmpty() || password_txt.isEmpty()) {
                Toast.makeText(LoginActivity.this,"Please enter your Email and Password", Toast.LENGTH_SHORT).show();

            } else if (!Utils.isValidEmailAddress(email_txt) || !Utils.isValidPassword(password_txt)) {

                if (!Utils.isValidEmailAddress(email_txt)) {
                    Toast.makeText(LoginActivity.this,"Please enter valid email address", Toast.LENGTH_SHORT).show();
                    // TODO: add background change to indicate invalid content
                }

                if (!Utils.isValidPassword(password_txt)) {
                    Toast.makeText(LoginActivity.this,"Please enter valid password", Toast.LENGTH_SHORT).show();
                    // TODO: add background change to indicate invalid content
                }
            } else {
                mAuth.signInWithEmailAndPassword(email_txt,password_txt).addOnSuccessListener(authResult -> {
                    Log.d(TAG, authResult.toString());
                    UserBoundary userBoundary = new UserBoundary(authResult.getUser().getEmail(), authResult.getUser().getUid());
                    // TODO: set the active user in the application class

                    // TODO: delete the following line
                    Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_LONG).show();

                    // TODO: start the next activity
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, e.getMessage());
                        Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

    private void setUpSignUpButton() {
        signup_here_tv.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));
    }
}