package com.pskovhack.coronachat;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class StatisticFragment extends Fragment {  // <-- Область экрана при нажатии первой кнопки (первая слева) в главном меню

    public StatisticFragment() {
    }

    static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  // <-- Обработка XML файла
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.menu_statistic, container, false);
        return inflatedView;
    }
}
