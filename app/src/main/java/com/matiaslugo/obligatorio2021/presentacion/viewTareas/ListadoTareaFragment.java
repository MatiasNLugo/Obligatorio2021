package com.matiaslugo.obligatorio2021.presentacion.viewTareas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListadoTareaFragment extends Fragment {

    public static ListadoTareaFragment newInstance(){

        return new ListadoTareaFragment();

    }

    private int idEvento;
    private View view;
    private AdaptadorTareas adapter;
    private ListView lv;
    private CheckBox chkRealizado;
    private ArrayList<DTTarea> tareas = new ArrayList<DTTarea>();
    protected OnTareaSeleccionadoListener onTareaSeleccionadoListener;

    public ListadoTareaFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt(Constantes.ID_EVENTO, 1);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnTareaSeleccionadoListener){
            onTareaSeleccionadoListener = (OnTareaSeleccionadoListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado_tarea,container,false);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try{

            lv = (ListView) getView().findViewById(R.id.lvTareas);

            lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            lv.setItemChecked(2, true);


            tareas = FabricaLogica.getControladorMantenimientoTarea(getContext()).listaTareas(idEvento);

            adapter = new AdaptadorTareas(getContext(), tareas);
            lv.setAdapter(adapter);

        } catch (Exception ex){
            try {
                throw new ExcepcionPersonalizada("No se pudo listar las tareas.");
            } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                excepcionPersonalizada.printStackTrace();
            }
        }


        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    if(checked){
                        Log.i("MIS_LOGS","Se selecciono" + lv.getItemAtPosition(position));
                    } else {
                        Log.i("MIS_LOGS","Se des selecciono" + lv.getItemAtPosition(position));
                }

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_contextual_lista ,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
               mode.setTitle("Marcar Tareas");
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
               switch(item.getItemId()){
                   case R.id.mniMostrarSeleccion:
                       return true;
                   default:
                       return false;
               }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SparseBooleanArray sp = lv.getCheckedItemPositions();

                String str="";
                for(int i=0;i<sp.size();i++)
                {
                    str+=tareas.get(sp.keyAt(i)).getDescipcion()+",";
                    //FabricaLogica.getControladorMantenimientoTarea(getContext()).cambiarEstadoTarea((DTTarea)tareas.get(sp.keyAt(i)));
                    lv.setItemChecked(i,sp.valueAt(i));
                }

                Toast.makeText(getContext(), ""+str, Toast.LENGTH_SHORT).show();

                //Toast.makeText(getContext(), "Se selecciono ItemClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lvTareaOnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(onTareaSeleccionadoListener != null){
            onTareaSeleccionadoListener.onTareaSeleccionado(
                    (DTTarea)parent.getItemAtPosition(position));

        }
    }

    public interface OnTareaSeleccionadoListener{
        void onTareaSeleccionado(DTTarea tarea);
    }

}
