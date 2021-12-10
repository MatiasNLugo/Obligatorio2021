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
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTReunion;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersistencia;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaReunion;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ListadoReunionFragment extends Fragment {

    public static ListadoReunionFragment newInstance(){

        return new ListadoReunionFragment();

    }

    private Switch swPendientes;
    private int idEvento;
    private FloatingActionButton btnAgregar;
    private ListView lv;
    private SearchView searchView;
    private AdaptadorReuniones adapter;
    private ArrayList<DTReunion> reuniones = new ArrayList<>();
    private ArrayList<DTReunion> pendientes ;
    private View view;
    protected OnReunionSeleccionadoListener onReunionSeleccionadoListener;


    public ListadoReunionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt(Constantes.ID_EVENTO,1);
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
            swPendientes = (Switch)getView().findViewById(R.id.swPendientes);
            lv = (ListView) getView().findViewById(R.id.lvReuniones);
            reuniones = FabricaLogica.getControladorMantenimientoReunion(getContext()).listaReuniones(idEvento);

            AdaptadorReuniones adapter = new AdaptadorReuniones(getContext(), reuniones);
            lv.setAdapter(adapter);
        } catch (ExcepcionPersonalizada ex){
            Toast.makeText(getContext(), "No se pudo crear el fragmento", Toast.LENGTH_SHORT).show();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvReunionOnItemClick(parent,view,position,id);
            }
        });

        swPendientes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    try {
                        if (pendientes == null) {
                            pendientes = FabricaLogica.getControladorMantenimientoReunion(getContext()).listarPendientes(reuniones);
                        }
                        adapter = new AdaptadorReuniones(getContext(), pendientes);
                        lv.setAdapter(adapter);

                    } catch (ExcepcionPersonalizada excepcionPersonalizada) {
                        Toast.makeText(getContext(), excepcionPersonalizada.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                if (!isChecked){
                    adapter = new AdaptadorReuniones(getContext(), reuniones);
                    lv.setAdapter(adapter);
                }
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
        void onReunionSelecionado(DTReunion reunion);
    }


}