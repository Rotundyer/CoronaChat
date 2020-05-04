package com.pskovhack.coronachat;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {  // <-- Страница входа в приложение

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.email_login);
        final EditText password = (EditText) findViewById(R.id.password_login);

        Button button_login = (Button) findViewById(R.id.login_enter);
        button_login.setOnClickListener(new View.OnClickListener() {  // <-- Кнопка с обработкой email и пароля
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();  // <-- Подключение базы данных
                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {  // <-- Запрос подтверждения из базы данных (email и пароль)
                        if (task.isSuccessful()) { // <-- Обработчик действий, если данные совпадают
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {  // <-- Обработчик действий, если данные не совпадают
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Вы ввели неправильно email или пароль");
                            builder.create().show();
                        }
                    }
                });
            }
        });

        Button button_back = (Button) findViewById(R.id.button_back_login);  // <-- Кнопка возвращение на начальный экран
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
