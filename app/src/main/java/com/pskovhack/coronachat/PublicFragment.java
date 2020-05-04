package com.pskovhack.coronachat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class PublicFragment extends Fragment {  // <-- Область экрана при нажатии третьей кнопки (третья слева) в главном меню

    FirebaseListAdapter<Message> adapter;

    public PublicFragment() {
    }

    static PublicFragment newInstance() {
        return new PublicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  // <-- Обработка XML файла
                             Bundle savedInstanceState) {
        final View inflatedView = inflater.inflate(R.layout.menu_public, container, false);

        RelativeLayout menu_public = (RelativeLayout) inflatedView.findViewById(R.id.menu_public);
        ImageButton button_message = (ImageButton) inflatedView.findViewById(R.id.button_message);
        button_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // <-- Отправка сообщения в базу данных
                EditText input = (EditText)inflatedView.findViewById(R.id.your_message);
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new Message(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getEmail())
                        );
                input.setText("");
            }
        });

        ListView listOfMessages = (ListView)inflatedView.findViewById(R.id.messages_view);  // <-- Отсюда код работает некоректно

        Query query = FirebaseDatabase.getInstance().getReference().child("chats");
        FirebaseListOptions<Message> options = new FirebaseListOptions.Builder<Message>()  // <-- Создание опций для запроса из базы данных
                .setQuery(FirebaseDatabase
                        .getInstance()
                        .getReference("Lobbies")
                        .child("Messages"), Message.class)
                .setLayout(R.layout.their_message)
                .build();
        adapter = new FirebaseListAdapter<Message>(options) {  // <-- Запрос сообщений у базы данны
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);  // <-- Вывод сообщений на экран

        return inflatedView;  // Конец некоректно работающего кода
    }
    @Override
    public void onStart() {  // Запуск запроса сообщений вначале активности
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {  // Окончание запроса сообщений при остановке активности
        super.onStop();
        adapter.stopListening();
    }
}
