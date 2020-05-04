package com.pskovhack.coronachat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_dialog = (Button) findViewById(R.id.button_dialog);  // <-- Кнопка выхода из аккаунта
        button_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Выйти из аккаунта?").setNegativeButton("Выйти", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {  // <-- Вызов диалога с подстверждением выхода
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.create().show();
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);  // <-- Вызов нижней навигации
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);  // <--

        loadFragment(StatisticFragment.newInstance());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {  // <-- Обработчик навигации
            switch (item.getItemId()) {
                case R.id.navigation_statistic:  // <-- Первая кнопка
                    loadFragment(StatisticFragment.newInstance());
                    return true;
                case R.id.navigation_individual:  // <-- Вторая кнопка
                    loadFragment(IndividualFragment.newInstance());
                    return true;
                case R.id.navigation_public:  // <-- Третья кнопка
                    loadFragment(PublicFragment.newInstance());
                    return true;
                case R.id.navigation_profile:  // <-- Четвёртая кнопка
                    loadFragment(ProfileFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {  // <-- Обработчик XML фрагментов
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }
}