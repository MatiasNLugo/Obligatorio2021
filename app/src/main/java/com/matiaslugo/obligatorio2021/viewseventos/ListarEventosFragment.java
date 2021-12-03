package com.matiaslugo.obligatorio2021.viewseventos;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaEvento;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ListarEventosFragment extends Fragment {

    public static ListarEventosFragment newInstance(){

        return new ListarEventosFragment();

    }

    private FloatingActionButton btnAgregar;
    private GridView gv;
    private SearchView searchView;
    private AdaptadorEventos adapter;
    private ArrayList<DTEvento> eventos = new ArrayList<DTEvento>();
    private PersistenciaEvento persistenciaEvento;
    private View view;
    protected OnEventoSeleccionadoListener onEventoSelecccionadoListener;

    public ListarEventosFragment(){}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnEventoSeleccionadoListener){
            onEventoSelecccionadoListener = (OnEventoSeleccionadoListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listar_evento,container,false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gv = (GridView) getView().findViewById(R.id.gvEventos);

        try {
            eventos = FabricaLogica.getControladorMantenimientoEvento(getContext()).listaEvento();
        } catch (ExcepcionPersonalizada excepcionPersonalizada) {
            excepcionPersonalizada.printStackTrace();
        }
        AdaptadorEventos adapter = new AdaptadorEventos(getContext(), eventos);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    gvEventoOnItemClick(parent,view,position,id);
                } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                    excepcionPersonalizada.printStackTrace();
                }
            }
        });


    }



    public void gvEventoOnItemClick(AdapterView<?> parent, View view, int position, long id) throws ExcepcionPersonalizada {
        if(onEventoSelecccionadoListener != null){
            onEventoSelecccionadoListener.onEventoSeleccionado(
                    (DTEvento)parent.getItemAtPosition(position));
        }
    }

    public interface OnEventoSeleccionadoListener{
        void onEventoSeleccionado(DTEvento evento) throws ExcepcionPersonalizada;
    }

}