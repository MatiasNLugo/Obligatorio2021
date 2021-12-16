package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
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
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaCliente;

public class ClienteModificarActivity extends MenuActivity {

    protected EditText etCedula,etNombre,etRut,etRazonSocial,etDireccion,etTelefono,etCorreo;
    protected Button btnAgregar;
    protected DTCliente unCliente;
    protected Integer idCliente;
    protected RadioButton rbParticular, rbComercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_modificar);

        unCliente = (DTCliente)getIntent().getSerializableExtra(ClienteMantenimiento.EXTRA_CLIENTE);
        idCliente = unCliente.getIdCliente();
        cargarViews();


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnAgregarOnClick(v);
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
                retorno = false; }
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

        unCliente.setIdCliente(idCliente);
        unCliente.setDireccion(direccion);
        unCliente.setTelefono(telefono);
        unCliente.setCorreo(correo);
        return retorno;
    }

    protected void setBtnAgregarOnClick(View v) {
        try {
            if (verificarCampos(v)) {

                FabricaLogica.getControladorMantenimientoCliente(getApplicationContext()).modificarCliente(unCliente);
                Toast.makeText(this, "Cliente Modificado con éxito.", Toast.LENGTH_LONG).show();

                Intent enviarCliente = new Intent(this, ClienteMantenimiento.class);
                startActivity(enviarCliente);
            }
        } catch(ExcepcionPersonalizada excepcionPersonalizada){
            Toast.makeText(this, excepcionPersonalizada.getMessage(), Toast.LENGTH_LONG).show();
        } catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Error al Modificar el Cliente.", Toast.LENGTH_LONG).show();

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
        rbComercial = findViewById(R.id.rbtnComercial);


        if(unCliente instanceof DTParticular){
            rgSeleccionCliente(rbParticular);
            etCedula.setText(String.valueOf(((DTParticular)unCliente).getCedula()));
            etNombre.setText(((DTParticular) unCliente).getNombre());
        } else {
            rbComercial.setChecked(true);
            rgSeleccionCliente(rbComercial);

            etRut.setText(String.valueOf(((DTComercial)unCliente).getRut()));
            etRazonSocial.setText(((DTComercial) unCliente).getRazonSocial());
        }

        rbComercial.setEnabled(false);
        rbParticular.setEnabled(false);
        etDireccion.setText(unCliente.getDireccion());
        etTelefono.setText(unCliente.getTelefono());
        etCorreo.setText(unCliente.getCorreo());

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
}