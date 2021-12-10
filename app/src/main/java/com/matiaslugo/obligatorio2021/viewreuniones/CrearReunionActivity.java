package com.matiaslugo.obligatorio2021.viewreuniones;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaReunion;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CrearReunionActivity extends MenuActivity {

    private EditText etDescripcion,etObjetivo,etFecha, etHora, etLugar;
    private CheckBox chkAvisar;
    private Button btnAgregar;
    private Date fecha;
    private int dia,mes, ano, hora,minutos;
    private DTReunion reunion;
    private DTEvento evento;
    private MenuItem mniModificar, mniEliminar,mniReunion,mniTareas,mniGastos,mniOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reunion);
        cargarView();

        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnOnClickAgregarReunion(v);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    excepcionPersonalizada.printStackTrace();
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

        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etHora.getWindowToken(), 0);
                etHoraOnClick(v);
            }
        });
    }

    private void cargarView(){
        etDescripcion = findViewById(R.id.etDescripcion);
        etObjetivo = findViewById(R.id.etObjetivo);
        etFecha = findViewById(R.id.etFecha);
        etHora =  findViewById(R.id.etHora);
        etLugar = findViewById(R.id.etLugar);
        btnAgregar = findViewById(R.id.btnAgregarReunion);
        chkAvisar = findViewById(R.id.chkAvisar);

    }

    public void etHoraOnClick(View view) {


        final Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(minute < 10){
                    etHora.setText(hourOfDay +":0"+ (minute) );
                } else {
                    etHora.setText(hourOfDay +":"+ (minute));
                }
            }
        },hora,minutos,false);
        timePickerDialog.show();

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

    public void btnOnClickAgregarReunion(View view) throws ExcepcionPersonalizada {
        try {
            reunion = new DTReunion();
            reunion.setDescripcion(etDescripcion.getText().toString());
            reunion.setObjetivo(etObjetivo.getText().toString());
            reunion.setFecha(etFecha.getText().toString());
            reunion.setHora(etHora.getText().toString());
            reunion.setLugar(etLugar.getText().toString());
            reunion.setNotificar(chkAvisar.isChecked());
            reunion.setEvento(evento);

            verificarCampos();
            FabricaLogica.getControladorMantenimientoReunion(getApplicationContext()).insertarReunion(reunion);

            Toast.makeText(this, "Reunión creada con éxito.", Toast.LENGTH_LONG).show();
        } catch(ExcepcionPersonalizada ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(this, "Error al crear la reunion.", Toast.LENGTH_LONG).show();
        }

    }

    private void verificarCampos() {
        String descripcion,objetivo,fecha, hora, lugar;
        descripcion = etDescripcion.getText().toString();
        objetivo = etObjetivo.getText().toString();
        lugar = etLugar.getText().toString();



        fecha = etFecha.getText().toString();

        if (descripcion.isEmpty()) etDescripcion.setError("Debe ingresar una descripción.");
        if (objetivo.isEmpty()) etObjetivo.setError("Debe ingresar un obetivo.");
        if (fecha.isEmpty()) etFecha.setError("Debe ingresar una fecha.");
        if (lugar.isEmpty()) etLugar.setError("Debe ingresar un lugar.");
        try{
            Date fechaModificada = (new SimpleDateFormat("dd/MM/yyyy")).parse(fecha);
        } catch (ParseException e) {
            Toast.makeText(this, "Error en el formato de fecha (dd/MM/yyyy)", Toast.LENGTH_LONG).show();
        }


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