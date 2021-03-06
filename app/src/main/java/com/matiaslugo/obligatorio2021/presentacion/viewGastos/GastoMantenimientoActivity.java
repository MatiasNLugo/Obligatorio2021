package com.matiaslugo.obligatorio2021.presentacion.viewGastos;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.presentacion.viewseventos.EventoMantenimiento;

public class GastoMantenimientoActivity extends MenuActivity implements ListadoGastoFragment.OnGastoSeleccionadoListener{


    private MenuItem mniReunion,mniGastos,mniTareas, mniModificar, mniEliminar,mniOpciones;
    private DTGasto gasto;
    private DTEvento evento;
    private Intent enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_mantenimiento);

        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
        agregarFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent enviar = new Intent(this, EventoMantenimiento.class);
        startActivity(enviar);

    }


    public void agregarFragment(){
        ListadoGastoFragment listadoGastoFragment = new ListadoGastoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.ID_EVENTO,evento.getIdEvento());
        listadoGastoFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoGastos,listadoGastoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        DetalleGastoFragment frgDetalleGasto = (DetalleGastoFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetalleGastos);
        if(frgDetalleGasto == null){

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                return super.onOptionsItemSelected(item);
    }

    public void btnOnClickAgregarGasto(View view) {
        Intent enviar = new Intent(this, CrearGastoActivity.class);
        enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
        startActivity(enviar);
    }


    @Override
    public void onGastoSeleccionado(DTGasto gasto) {
        this.gasto = gasto;
        DetalleGastoFragment frgGastoFragment =
                (DetalleGastoFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetalleGastos);
        if(frgGastoFragment != null){
            frgGastoFragment.mostrarGasto(gasto);
        } else{
            Intent enviarGasto = new Intent(this, DetalleGastoActivity.class);
            enviarGasto.putExtra(Constantes.EXTRA_GASTO, gasto);
            startActivity(enviarGasto);
        }
    }
}