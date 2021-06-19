package com.example.laptrinhandroid_test_ktck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    Button btn_login_login, btn_login_back;
    EditText edt_login_user, edt_login_pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        btn_login_back = findViewById(R.id.btn_login_back);
        btn_login_login = findViewById(R.id.btn_login_login);
        edt_login_user = findViewById(R.id.edt_login_user);
        edt_login_pass = findViewById(R.id.edt_login_pass);

        edt_login_pass.setHint("Password");
        edt_login_user.setHint("User");

        edt_login_user.setText("18084791_TruongCongCuong@gmail.com");
        edt_login_pass.setText("123456");

        btn_login_back.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        });

        btn_login_login.setOnClickListener(v->{
            auth.signInWithEmailAndPassword(edt_login_user.getText().toString(),edt_login_pass.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                                Toast.makeText(LoginActivity.this,"Login failed....",Toast.LENGTH_SHORT).show();
                            else {
                                startActivity(new Intent(LoginActivity.this, CRUDActivity.class));
                                finish();
                            }
                        }
                    });
        });

    }
}