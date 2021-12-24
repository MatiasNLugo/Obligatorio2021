package com.matiaslugo.obligatorio2021.presentacion.viewreuniones;

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


public class DetalleReunionActivity extends MenuActivity {

    private DetalleReunionFragment frgDetalleReunion;
    private DTReunion reunion;
    private DTEvento evento;
    private MenuItem mniModificar,mniEliminar,mniReuniones,mniOpciones;
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
            return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        setTitle("Reuniones");
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniReuniones = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        return true;
    }

}