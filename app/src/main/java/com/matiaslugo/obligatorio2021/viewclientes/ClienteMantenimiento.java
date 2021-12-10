package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.persistencia.FabricaPersistencia;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;

public class ClienteMantenimiento extends MenuActivity
        implements ListadoClienteFragment.OnClienteSeleccionadoListener {



    private MenuItem mniReunion,mniGastos,mniTareas,mniOpciones,mniModificar,mniEliminar;
    public static final String EXTRA_CLIENTE = "EXTRA_CLIENTE";
    private DTCliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);

    }
    @Override
    public void onClienteSelecionado(DTCliente cliente) {

            this.cliente = cliente;
            DetalleClienteFragment frgDetalleCliente = (DetalleClienteFragment) getSupportFragmentManager().findFragmentById(R.id.frmClienteDetalle);


            if (frgDetalleCliente != null){
                mniModificar.setVisible(true);
                mniEliminar.setVisible(true);
                // Mostrar el cliente en el fragmento
                frgDetalleCliente.mostrarCliente(cliente);
            } else {
                //Iniciar detalle cliente Activity y pasamos el cliente en un intent.

                Intent enviarCliente  = new Intent(this, DetalleClienteActivity.class);
                enviarCliente.putExtra(EXTRA_CLIENTE,cliente);
                startActivity(enviarCliente);

        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
@Override
public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        if(cliente == null){
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);
    }
    return true;
}


    public void btnOnClickAgregarCliente(View view) {
        Intent enviar = new Intent(this,ClienteCrearActivity.class);
        startActivity(enviar);

    }
    private void mostrarDialogo() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar Cliente")
                .setMessage("¿Desea eliminar el cliente?")
                .setPositiveButton("Eliminar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            FabricaPersistencia.getPersistenciaCliente(getApplicationContext())
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


}