package com.example.laptrinhandroid_test_ktck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_main_login, btn_main_registry, btn_main_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        btn_main_login = findViewById(R.id.btn_main_login);
        btn_main_registry = findViewById(R.id.btn_main_registry);
        btn_main_exit = findViewById(R.id.btn_main_exit);

        btn_main_exit.setOnClickListener(v->finishAffinity());

        btn_main_login.setOnClickListener(v->startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        btn_main_registry.setOnClickListener(v->startActivity(new Intent(MainActivity.this, RegistryActivity.class)));
    }
}