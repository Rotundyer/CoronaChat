package com.pskovhack.coronachat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class IndividualFragment extends Fragment {  // <-- Область экрана при нажатии второй кнопки (вторая слева) в главном меню

    public IndividualFragment() {
    }

    static IndividualFragment newInstance() {
        return new IndividualFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  // <-- Обработка XML файла
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_individual, container, false);
    }
}