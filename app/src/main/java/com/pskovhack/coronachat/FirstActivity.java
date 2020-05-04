package com.pskovhack.coronachat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Type;

public class FirstActivity extends AppCompatActivity {  // <-- Начальный экран

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        FirebaseAuth auth = FirebaseAuth.getInstance();  // <-- Проверка авторизации
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent (FirstActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Button button_login = (Button) findViewById(R.id.login);  // <-- Кнопка перехода на страницу входа
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button button_register = (Button) findViewById(R.id.register); // <-- Кнопка перехода на страницу регистрации
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button button_destroy = (Button) findViewById(R.id.button_destroy); // <-- Кнопка выхода из приложения
        button_destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
