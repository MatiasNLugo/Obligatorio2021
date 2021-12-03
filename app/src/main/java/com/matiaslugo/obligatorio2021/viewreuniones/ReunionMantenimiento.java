package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class ReunionMantenimiento extends MenuActivity
        implements ListadoReunionFragment.OnReunionSeleccionadoListener{

    public static final String EXTRA_REUNION = "EXTRA_REUNION";
    private DTEvento evento;
    private DTReunion DTReunion;
    private int idEvento;
    private MenuItem mniReunion,mniGastos,mniTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_mantenimiento);

        evento = (DTEvento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);
        agregarFragmento();
    }

    public void agregarFragmento(){
        ListadoReunionFragment listadoReunionFragment = new ListadoReunionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idEvento",evento.getIdEvento());
        listadoReunionFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoReuniones, listadoReunionFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        DetalleReunionFragment frgDetalleReunion = (DetalleReunionFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        if(frgDetalleReunion == null){
            getMenuInflater().inflate(R.menu.menu_main,menu);
            mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
            mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
            mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent enviar;
        switch (item.getItemId()){
            case R.id.mniReuniones:
                enviar = new Intent(this, ReunionMantenimiento.class);
                enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
                startActivity(enviar);
                return true;
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarReunionActivity.class);
                enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
                enviar.putExtra(ReunionMantenimiento.EXTRA_REUNION, DTReunion);
                startActivity(enviar);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btnOnClickAgregarReunion(View view) {
       Intent enviar = new Intent(this, CrearReunionActivity.class);
       enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
       startActivity(enviar);
    }

    @Override
    public void onReunionSelecionado(DTReunion DTReunion) {

        this.DTReunion = DTReunion;
        DetalleReunionFragment frgReunionFragment =
                (DetalleReunionFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        if(frgReunionFragment != null){
            frgReunionFragment.mostrarReunion(DTReunion);
        } else{
            Intent enviarEvento = new Intent(this, DetalleReunionActivity.class);
            enviarEvento.putExtra(EXTRA_REUNION, DTReunion);
            startActivity(enviarEvento);
        }

    }
}