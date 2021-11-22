package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;

public class ClienteMantenimiento extends AppCompatActivity implements ListadoClienteFragment.OnClienteSeleccionadoListener {



    protected FloatingActionButton btnAgregar;
    protected Fragment fragmentoA;
    protected Fragment fragmentoB;
    protected Fragment fragmentoC;
    private ListView lv;
    private Cliente cliente;
    public static final String EXTRA_CLIENTE = "EXTRA_CLIENTE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);

    }
    @Override
    public void onClienteSelecionado(Cliente cliente) {
        if(cliente instanceof Particular) {
            //Toast.makeText(this, ((Particular) cliente).getNombre(),Toast.LENGTH_LONG).show();
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
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    public void btnOnClickAgregarCliente(View view) {
        Intent enviar = new Intent(this,ClienteCrearActivity.class);
        startActivity(enviar);

    }


}