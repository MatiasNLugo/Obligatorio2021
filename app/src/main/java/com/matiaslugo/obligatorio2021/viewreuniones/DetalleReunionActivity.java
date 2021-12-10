package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;


public class DetalleReunionActivity extends MenuActivity {

    private DetalleReunionFragment frgDetalleReunion;
    private DTReunion reunion;
    private DTEvento evento;
    private MenuItem mniModificar,mniEliminar,mniReuniones;
    private Intent enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reunion);

        frgDetalleReunion = (DetalleReunionFragment)
                getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        reunion = (DTReunion)
                getIntent().getSerializableExtra(Constantes.EXTRA_REUNION);
        evento = (DTEvento)
                getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleReunion.mostrarReunion(reunion);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarReunionActivity.class);
                enviar.putExtra(Constantes.EXTRA_EVENTO, evento);
                startActivity(enviar);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(true);
        mniReuniones = menu.findItem(R.id.mniReuniones).setVisible(false);
        return true;
    }

}