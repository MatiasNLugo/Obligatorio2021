package com.matiaslugo.obligatorio2021.viewTareas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CrearTareaActivity extends MenuActivity {

    private Button btnAgregarTarea;
    private EditText etFecha, etDescripcion;
    private int dia, ano, mes;
    private DTTarea tarea;
    private DTEvento evento;
    private MenuItem mniModificar, mniEliminar,mniReunion,mniTareas,mniGastos,mniOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        cargarView();

        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);

        btnAgregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnAgregarTareaOnClickListener(v);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    String mensaje = excepcionPersonalizada.getMessage().toString();
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                }
            }
        });



        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etFecha.getWindowToken(), 0);
                etFechaOnClick(v);
            }
        });

    }

    private void verificarCampos(){
        String fecha , descripcion;
        fecha = etFecha.getText().toString();
        descripcion = etDescripcion.getText().toString().trim();
            if (fecha.isEmpty()) etFecha.setError("Debe ingresar una fecha.");
            if(descripcion.isEmpty()) etDescripcion.setError("Debe ingresar una descripción.");

            try{
                Date fechaFormto =  new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            } catch (ParseException e) {
                Toast.makeText(this, "Formato de fecha invalido. (dd/MM/yyyy)", Toast.LENGTH_LONG).show();;
            }
    }
    private void btnAgregarTareaOnClickListener(View v) throws ExcepcionPersonalizada {
        tarea = new DTTarea();
        tarea.setDescipcion(etDescripcion.getText().toString().trim());
        tarea.setFechaLimite(etFecha.getText().toString());
        tarea.setRealizada(false);
        tarea.setUnEvento(evento);

        verificarCampos();
        FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).insertarTarea(tarea);
        Toast.makeText(getApplicationContext(), "Tarea Creada con Exito.", Toast.LENGTH_LONG).show();

        Intent enviar = new Intent(getApplicationContext(),ListarTareasActivity.class);
        enviar.putExtra(Constantes.EXTRA_EVENTO, tarea.getUnEvento());
        startActivity(enviar);


    }

    private void cargarView() {
        etFecha = findViewById(R.id.etFecha);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnAgregarTarea = findViewById(R.id.btnAgregarTarea);
    }

    public void etFechaOnClick(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etFecha.getWindowToken(), 0);


        final Calendar c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        ano = c.get(Calendar.YEAR);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this ,new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                etFecha.setText(dayOfMonth +"/"+ (month + 1) +"/"+ year);
            }

        },dia,mes,ano);
        datePickerDialog.updateDate(ano,mes,dia);
        datePickerDialog.show();
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