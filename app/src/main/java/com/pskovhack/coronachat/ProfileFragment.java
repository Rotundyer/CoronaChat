package com.pskovhack.coronachat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {  // <-- Область экрана при нажатии четвёртой кнопки (четвёртая слева) в главном меню

    public ProfileFragment() {
    }

    static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  // <-- Обработка XML файла
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_profile, container, false);
    }
}