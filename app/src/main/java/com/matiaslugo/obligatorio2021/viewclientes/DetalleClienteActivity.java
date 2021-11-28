package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;

public class DetalleClienteActivity extends MenuActivity {

    protected DetalleClienteFragment frgDetalleCliente;
    protected Cliente cliente;
    protected MenuItem mniModificar,mniEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);



        frgDetalleCliente = (DetalleClienteFragment)getSupportFragmentManager()
                .findFragmentById(R.id.frmClienteDetalle);

        cliente = (Cliente)getIntent().getSerializableExtra(ClienteMantenimiento.EXTRA_CLIENTE);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frgDetalleCliente.mostrarCliente(cliente);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mniModificar:

                Intent enviarCliente  = new Intent(this, ClienteModificarActivity.class);
                enviarCliente.putExtra(ClienteMantenimiento.EXTRA_CLIENTE,cliente);
                startActivity(enviarCliente);
                return true;
            case R.id.mniEliminar:
                DbClientes dbClientes = new DbClientes(this);
                if(dbClientes.verificarDependenciaCliente(cliente.getIdCliente())){
                    //TODO HACER MENU CONTEXTUAL para verificar
                    Toast.makeText(this,
                            "No se puede eliminar el cliente, tiene eventos asociados.",
                            Toast.LENGTH_LONG).show();
                    return true;
                } else {
                dbClientes.eliminarCliente(cliente.getIdCliente());
                    Toast.makeText(this,
                            "Cliente Eliminado con éxito.",
                            Toast.LENGTH_LONG).show();

                    return true;

                }
            default: return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(true);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(true);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        invalidateOptionsMenu();
        mniModificar.setVisible(true);
        mniEliminar.setVisible(true);
    }
}