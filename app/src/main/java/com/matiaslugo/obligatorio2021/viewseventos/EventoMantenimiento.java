package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteCrearActivity;

public class EventoMantenimiento extends MenuActivity implements ListarEventosFragment.OnEventoSeleccionadoListener {

    public static final String EXTRA_EVENTO = "EXTRA_EVENTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_mantenimiento);
    }


    public void btnOnClickAgregarEvento(View view) {
        Intent intencion = new Intent(this, CrearEventoActivity.class);
        startActivity(intencion);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEventoSeleccionado(Evento evento) {
        DetalleEventoFragment frgDetalleEvento = (DetalleEventoFragment) getSupportFragmentManager().findFragmentById(R.id.frmEventoDetalle);
    if(frgDetalleEvento != null){
        frgDetalleEvento.mostrarEvento(evento);
    } else{
        Intent enviarEvento = new Intent(this,DetalleEventoActivity.class);
        enviarEvento.putExtra(EXTRA_EVENTO,evento);
        startActivity(enviarEvento);
    }

    }

    public void btnOnClickAgregarCliente(View view) {
        Intent enviar = new Intent(this, CrearEventoActivity.class);
        startActivity(enviar);
    }
}