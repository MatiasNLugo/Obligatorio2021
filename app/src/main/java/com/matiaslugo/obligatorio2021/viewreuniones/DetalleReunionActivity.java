package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;


public class DetalleReunionActivity extends MenuActivity {

    private DetalleReunionFragment frgDetalleReunion;
    private DTReunion DTReunion;
    private DTEvento evento;
    private MenuItem mniModificar,mniEliminar,mniReuniones;
    private Intent enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reunion);

        frgDetalleReunion = (DetalleReunionFragment)getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        DTReunion = (DTReunion)getIntent().getSerializableExtra(ReunionMantenimiento.EXTRA_REUNION);
        evento = (DTEvento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleReunion.mostrarReunion(DTReunion);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarReunionActivity.class);
                enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO, evento);
                enviar.putExtra(ReunionMantenimiento.EXTRA_REUNION, DTReunion);
                startActivity(enviar);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniReuniones = menu.findItem(R.id.mniReuniones).setVisible(false);
        return true;
    }

}