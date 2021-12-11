package com.matiaslugo.obligatorio2021.viewGastos;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.DetalleReunionActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.DetalleReunionFragment;
import com.matiaslugo.obligatorio2021.viewreuniones.ModificarReunionActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class GastoMantenimientoActivity extends MenuActivity implements ListadoGastoFragment.OnGastoSeleccionadoListener{


    private MenuItem mniReunion,mniGastos,mniTareas;
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
        DetalleGastoFragment frgDetalleGasto = (DetalleGastoFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetalleGastos);
        if(frgDetalleGasto == null){

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent enviar;
        switch (item.getItemId()){
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarGastoActivity.class);
                enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
                enviar.putExtra(Constantes.EXTRA_GASTO, gasto);
                startActivity(enviar);
                return true;
            // TODO ELIMIAR EL GASTO
            default:
                return super.onOptionsItemSelected(item);
        }
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