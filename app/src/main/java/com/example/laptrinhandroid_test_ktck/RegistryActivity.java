package com.example.laptrinhandroid_test_ktck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistryActivity extends AppCompatActivity {
    Button btn_reg_reg, btn_reg_back;
    EditText edt_reg_user, edt_reg_pass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_registry);
        btn_reg_back = findViewById(R.id.btn_reg_back);
        btn_reg_reg = findViewById(R.id.btn_reg_reg);
        edt_reg_user = findViewById(R.id.edt_reg_user);
        edt_reg_pass = findViewById(R.id.edt_reg_pass);
        auth = FirebaseAuth.getInstance();

        edt_reg_pass.setHint("Password");
        edt_reg_user.setHint("User");
        btn_reg_back.setOnClickListener(v->{
            startActivity(new Intent(RegistryActivity.this, MainActivity.class));
            finish();
        });

        btn_reg_reg.setOnClickListener(v->{
            auth.createUserWithEmailAndPassword(edt_reg_user.getText().toString(), edt_reg_pass.getText().toString())
                    .addOnCompleteListener(RegistryActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                                Toast.makeText(RegistryActivity.this, "Registry failed....", Toast.LENGTH_SHORT).show();
                            else {
                                startActivity(new Intent(RegistryActivity.this, CRUDActivity.class));
                                finish();
                            }
                        }
                    });
        });
    }
}