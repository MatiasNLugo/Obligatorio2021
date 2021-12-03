package com.matiaslugo.obligatorio2021.viewreuniones;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaReunion;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.util.Calendar;

public class CrearReunionActivity extends MenuActivity {

    private EditText etDescripcion,etObjetivo,etFecha, etHora, etLugar;
    private CheckBox chkAvisar;
    private Button btnAgregar;
    private int dia,mes, ano, hora,minutos;
    private DTReunion DTReunion;
    private DTEvento evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reunion);
        cargarView();

        evento = (DTEvento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);

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
        DTReunion = new DTReunion();
        DTReunion.setDescripcion(etDescripcion.getText().toString());
        DTReunion.setObjetivo(etObjetivo.getText().toString());
        DTReunion.setFecha(etFecha.getText().toString());
        DTReunion.setHora(etHora.getText().toString());
        DTReunion.setLugar(etLugar.getText().toString());
        DTReunion.setNotificar(chkAvisar.isChecked());
        DTReunion.setIdEvento(evento.getIdEvento());

        FabricaLogica.getControladorMantenimientoReunion(getApplicationContext()).insertarReunion(DTReunion);


    }
}