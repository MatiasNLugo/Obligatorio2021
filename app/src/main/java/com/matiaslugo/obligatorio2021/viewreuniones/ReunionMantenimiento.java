package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteCrearActivity;
import com.matiaslugo.obligatorio2021.viewclientes.DetalleClienteFragment;
import com.matiaslugo.obligatorio2021.viewseventos.DetalleEventoActivity;
import com.matiaslugo.obligatorio2021.viewseventos.DetalleEventoFragment;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

public class ReunionMantenimiento extends MenuActivity
        implements ListadoReunionFragment.OnReunionSeleccionadoListener{

    public static final String EXTRA_REUNION = "EXTRA_REUNION";
    private Evento evento;
    private Reunion reunion;
    private MenuItem mniReunion,mniGastos,mniTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_mantenimiento);

        evento = (Evento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);

        ListadoReunionFragment listadoReunionFragment = new ListadoReunionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("idEvento",evento.getIdEvento());

        listadoReunionFragment.setArguments(bundle);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frmListadoReuniones,listadoReunionFragment,null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        DetalleClienteFragment frgDetalleCliente = (DetalleClienteFragment) getSupportFragmentManager().findFragmentById(R.id.frmClienteDetalle);
        if(frgDetalleCliente == null){
            getMenuInflater().inflate(R.menu.menu_main,menu);
            mniReunion = menu.findItem(R.id.mniReuniones).setVisible(true);
            mniGastos = menu.findItem(R.id.mniGastos).setVisible(true);
            mniTareas = menu.findItem(R.id.mniTareas).setVisible(true);
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
    public void onReunionSelecionado(Reunion reunion) {

        this.reunion = reunion;
        DetalleReunionFragment frgReunionFragment =
                (DetalleReunionFragment) getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        if(frgReunionFragment != null){
            frgReunionFragment.mostrarReunion(reunion);
        } else{
            Intent enviarEvento = new Intent(this, DetalleReunionActivity.class);
            enviarEvento.putExtra(EXTRA_REUNION,reunion);
            startActivity(enviarEvento);
        }

    }
}