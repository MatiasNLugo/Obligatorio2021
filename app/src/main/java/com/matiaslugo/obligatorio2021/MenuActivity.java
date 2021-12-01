package com.matiaslugo.obligatorio2021;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;

import com.matiaslugo.obligatorio2021.viewclientes.ClienteMantenimiento;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class MenuActivity extends AppCompatActivity {

    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mniEventos:
                Intent enviar = new Intent(this, EventoMantenimiento.class);
                startActivity(enviar);
                return true;
            case R.id.mniClientes:
                Intent enviar2 = new Intent(this, ClienteMantenimiento.class);
                startActivity(enviar2);
                return true;
            case R.id.mniReuniones:
                Intent enviar3 = new Intent(this, ReunionMantenimiento.class);
                startActivity(enviar3);
                return true;
        }
        return false;
    }
}