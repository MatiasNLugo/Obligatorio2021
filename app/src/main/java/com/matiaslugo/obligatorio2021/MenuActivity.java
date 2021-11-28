package com.matiaslugo.obligatorio2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.viewclientes.ClienteMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.ListarEventosFragment;

public class MenuActivity extends AppCompatActivity {

    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

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
        }
        return false;
    }
}