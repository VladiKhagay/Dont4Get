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

import com.dont4get.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = SignUpActivity.class.getSimpleName();

    private MyApplication app;

    private FirebaseAuth mAuth;

    private EditText email_et;
    private EditText password_et;
    private EditText repeat_password_et;
    private Button signup_btn;

    private TextView login_here_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // remove top bar
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        email_et = findViewById(R.id.signup_email_et);
        password_et = findViewById(R.id.signup_password_et);
        repeat_password_et = findViewById(R.id.signup_rp_password_et);
        signup_btn = findViewById(R.id.signup_signup_btn);
        login_here_tv = findViewById(R.id.signup_login_btn);

        setUpSignUpButton();
        setUpLoginButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        email_et.getText().clear();
        password_et.getText().clear();
        repeat_password_et.getText().clear();
    }

    private void setUpSignUpButton() {
        this.signup_btn.setOnClickListener(view -> {
            final String email_txt = email_et.getText().toString();
            final String password_txt = password_et.getText().toString();
            final String rp_password_txt = repeat_password_et.getText().toString();

            // check if user fill all the fields before sending data to firebase
            if (email_txt.isEmpty() || password_txt.isEmpty() || rp_password_txt.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

            // check if valid email format
            } else if (!Utils.isValidEmailAddress(email_txt)) {
                Toast.makeText(SignUpActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();

            // check if valid password format
            } else if (!Utils.isValidPassword(password_txt)) {
                Toast.makeText(SignUpActivity.this,"Please enter valid password \nAny characters\ncontains at least six characters.", Toast.LENGTH_SHORT).show();
            }

            // check if password are matching with each other
            // if not matching with each other then show a toast message
            else if (!password_txt.equals(rp_password_txt)) {
                Toast.makeText(SignUpActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "All fields filled and valid, starting registration");

                mAuth.createUserWithEmailAndPassword(email_txt, password_txt).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        //TODO: add creation of trips list by uid
                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                    } else {
                        Toast.makeText(SignUpActivity.this, "Failed register new user" + task.getResult(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setUpLoginButton() {
        login_here_tv.setOnClickListener(view -> finish());
    }
}