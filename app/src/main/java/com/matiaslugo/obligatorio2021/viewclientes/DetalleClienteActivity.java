package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaCliente;

public class DetalleClienteActivity extends MenuActivity {

    protected DetalleClienteFragment frgDetalleCliente;
    protected DTCliente cliente;
    protected MenuItem mniModificar, mniEliminar, mniReunion, mniTareas, mniGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);


        frgDetalleCliente = (DetalleClienteFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frmClienteDetalle);

        cliente = (DTCliente) getIntent().getSerializableExtra(ClienteMantenimiento.EXTRA_CLIENTE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleCliente.mostrarCliente(cliente);
    }

    private void mostrarDialogo() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Cliente")
                .setMessage("¿Desea eliminar el cliente?")
                .setPositiveButton("Eliminar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            FabricaLogica.getControladorMantenimientoCliente(getApplicationContext())
                                    .eliminarCliente(cliente.getIdCliente());
                            finish();
                        } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                            Toast.makeText(getApplicationContext(),
                                    excepcionPersonalizada.getMessage(), Toast.LENGTH_SHORT).show();
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

        switch (item.getItemId()) {
            case R.id.mniModificar:
                Intent enviarCliente = new Intent(this, ClienteModificarActivity.class);
                enviarCliente.putExtra(ClienteMantenimiento.EXTRA_CLIENTE, cliente);
                startActivity(enviarCliente);
                return true;
            case R.id.mniEliminar:
                mostrarDialogo();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(true);

        DetalleClienteFragment frgDetalleCliente = (DetalleClienteFragment)
                getSupportFragmentManager().findFragmentById(R.id.frmClienteDetalle);
        if (frgDetalleCliente == null) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
            mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
            mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);
        }
        return true;
    }

}