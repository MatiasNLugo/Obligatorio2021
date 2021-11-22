package com.matiaslugo.obligatorio2021.viewclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;

public class ClienteCrearActivity extends AppCompatActivity {
    protected EditText etCedula,etNombre,etRut,etRazonSocial,etDireccion,etTelefono,etCorreo;
    protected Button btnAgregar;
    protected Cliente unCliente;
    protected RadioButton rbParticular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_crear);


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

        cedula = etCedula.getText().toString();
        rut = etRut.getText().toString();
        nombre = etNombre.getText().toString();
        razonSocial = etRazonSocial.getText().toString();
        direccion = etDireccion.getText().toString();
        telefono = etTelefono.getText().toString();
        correo = etCorreo.getText().toString();


        boolean retorno = true;


        if(rbParticular.isChecked()){

            if(nombre.trim().isEmpty()){
                etNombre.setError("Debe ingrsar un Nombre");
                return false; }
            if(cedula.trim().isEmpty()){
                etCedula.setError("Debe ingresar una Cédula");
                return false; }
            unCliente = new Particular();
            ((Particular)unCliente).setCedula(cedula);
            ((Particular)unCliente).setNombre(nombre);

        } else {
            if(razonSocial.trim().isEmpty()){
                etNombre.setError("Debe ingrsar una Razon Social");
                return false; }
            if(rut.trim().isEmpty()){
                etCedula.setError("Debe ingresar número de Rut");
                retorno = false; }
            unCliente = new Comercial();
            ((Comercial)unCliente).setRut(rut);
            ((Comercial)unCliente).setRazonSocial(razonSocial);

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

    protected void setBtnAgregarOnClick(View v){

        if(verificarCampos(v)){

            DbClientes dbClientes = new DbClientes(this);
            dbClientes.insertarCliente(unCliente);
            Toast.makeText(this,"Cliente agregado con éxito.", Toast.LENGTH_LONG).show();

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
}