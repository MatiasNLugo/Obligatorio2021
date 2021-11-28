package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewseventos.DetalleEventoFragment;

public class ClienteMantenimiento extends MenuActivity
        implements ListadoClienteFragment.OnClienteSeleccionadoListener {



    private MenuItem mniReunion,mniGastos,mniTareas;
    public static final String EXTRA_CLIENTE = "EXTRA_CLIENTE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);




    }
    @Override
    public void onClienteSelecionado(Cliente cliente) {


            DetalleClienteFragment frgDetalleCliente = (DetalleClienteFragment)getSupportFragmentManager().findFragmentById(R.id.frmClienteDetalle);

            if (frgDetalleCliente != null){
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
    DetalleClienteFragment frgDetalleCliente = (DetalleClienteFragment) getSupportFragmentManager().findFragmentById(R.id.frmClienteDetalle);
    if(frgDetalleCliente == null){
        getMenuInflater().inflate(R.menu.menu_main,menu);
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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}