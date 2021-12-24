package com.matiaslugo.obligatorio2021.presentacion.viewreuniones;

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
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;

public class ReunionMantenimiento extends MenuActivity
        implements ListadoReunionFragment.OnReunionSeleccionadoListener{

    private DTEvento evento;
    private DTReunion reunion;
    private int idEvento;
    private MenuItem mniReunion,mniGastos,mniTareas, mniModificar,mniEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_mantenimiento);

        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
        agregarFragmento();
    }

    public void agregarFragmento(){
        ListadoReunionFragment listadoReunionFragment = new ListadoReunionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.ID_EVENTO,evento.getIdEvento());
        listadoReunionFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoReuniones, listadoReunionFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                return super.onOptionsItemSelected(item);
    }

    public void btnOnClickAgregarReunion(View view) {
       Intent enviar = new Intent(this, CrearReunionActivity.class);
       enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
       startActivity(enviar);
    }

    @Override
    public void onReunionSelecionado(DTReunion reunion) {

        this.reunion = reunion;
        DetalleReunionFragment frgReunionFragment =
                (DetalleReunionFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        if(frgReunionFragment != null){
            frgReunionFragment.mostrarReunion(reunion);
        } else{
            Intent enviarEvento = new Intent(this, DetalleReunionActivity.class);
            enviarEvento.putExtra(Constantes.EXTRA_REUNION, reunion);
            startActivity(enviarEvento);
        }

    }


}