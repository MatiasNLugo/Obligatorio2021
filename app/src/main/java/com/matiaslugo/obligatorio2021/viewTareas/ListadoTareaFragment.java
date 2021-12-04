package com.matiaslugo.obligatorio2021.viewTareas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.viewreuniones.ListadoReunionFragment;

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
    private ArrayList<DTTarea> tareas = new ArrayList<DTTarea>();
    protected OnTareaSeleccionadoListener onTareaSeleccionadoListener;

    public ListadoTareaFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt("idEvento", 1);
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvTareaOnItemClick(parent,view,position,id);
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
