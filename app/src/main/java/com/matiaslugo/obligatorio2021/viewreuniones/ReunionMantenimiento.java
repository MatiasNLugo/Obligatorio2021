package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteCrearActivity;
import com.matiaslugo.obligatorio2021.viewseventos.DetalleEventoFragment;

public class ReunionMantenimiento extends MenuActivity
        implements ListadoReunionFragment.OnReunionSeleccionadoListener{

    public static final String EXTRA_REUNION = "EXTRA_REUNION";

    int idEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_mantenimiento);

        idEvento = (int)getIntent().getIntExtra("idEvento",1);

        ListadoReunionFragment listadoReunionFragment = new ListadoReunionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idEvento",idEvento);

        listadoReunionFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frmListadoReuniones,listadoReunionFragment,null);
        fragmentTransaction.commit();

    }


    public void OnClienteSeleccionado(Reunion reunion){

    }

    public void btnOnClickAgregarReunion(View view) {
       // Intent enviar = new Intent(this, ReunionCrearActivity.class);
        //startActivity(enviar);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onReunionSelecionado(Reunion reunion) {

    }
}