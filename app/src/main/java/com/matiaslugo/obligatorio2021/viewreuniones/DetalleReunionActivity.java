package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;


public class DetalleReunionActivity extends MenuActivity {

    private DetalleReunionFragment frgDetalleReunion;
    private Reunion reunion;
    private MenuItem mniModificar,mniEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reunion);

        frgDetalleReunion = (DetalleReunionFragment)getSupportFragmentManager().findFragmentById(R.id.frmDetallereunion);
        reunion = (Reunion)getIntent().getSerializableExtra(ReunionMantenimiento.EXTRA_REUNION);

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
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(true);
        return true;
    }

}