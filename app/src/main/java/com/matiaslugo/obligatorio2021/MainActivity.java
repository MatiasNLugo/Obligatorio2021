package com.matiaslugo.obligatorio2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.ListarEventosFragment;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button btnAgregar, btnAgregarEvento, btnListarEvento;
    private TextView tv;
    private String paraMandar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnAgregar = findViewById(R.id.btnAgregarCliente);
        btnAgregarEvento = findViewById(R.id.btnAgregarEvento);
        btnListarEvento = findViewById(R.id.btnIrListarEventos);
        tv = findViewById(R.id.tv);

    btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                IrAclientes();
            } catch (Exception ex) {
                String mensaje = ex.toString();
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
            } finally {
                //dbClientes.close();
            }
        }
    });

    btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnOnClickAgregarEvento(v);
        }
    });

    btnListarEvento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnOnClickListarEventos(v);
        }
    });

    }

    protected void IrAclientes(){
        Intent enviar = new Intent(this, ClienteMantenimiento.class);
        startActivity(enviar);
    }


    protected void btnOnClickAgregarEvento(View view) {


    }


    protected void btnOnClickListarEventos(View view) {
        Intent enviar = new Intent(this, ListarEventosFragment.class);
        startActivity(enviar);
    }


}