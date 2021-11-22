package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.R;

public class DetalleClienteActivity extends AppCompatActivity {

    protected DetalleClienteFragment frgDetalleCliente;
    protected Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        frgDetalleCliente = (DetalleClienteFragment)getSupportFragmentManager()
                .findFragmentById(R.id.frmClienteDetalle);

        cliente = (Cliente)getIntent().getSerializableExtra(ClienteMantenimiento.EXTRA_CLIENTE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleCliente.mostrarCliente(cliente);
    }
}