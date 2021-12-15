package com.matiaslugo.obligatorio2021.presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.ListarEventosFragment;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}