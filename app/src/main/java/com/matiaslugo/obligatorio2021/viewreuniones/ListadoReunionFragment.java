package com.matiaslugo.obligatorio2021.viewreuniones;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaReunion;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ListadoReunionFragment extends Fragment {

    public static ListadoReunionFragment newInstance(){

        return new ListadoReunionFragment();

    }

    private int idEvento;
    private FloatingActionButton btnAgregar;
    private ListView lv;
    private SearchView searchView;
    private AdaptadorReuniones adapter;
    private ArrayList<DTReunion> reuniones = new ArrayList<>();
    private View view;
    protected OnReunionSeleccionadoListener onReunionSeleccionadoListener;


    public ListadoReunionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt("idEvento",1);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnReunionSeleccionadoListener){
            onReunionSeleccionadoListener = (OnReunionSeleccionadoListener) context;
        }
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable  ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado_reunion, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {

            lv = (ListView) getView().findViewById(R.id.lvReuniones);
            reuniones = FabricaLogica.getControladorMantenimientoReunion(getContext()).listaReuniones(idEvento);

            AdaptadorReuniones adapter = new AdaptadorReuniones(getContext(), reuniones);
            lv.setAdapter(adapter);
        } catch (Exception ex){
            try {
                throw new ExcepcionPersonalizada("No se pudo crear el fragmento");
            } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                excepcionPersonalizada.printStackTrace();
            }

        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvReunionOnItemClick(parent,view,position,id);
            }
        });


    }

    public void lvReunionOnItemClick(AdapterView<?> parent, View view, int position, long id) {

        //parent es el listview
        if(onReunionSeleccionadoListener != null){
            onReunionSeleccionadoListener.onReunionSelecionado(
                    (DTReunion)parent.getItemAtPosition(position));
        }
    }

    public interface OnReunionSeleccionadoListener{
        void onReunionSelecionado(DTReunion DTReunion);
    }


}