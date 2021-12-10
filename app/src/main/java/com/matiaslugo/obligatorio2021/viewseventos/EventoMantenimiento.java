package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewGastos.GastoMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewTareas.TareaMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;

public class EventoMantenimiento extends MenuActivity implements ListarEventosFragment.OnEventoSeleccionadoListener {

    private DTEvento evento;
    private MenuItem mniReunion,mniGastos, mniTareas, mniModificar,mniEliminar, mniOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_mantenimiento);


    }

    public void btnOnClickAgregarEvento(View view) {
        Intent intencion = new Intent(this, CrearEventoActivity.class);
        startActivity(intencion);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
            mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
            mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
            mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
            mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
            mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
            mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent enviar;
        switch (item.getItemId()){
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarEventoActivity.class);
                enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
                startActivity(enviar);
                return true;
            case R.id.mniEliminar:

                //TODO Eliminar Evento
                return true;
            case R.id.mniReuniones:
                 enviar = new Intent(this, ReunionMantenimiento.class);
                 enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
                 startActivity(enviar);
                return true;
            case R.id.mniGastos:
                enviar = new Intent(this, GastoMantenimientoActivity.class);
                enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
                startActivity(enviar);
                return true;
            case R.id.mniTareas:
                enviar = new Intent(this, TareaMantenimientoActivity.class);
                enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
                startActivity(enviar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onEventoSeleccionado(DTEvento evento) throws ExcepcionPersonalizada {


            this.evento = evento;
            DetalleEventoFragment frgDetalleEvento = (DetalleEventoFragment) getSupportFragmentManager().findFragmentById(R.id.frmEventoDetalle);
            if (frgDetalleEvento != null) {
                mniModificar.setVisible(true);
                mniEliminar.setVisible(true);
                mniOpciones.setVisible(true);
                mniGastos.setVisible(true);
                mniReunion.setVisible(true);
                mniTareas.setVisible(true);

                frgDetalleEvento.mostrarEvento(evento);
            } else {
                Intent enviarEvento = new Intent(this, DetalleEventoActivity.class);
                enviarEvento.putExtra(Constantes.EXTRA_EVENTO, evento);
                startActivity(enviarEvento);
            }

    }

    public void btnOnClickAgregarCliente(View view) {
        Intent enviar = new Intent(this, CrearEventoActivity.class);
        startActivity(enviar);
    }
}