package com.matiaslugo.obligatorio2021.viewGastos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewreuniones.ListadoReunionFragment;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class GastoMantenimientoActivity extends AppCompatActivity {

    private Evento evento;
    private Intent enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_mantenimiento);

        evento = (Evento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);
        ListadoGastoFragment listadoGastoFragment = new ListadoGastoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idEvento",evento.getIdEvento());

        listadoGastoFragment.setArguments(bundle);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoGastos,listadoGastoFragment,null);
        fragmentTransaction.commit();
    }


    public void btnOnClickAgregarGasto(View view) {
        enviar = new Intent(this, CrearGastoActivity.class);
        enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
        startActivity(enviar);
    }
}