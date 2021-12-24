package com.matiaslugo.obligatorio2021.presentacion.viewclientes;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;

public class ClienteCrearActivity extends MenuActivity {

    protected EditText etCedula,etNombre,etRut,etRazonSocial,etDireccion,etTelefono,etCorreo;
    protected Button btnAgregar;
    protected DTCliente unCliente;
    protected RadioButton rbParticular;
    protected MenuItem mniModificar,mniEliminar,mniOpciones,mniReunion,mniGastos,mniTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crear);


        cargarViews();
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setBtnAgregarOnClick(v);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    excepcionPersonalizada.printStackTrace();
                }
            }
        });

    }

    protected boolean verificarCampos(View v){
        String cedula, rut;
        String nombre,razonSocial,direccion,telefono,correo;

        cedula = etCedula.getText().toString().trim();
        rut = etRut.getText().toString().trim();
        nombre = etNombre.getText().toString().trim();
        razonSocial = etRazonSocial.getText().toString().trim();
        direccion = etDireccion.getText().toString().trim();
        telefono = etTelefono.getText().toString().trim();
        correo = etCorreo.getText().toString().trim();


        boolean retorno = true;


        if(rbParticular.isChecked()){

            if(nombre.trim().isEmpty()){
                etNombre.setError("Debe ingrsar un Nombre");
                return false; }
            if(cedula.trim().isEmpty()){
                etCedula.setError("Debe ingresar una Cédula");
                return false; }
            unCliente = new DTParticular();
            ((DTParticular)unCliente).setCedula(cedula);
            ((DTParticular)unCliente).setNombre(nombre);

        } else {
            if(razonSocial.trim().isEmpty()){
                etNombre.setError("Debe ingrsar una Razon Social");
                return false; }
            if(rut.trim().isEmpty()){
                etCedula.setError("Debe ingresar número de Rut");
                retorno = false; }
            unCliente = new DTComercial();
            ((DTComercial)unCliente).setRut(rut);
            ((DTComercial)unCliente).setRazonSocial(razonSocial);

        }
         if(direccion.isEmpty()){
             etDireccion.setError("Debe ingresar Dirección");
             retorno = false;
         }
        if(telefono.isEmpty()){
            etTelefono.setError("Debe ingresar un Teléfono");
            retorno = false;
        }
        if(correo.isEmpty()){
            etCorreo.setError("Debe ingresar un correo electrónico.");
            retorno = false;
        }

        unCliente.setDireccion(direccion);
        unCliente.setTelefono(telefono);
        unCliente.setCorreo(correo);
        return retorno;
    }

    protected void setBtnAgregarOnClick(View v) throws ExcepcionPersonalizada {

        if(verificarCampos(v)){
            FabricaLogica.getControladorMantenimientoCliente(getApplicationContext()).insertarCliente(unCliente);
            Toast.makeText(this,"Cliente agregado con éxito.", Toast.LENGTH_LONG).show();
            Intent enviarCliente = new Intent(this, DetalleClienteActivity.class);
            startActivity(enviarCliente);
        }
    }

    protected void cargarViews(){
        etCedula = findViewById(R.id.etCedula);
        etNombre = findViewById(R.id.etNombre);
        etRut = findViewById(R.id.etRut);
        etRazonSocial = findViewById(R.id.etRazonSocial);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);

        etRut.setVisibility(View.GONE);
        etRazonSocial.setVisibility(View.GONE);

        btnAgregar = findViewById(R.id.btnAgregarCliente);
        rbParticular = findViewById(R.id.rbtnParticular);



    }

    public void rgSeleccionCliente(View view) {
       boolean activado = ((RadioButton)view).isChecked();


        switch (view.getId()){
            case R.id.rbtnParticular:
                etRut.setVisibility(View.GONE);
                etRazonSocial.setVisibility(View.GONE);

                etCedula.setVisibility(View.VISIBLE);
                etNombre.setVisibility(View.VISIBLE);

                break;

            case R.id.rbtnComercial:
                etCedula.setVisibility(View.GONE);
                etNombre.setVisibility(View.GONE);

                etRut.setVisibility(View.VISIBLE);
                etRazonSocial.setVisibility(View.VISIBLE);
                break;
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
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