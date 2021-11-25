package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;
import com.matiaslugo.obligatorio2021.db.DbEventos;
import com.matiaslugo.obligatorio2021.viewclientes.AdaptadorClientes;

import java.util.ArrayList;
import java.util.Calendar;

public class CrearEventoActivity extends AppCompatActivity {

    private ArrayList<Cliente> clientes;
    private Button btnAgregarCliente;
    private EditText etTitulo,etFecha, etHora,etAsistentes;
    private Spinner spClientes,spDuracion, spTipoEvento;
    private int dia,mes, ano, hora,minutos;
    private int media = 30;
    private ListView lvClientes;
    private AdaptadorClientes adapter;
    private Evento evento = new Evento();
    private ArrayList<Integer> ids = new ArrayList<Integer>();
    private ArrayList<String> horas = new ArrayList<String>();
    private ArrayList<String> nombres = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_evento);



        spTipoEvento = findViewById(R.id.spTipoEvento);
        spClientes = findViewById(R.id.spClientes);
        btnAgregarCliente = findViewById(R.id.btnAgregarCliente);
        etTitulo = findViewById(R.id.etTitulo);
        etFecha = findViewById(R.id.etFecha);
        etHora = findViewById(R.id.etHora);
        spDuracion = findViewById(R.id.spDuracion);
        etAsistentes = findViewById(R.id.etAsistentes);
        spAgregarSpinners();



        spClientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setOnClienteItemClick(parent,view,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etFechaOnClick(v);
            }
        });

        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etHoraOnClick(v);
            }
        });

    }

    private void setOnClienteItemClick(AdapterView<?> parent, View view, int position, long id) {
        evento.setIdCliente(ids.get(position));
    }

    public void spAgregarSpinners() {
        DbClientes dbClientes = new DbClientes(this);
        clientes = dbClientes.listaClientes();

        for (Cliente item : clientes) {
            ids.add(item.getIdCliente());
            if (item instanceof Particular) {
                nombres.add(((Particular) item).getNombre());

            }else{
                nombres.add(((Comercial)item).getRazonSocial());
        }

    }

       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nombres);
       spClientes.setAdapter(adapter);
       dbClientes.close();

       for(int i=1 ; i < 100; i++){
           if(i<10){
           String hora = "0" + i ;
           horas.add(hora);
           } else{
               horas.add(String.valueOf(i));
           }
       }
        ArrayAdapter<String> adapterDuracion = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,horas);
        spDuracion.setAdapter(adapterDuracion);




    }

    public void etHoraOnClick(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etHora.getWindowToken(), 0);


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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etFecha.setText(dayOfMonth +"/"+ (month + 1) +"/"+ year);
            }
        },dia,mes,ano);
        datePickerDialog.show();
        Toast.makeText(this,"Click en EditText Fecha",Toast.LENGTH_SHORT).show();
    }

    public void btnOnClickAgregarEvento(View view) {
        evento.setTitulo(etTitulo.getText().toString());
        evento.setFecha(etFecha.getText().toString());
        evento.setHora(etHora.getText().toString());
        evento.setDuracion(spDuracion.getSelectedItem().toString());
        evento.setCantAsistentes(Integer.parseInt(etAsistentes.getText().toString()));
        if(evento.getIdCliente() < 0 ){
            Toast.makeText(this,"Debe ingresar un cliente.",Toast.LENGTH_SHORT).show();
        }
        switch (spTipoEvento.getSelectedItem().toString()){
            case "Familiar":
                evento.setTipo(1);
                break;
            case "Empresarial":
                evento.setTipo(2);
                break;
            case "Deportivo":
                evento.setTipo(3);
                break;
            case "Social":
                evento.setTipo(4);
                break;
            case "Politico":
                evento.setTipo(5);
                break;

        }


        DbEventos dbEventos = new DbEventos(this);
        dbEventos.insertarEvento(evento);

        Toast.makeText(this,"Evento creado con exito.",Toast.LENGTH_SHORT).show();
    }


}