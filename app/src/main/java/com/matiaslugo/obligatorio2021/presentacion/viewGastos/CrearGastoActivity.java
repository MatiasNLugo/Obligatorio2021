package com.matiaslugo.obligatorio2021.presentacion.viewGastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;

public class CrearGastoActivity extends AppCompatActivity {

    private EditText etProveedor, etMonto, etMotivo;
    private Button btnAgregar;
    private DTEvento evento;
    private DTGasto gasto;
    private MenuItem mniModificar, mniEliminar,mniReunion,mniTareas,mniGastos,mniOpciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gasto);



        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
        cargarView();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnOnClickAgregarGasto(v);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    excepcionPersonalizada.printStackTrace();
                }
            }
        });
    }

    private void cargarView(){
        etProveedor = findViewById(R.id.etProveedor);
        etMonto = findViewById(R.id.etMonto);
        etMotivo = findViewById(R.id.etMotivo);
        btnAgregar = findViewById(R.id.btnAgregarGasto);
    }

    public void btnOnClickAgregarGasto(View view) throws ExcepcionPersonalizada {
        try {
            verificarCampos();
        gasto = new DTGasto();
        gasto.setUnEvento(evento);
        gasto.setMonto(Float.parseFloat(etMonto.getText().toString()));
        gasto.setMotivo(etMotivo.getText().toString().trim());
        gasto.setProveedor(etProveedor.getText().toString().trim());



            FabricaLogica.getControladorMantenimientoGasto(this).insertarGasto(gasto);

            Toast.makeText(getApplicationContext(), "Gasto Agregado con Éxito.", Toast.LENGTH_SHORT).show();
            Intent enviar = new Intent(this, GastoMantenimientoActivity.class);
            enviar.putExtra(Constantes.EXTRA_EVENTO, evento);
            startActivity(enviar);
        } catch(ExcepcionPersonalizada excepcionPersonalizada){
            Toast.makeText(this, excepcionPersonalizada.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "No se pudo crear el gasto", Toast.LENGTH_LONG).show();

        }
    }

    private void verificarCampos(){
        String motivo, proveedor, monto;
        motivo = etMotivo.getText().toString();
        proveedor = etProveedor.getText().toString().trim();
        monto = etMonto.getText().toString().trim();

        if(motivo.isEmpty()) etMotivo.setError("Debe ingresar un Motivo.");
        if(proveedor.isEmpty()) etProveedor.setError("Debe ingresar un Proveedor.");
        if(monto.isEmpty()) etMonto.setError("Debe ingresar un Monto.");
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);

        return true;

    }






}