package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;

public class ModificarReunionActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reunion);
    }
}