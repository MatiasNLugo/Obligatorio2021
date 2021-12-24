package com.matiaslugo.obligatorio2021.presentacion.viewseventos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.presentacion.viewGastos.GastoMantenimientoActivity;
import com.matiaslugo.obligatorio2021.presentacion.viewTareas.ListarTareasActivity;
import com.matiaslugo.obligatorio2021.presentacion.viewreuniones.ReunionMantenimiento;

public class EventoMantenimiento extends MenuActivity implements ListarEventosFragment.OnEventoSeleccionadoListener {

    private DTEvento evento;
    protected MenuItem mniReunion,mniGastos, mniTareas, mniModificar,mniEliminar, mniOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_mantenimiento);


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void btnOnClickAgregarEvento(View view) {
        Intent intencion = new Intent(this, CrearEventoActivity.class);
        startActivity(intencion);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        setTitle("Eventos");
            mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
            mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
            mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
            mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
            mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
            mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);

        return true;
    }

    private void mostrarDialogo(){
        new AlertDialog.Builder(this)
                .setTitle("Eliminar el Evento")
                .setMessage("¿Desea eliminar el evento y sus gastos, tareas y reuniones asociadas?")
                .setPositiveButton("Eliminar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {

                            FabricaPersistencia.getPersistenciaEvento(getApplicationContext()).eliminarEvento(evento.getIdEvento());
                            finish();
                        } catch (ExcepcionPersistencia excepcionPersistencia) {
                            Toast.makeText(getApplicationContext(), excepcionPersistencia.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
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
                mostrarDialogo();
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
                enviar = new Intent(this, ListarTareasActivity.class);
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