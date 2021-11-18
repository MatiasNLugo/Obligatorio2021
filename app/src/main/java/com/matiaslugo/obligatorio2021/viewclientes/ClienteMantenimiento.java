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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);

    }
    @Override
    public void onClienteSelecionado(Cliente cliente) {
        if(cliente instanceof Particular) {
            Toast.makeText(this, ((Particular) cliente).getNombre(),Toast.LENGTH_LONG).show();
        }
    }

    public void btnOnClickAgregarCliente(View view) {
        Intent enviar = new Intent(this,ClienteCrearActivity.class);
        startActivity(enviar);

    }
}