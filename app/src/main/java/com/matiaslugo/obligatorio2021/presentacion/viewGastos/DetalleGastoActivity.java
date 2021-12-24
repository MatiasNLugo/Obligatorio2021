package com.matiaslugo.obligatorio2021.presentacion.viewGastos;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;

public class DetalleGastoActivity extends MenuActivity {

    private DetalleGastoFragment frgDetalleGasto;
    private DTGasto gasto;
    private DTEvento evento;
    private MenuItem mniModificar,mniEliminar,mniGastos, mniOpciones;
    private Intent enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gasto);

        frgDetalleGasto = (DetalleGastoFragment)
                getSupportFragmentManager().findFragmentById(R.id.frmDetalleGastos);
        gasto = (DTGasto)
                getIntent().getSerializableExtra(Constantes.EXTRA_GASTO);
    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleGasto.mostrarGasto(gasto);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        setTitle("Detalle del Gasto");
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setEnabled(false).setVisible(false);
        return true;
    }

}