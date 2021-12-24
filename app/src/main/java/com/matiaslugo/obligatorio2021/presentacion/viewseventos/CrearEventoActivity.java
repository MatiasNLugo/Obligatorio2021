package com.matiaslugo.obligatorio2021.presentacion.viewseventos;

import androidx.annotation.NonNull;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.presentacion.viewclientes.AdaptadorClientes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CrearEventoActivity extends MenuActivity {

    private MenuItem mniModificar, mniEliminar,mniReunion,mniTareas,mniGastos,mniOpciones;
    private ArrayList<DTCliente> clientes;
    private Button btnAgregarCliente;
    private EditText etTitulo,etFecha, etHora,etAsistentes;
    private Spinner spClientes,spDuracion, spTipoEvento;
    private int dia,mes, ano, hora,minutos;
    private int media = 30;
    private ListView lvClientes;
    private AdaptadorClientes adapter;
    private DTEvento evento = new DTEvento();
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

    private boolean veificarCampos(){
        boolean verificado = false;
        String titulo,fecha,hora, asistentes, cliente,duracion,tipoEvento;
        titulo = etTitulo.getText().toString().trim();
        fecha = etFecha.getText().toString().trim();
        hora = etHora.getText().toString().trim();
        asistentes = etAsistentes.getText().toString().trim();
        cliente = spClientes.getSelectedItem().toString().trim();
        duracion = spDuracion.getSelectedItem().toString().trim();
        tipoEvento = spTipoEvento.getSelectedItem().toString().trim();

        if(titulo.isEmpty()){
            etTitulo.setError("Debe ingresar un Titulo.");
            return false;
        }
        if(fecha.isEmpty()) {
            etFecha.setError("Debe seleccionar una Fecha.");
            return false;
        }

        if(hora.isEmpty()){
            etHora.setError("Debe ingresar una Hora");
            return false;
        }
        if(asistentes.isEmpty()){
            etAsistentes.setError("Debe ingresar cantidad de asistentes");
            return false;
        }
        try{

            Date fechaFormto =  new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            int numero = Integer.parseInt(asistentes);
            if (numero < 0 ) throw new ExcepcionPersonalizada("Los asistentes deben ser positivo.");
            numero = Integer.parseInt(duracion);
            if (numero < 0 ) throw new ExcepcionPersonalizada("La duración deben ser positiva.");
            if(cliente.isEmpty()) throw new ExcepcionPersonalizada("Debe seleccionar un Cliente.");
            if(tipoEvento.isEmpty()) throw new ExcepcionPersonalizada("Debe seleccionar el Tipo de Evento.");

            verificado = true;
            return verificado;

        } catch (ExcepcionPersonalizada e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
        catch (NumberFormatException ex){
            Toast.makeText(this, "La cantidad de asistentes debe ser un número.", Toast.LENGTH_LONG).show();
            return false;

        } catch (ParseException e) {
            Toast.makeText(this, "Error en el formato de fecha (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void spAgregarSpinners() {
        try {
            clientes = FabricaLogica.getControladorMantenimientoCliente(getApplicationContext()).listaClientes();

            for (DTCliente item : clientes) {
                ids.add(item.getIdCliente());
                if (item instanceof DTParticular) {
                    nombres.add(((DTParticular) item).getNombre());

                } else {
                    nombres.add(((DTComercial) item).getRazonSocial());
                }

            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nombres);
            spClientes.setAdapter(adapter);
        } catch(ExcepcionPersonalizada excepcionPersonalizada){
            Toast.makeText(this, excepcionPersonalizada.getMessage(), Toast.LENGTH_LONG).show();
        }

       for(int i=30 ; i < 500; i+= 30){
               horas.add(String.valueOf(i));
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
                StringBuilder unaHora = new StringBuilder() ;
                if(hourOfDay < 10 ) {
                    unaHora.append("0").append(hourOfDay).append(":");
                } else {
                    unaHora.append(hourOfDay).append(":");
                }
                if (minute < 10) {
                       unaHora.append("0").append(minute);
                   } else {
                    unaHora.append(minute);
               }
                etHora.setText(unaHora);
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
        //Toast.makeText(this,"Click en EditText Fecha",Toast.LENGTH_SHORT).show();
    }

    public void btnOnClickAgregarEvento(View view) throws ExcepcionPersonalizada {
       try {
           if (veificarCampos()) {
               evento.setTitulo(etTitulo.getText().toString().trim());
               evento.setFecha(etFecha.getText().toString());
               evento.setHora(etHora.getText().toString());
               evento.setDuracion(spDuracion.getSelectedItem().toString());
               evento.setCantAsistentes(Integer.parseInt(etAsistentes.getText().toString()));
               if (evento.getIdCliente() < 0) {
                   Toast.makeText(this, "Debe ingresar un cliente.", Toast.LENGTH_SHORT).show();
               }
               switch (spTipoEvento.getSelectedItem().toString()) {
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

               FabricaLogica.getControladorMantenimientoEvento(getApplicationContext()).insertarEvento(evento);
               Toast.makeText(this, "Evento creado con exito.", Toast.LENGTH_SHORT).show();
               Intent enviarCliente = new Intent(this, EventoMantenimiento.class);
               startActivity(enviarCliente);

           }
       } catch (ExcepcionPersonalizada ex ){
           Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

       } catch (Exception e){
           Toast.makeText(this, "Error al crear el Evento", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        setTitle("Crear Evento");
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        mniReunion = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);

        return true;
    }
}