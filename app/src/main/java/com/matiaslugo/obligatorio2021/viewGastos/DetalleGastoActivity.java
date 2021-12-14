package com.matiaslugo.obligatorio2021.viewGastos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.DetalleReunionFragment;
import com.matiaslugo.obligatorio2021.viewreuniones.ModificarReunionActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

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
        switch(item.getItemId()) {
            case R.id.mniModificar:
                enviar = new Intent(this, ModificarGastoActivity.class);
                enviar.putExtra(Constantes.EXTRA_GASTO, gasto);
                startActivity(enviar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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