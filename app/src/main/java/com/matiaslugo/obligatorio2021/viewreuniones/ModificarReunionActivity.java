package com.matiaslugo.obligatorio2021.viewreuniones;

import androidx.appcompat.app.AppCompatActivity;

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

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbReuniones;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.util.Calendar;

public class ModificarReunionActivity extends MenuActivity {

    private EditText etDescripcion,etObjetivo,etFecha, etHora, etLugar;
    private CheckBox chkAvisar;
    private Button btnAgregar;
    private int dia,mes, ano, hora,minutos;
    private Reunion reunion;
    private Evento evento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_reunion);

        evento = (Evento)getIntent().getSerializableExtra(EventoMantenimiento.EXTRA_EVENTO);
        reunion = (Reunion)getIntent().getSerializableExtra(ReunionMantenimiento.EXTRA_REUNION);

        cargarView();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClickModificarReunion(v);
            }
        });
    }

    private void cargarView(){
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

    public void btnOnClickModificarReunion(View view) {
        reunion.setDescripcion(etDescripcion.getText().toString());
        reunion.setObjetivo(etObjetivo.getText().toString());
        reunion.setFecha(etFecha.getText().toString());
        reunion.setHora(etHora.getText().toString());
        reunion.setLugar(etLugar.getText().toString());
        reunion.setNotificar(chkAvisar.isChecked());
        DbReuniones dbReuniones = new DbReuniones(this);
        dbReuniones.modificarReunion(reunion);
        dbReuniones.close();
    }


}