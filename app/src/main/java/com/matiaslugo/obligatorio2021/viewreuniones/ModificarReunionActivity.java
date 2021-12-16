package com.matiaslugo.obligatorio2021.viewreuniones;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class ModificarReunionActivity extends MenuActivity {

    private EditText etDescripcion,etObjetivo,etFecha, etHora, etLugar;
    private CheckBox chkAvisar;
    private Button btnAgregar;
    private int dia,mes, ano, hora,minutos;
    private DTReunion reunion;
    private DTEvento evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reunion);

        evento = (DTEvento)getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
        reunion = (DTReunion)getIntent().getSerializableExtra(Constantes.EXTRA_REUNION);

        cargarView();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    btnOnClickModificarReunion(v);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    excepcionPersonalizada.printStackTrace();
                }
            }
        });
    }

    protected void cargarView(){
        etDescripcion = findViewById(R.id.etDescripcion);
        etObjetivo = findViewById(R.id.etObjetivo);
        etFecha = findViewById(R.id.etFecha);
        etHora =  findViewById(R.id.etHora);
        etLugar = findViewById(R.id.etLugar);
        btnAgregar = findViewById(R.id.btnModificarReunion);
        chkAvisar = findViewById(R.id.chkAvisar);

        etDescripcion.setText(reunion.getDescripcion());
        etObjetivo.setText(reunion.getObjetivo());
        etFecha.setText(reunion.getFecha());
        etHora.setText(reunion.getHora());
        etLugar.setText(reunion.getLugar());
        if(reunion.isNotificar()){
            chkAvisar.setChecked(true);
        } else {
            chkAvisar.setChecked(false);
        }

    }

    protected void etHoraOnClick(View view) {


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

    protected void etFechaOnClick(View view) {

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

    public void btnOnClickModificarReunion(View view) throws ExcepcionPersonalizada {
       try{

           reunion.setDescripcion(etDescripcion.getText().toString().trim());
           reunion.setObjetivo(etObjetivo.getText().toString().trim());
           reunion.setFecha(etFecha.getText().toString());
           reunion.setHora(etHora.getText().toString());
           reunion.setLugar(etLugar.getText().toString().trim());
           reunion.setNotificar(chkAvisar.isChecked());

           verificarCampos();
           FabricaLogica.getControladorMantenimientoReunion(getApplicationContext()).modificarReunion(reunion);
           Toast.makeText(this, "Reunión Modificada con éxito.", Toast.LENGTH_SHORT).show();

       } catch(ExcepcionPersonalizada ex){
           Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
       } catch (Exception e){
           Toast.makeText(this, "Error al modificar la reunion.", Toast.LENGTH_SHORT).show();
       }

       }

    private void verificarCampos() {
        String descripcion,objetivo,fecha, hora, lugar;
        descripcion = etDescripcion.getText().toString().trim();
        objetivo = etObjetivo.getText().toString().trim();
        lugar = etLugar.getText().toString().trim();

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




}