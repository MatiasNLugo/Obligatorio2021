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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;
import com.matiaslugo.obligatorio2021.presentacion.MenuActivity;
import com.matiaslugo.obligatorio2021.viewseventos.EventoMantenimiento;

import java.lang.reflect.Array;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListarTareasActivity extends MenuActivity {

    ArrayList<DTTarea> tareas = new ArrayList<>();
    String[] tareasText;
    DTEvento   evento;
    DTTarea unaTarea;
    ListView lv;
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
                } catch (ExcepcionPersistencia excepcionPersistencia) {
                    excepcionPersistencia.printStackTrace();
                }


            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_contextual_lista,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                setTitle("Tareas Realizadas");
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

    public void cargarLista(){
        try {
            tareas  = FabricaLogica.getControladorMantenimientoTarea(getApplicationContext()).listaTareas(evento.getIdEvento());
        } catch (ExcepcionPersistencia excepcionPersistencia) {
            excepcionPersistencia.printStackTrace();
        }

        lv = findViewById(R.id.lvTareas);

        tareasText = new String[tareas.size()];
        for (DTTarea item : tareas) {

                tareasText[tareas.indexOf(item)] = item.getDescipcion() + " ( Fecha Limite : " + item.getFechaLimite() + " )" ;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,tareasText);
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
}