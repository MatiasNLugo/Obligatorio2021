package com.matiaslugo.obligatorio2021.viewTareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.CrearReunionActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.DetalleReunionFragment;
import com.matiaslugo.obligatorio2021.viewreuniones.ListadoReunionFragment;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class TareaMantenimientoActivity extends MenuActivity implements ListadoTareaFragment.OnTareaSeleccionadoListener {


    private Button btnAgregar;
    private DTEvento evento;
    private int idEvento;
    private MenuItem mniReunion,mniGastos,mniTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_mantenimiento);

        evento = (DTEvento) getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);
        agregarFragmento();


    }


    public void agregarFragmento(){
        ListadoTareaFragment listadoTareaFragment = new ListadoTareaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idEvento",evento.getIdEvento());
        listadoTareaFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoTareas, listadoTareaFragment);
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
    public void onTareaSeleccionado(DTTarea tarea) {

    }

    public void btnOnClickAgregarReunion(View view) {
        Intent enviar = new Intent(this, CrearTareaActivity.class);
        enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
        startActivity(enviar);
    }
}