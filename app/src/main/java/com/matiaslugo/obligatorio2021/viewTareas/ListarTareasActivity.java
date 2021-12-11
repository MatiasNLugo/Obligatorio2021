package com.matiaslugo.obligatorio2021.viewTareas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTareaListItem;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListarTareasActivity extends MenuActivity {

    private ArrayList<DTTarea> tareas = new ArrayList<>();
    private String[] tareasText;
    private DTEvento   evento;
    private DTTarea unaTarea;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private MenuItem mniModificar,mniEliminar,mniGastos,mniTareas,mniReuniones, mniOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tareas);

        evento = (DTEvento) getIntent().getSerializableExtra(Constantes.EXTRA_EVENTO);
        cargarLista();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        SparseBooleanArray posicionesSeleccioandas = lv.getCheckedItemPositions();

                        for (int i = 0; i < posicionesSeleccioandas.size(); i++) {
                            if (posicionesSeleccioandas.valueAt(i)) {
                                unaTarea = tareas.get(i);
                                unaTarea.setRealizada(true);
                            } else {
                                unaTarea = tareas.get(i);
                                unaTarea.setRealizada(false);
                            }
                            try {
                                FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).cambiarEstadoTarea(unaTarea);
                            } catch (ExcepcionPersistencia excepcionPersistencia) {
                                excepcionPersistencia.printStackTrace();
                            }

                        }

            }
        });







        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                try {
                    FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).cambiarEstadoTarea(tareas.get(position));
                    Toast.makeText(getApplicationContext(), "Tarea Modificada !", Toast.LENGTH_SHORT).show();
                } catch (ExcepcionPersistencia excepcionPersistencia) {
                    Toast.makeText(getApplicationContext(), excepcionPersistencia.getMessage(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_contextual_lista,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                setTitleColor(R.color.fondoLista);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.mniModificar:

                        SparseBooleanArray posicionesSeleccioandas = lv.getCheckedItemPositions();

                        for (int i = 0; i < posicionesSeleccioandas.size(); i++) {
                            if (posicionesSeleccioandas.valueAt(i)) {
                                 unaTarea = tareas.get(i);
                                unaTarea.setRealizada(true);
                            } else {
                                 unaTarea = tareas.get(i);
                                unaTarea.setRealizada(false);
                            }
                                try {
                                    FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).cambiarEstadoTarea(unaTarea);
                                    Toast.makeText(getApplicationContext(), "Tarea Modificada.", Toast.LENGTH_LONG).show();
                                } catch (ExcepcionPersistencia excepcionPersistencia) {
                                    excepcionPersistencia.printStackTrace();
                                }

                        }

                        return true;

                    default:
                        return false;

                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent enviar = new Intent(this, EventoMantenimiento.class);
        startActivity(enviar);

    }

    public void cargarLista(){
        try {
            tareas  = FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).listaTareas(evento.getIdEvento());
        } catch (ExcepcionPersistencia excepcionPersistencia) {
            Toast.makeText(this, excepcionPersistencia.getMessage(), Toast.LENGTH_LONG).show();;
        }


/*
       StringBuilder stringBuilder = new StringBuilder();
        String[][] tareasListar = new String[tareas.size()][];
        for(DTTarea item : tareas){

            stringBuilder.append("{");
            String descripcion = "Descripción: " + item.getDescipcion();
            String fechaLimite = "Fecha Limite: " + item.getFechaLimite();
            stringBuilder.append(descripcion).append(",").append(fechaLimite);
            if(tareas.size()-1 == tareas.indexOf(item)){
                stringBuilder.append("}}");
            } else {
                stringBuilder.append("},");
            }
            //String[] enlazar = new String[]{"Descripción: ", descripcion, "Fecha Limite: " + fechaLimite};

            tareasListar[tareas.indexOf(item)][tareas.indexOf(item)] = stringBuilder.toString();

          ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(getApplicationContext(),android.R.layout.simple_list_item_checked,tareasListar);
        lv.setAdapter(adapter);

        }*/






        lv = findViewById(R.id.lvTareas);

        tareasText = new String[tareas.size()];
        for (DTTarea item : tareas) {

                tareasText[tareas.indexOf(item)] = item.getDescipcion() + " ( Fecha Limite : " + item.getFechaLimite() + " )" ;
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,tareasText);
        lv.setAdapter(adapter);

        for (DTTarea item : tareas){
                lv.setItemChecked(tareas.indexOf(item),item.isRealizada());
                lv.setSelection(tareas.indexOf(item));

        }

    }



    public void btnOnClickAgregarTarea(View view) {
        Intent enviar = new Intent(this, CrearTareaActivity.class);
        enviar.putExtra(Constantes.EXTRA_EVENTO,evento);
        startActivity(enviar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setTitle("Lista de Tareas");
        getMenuInflater().inflate(R.menu.menu_main,menu);
        mniModificar = menu.findItem(R.id.mniModificar).setVisible(false);
        mniEliminar = menu.findItem(R.id.mniEliminar).setVisible(false);
        mniTareas = menu.findItem(R.id.mniTareas).setVisible(false);
        mniGastos = menu.findItem(R.id.mniGastos).setVisible(false);
        mniReuniones = menu.findItem(R.id.mniReuniones).setVisible(false);
        mniOpciones = menu.findItem(R.id.mniOpciones).setVisible(false);
        return true;

    }
}