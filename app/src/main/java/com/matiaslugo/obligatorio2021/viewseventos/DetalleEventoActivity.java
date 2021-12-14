package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewGastos.GastoMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewTareas.ListarTareasActivity;
import com.matiaslugo.obligatorio2021.viewTareas.TareaMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;

public class DetalleEventoActivity extends MenuActivity {

    protected DetalleEventoFragment frgDetalleEvento;
    protected DTEvento evento;
    protected MenuItem mniModificar,mniEliminar, mniOpciones, mniReunion, mniGastos, mniTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

       frgDetalleEvento = (DetalleEventoFragment) getSupportFragmentManager().findFragmentById(R.id.frmEventoDetalle);
       evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
         }


    @Override
    protected void onStart() {
        super.onStart();
        try {
            frgDetalleEvento.mostrarEvento(evento);
        } catch (ExcepcionPersonalizada excepcionPersonalizada) {
            excepcionPersonalizada.printStackTrace();
        }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        setTitle("Detalle de Evento");
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(true);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(true);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(true);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(true);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(true);

        return true;
    }
}



