package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;
import com.matiaslugo.obligatorio2021.viewGastos.GastoMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewTareas.TareaMantenimientoActivity;
import com.matiaslugo.obligatorio2021.viewreuniones.ReunionMantenimiento;

public class DetalleEventoActivity extends MenuActivity {

    protected DetalleEventoFragment frgDetalleEvento;
    protected Evento evento;
    protected MenuItem mniModificar,mniEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

       frgDetalleEvento = (DetalleEventoFragment) getSupportFragmentManager().findFragmentById(R.id.frmEventoDetalle);
       evento = (Evento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);
         }


    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleEvento.mostrarEvento(evento);
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
            case R.id.mniGastos:
                enviar = new Intent(this, GastoMantenimientoActivity.class);
                enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
                startActivity(enviar);
                return true;
            case R.id.mniTareas:
                enviar = new Intent(this, TareaMantenimientoActivity.class);
                enviar.putExtra(EventoMantenimiento.EXTRA_EVENTO,evento);
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

        return true;
    }
}



