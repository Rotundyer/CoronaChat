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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity<mFirebaseAnalytics> extends AppCompatActivity {  // <-- Страница регистрации в приложении

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = (EditText) findViewById(R.id.email_register);
        final EditText password = (EditText) findViewById(R.id.password_register);

        Button button_register = (Button) findViewById(R.id.register_enter);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // <-- Кнопка с обработкой регистрации
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {  // <-- Проверка на уникальность email
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {  // <-- Вывод диалога, если email пользователя совпадает с email из базы данных
                                    FirebaseAuth.getInstance().signOut();  // <-- Защита от несанкционированного взлома (костыль)
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Такой email уже занят");
                                    builder.create().show();
                                }
                            }
                        });
            }
        });

        Button button_back = (Button) findViewById(R.id.button_back_register);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // <-- Кнопка возвращение на начальный экран
                Intent intent = new Intent(RegisterActivity.this, FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
