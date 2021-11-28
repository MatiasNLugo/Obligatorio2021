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
import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;
import com.matiaslugo.obligatorio2021.db.DbReuniones;
import com.matiaslugo.obligatorio2021.viewclientes.AdaptadorClientes;
import com.matiaslugo.obligatorio2021.viewclientes.ListadoClienteFragment;
import com.matiaslugo.obligatorio2021.viewseventos.AdaptadorEventos;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ListadoReunionFragment extends Fragment {

    public static ListadoReunionFragment newInstance(){

        return new ListadoReunionFragment();

    }

    int idEvento;
    private FloatingActionButton btnAgregar;
    private ListView lv;
    private SearchView searchView;
    private AdaptadorReuniones adapter;

    private ArrayList<Reunion> reuniones = new ArrayList<>();
    DbReuniones dbReunion;
    View view;
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
       // btnAgregar = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) getView().findViewById(R.id.lvReuniones);
        dbReunion = new DbReuniones(getActivity());
        reuniones = dbReunion.listaReuniones(idEvento);

        AdaptadorReuniones adapter = new AdaptadorReuniones(getContext(),reuniones);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvReunionOnItemClick(parent,view,position,id);
            }
        });
        dbReunion.close();
    }

    public void lvReunionOnItemClick(AdapterView<?> parent, View view, int position, long id) {

        //parent es el listview
        if(onReunionSeleccionadoListener != null){
            onReunionSeleccionadoListener.onReunionSelecionado(
                    (Reunion)parent.getItemAtPosition(position));
        }
    }

    public interface OnReunionSeleccionadoListener{
        void onReunionSelecionado(Reunion reunion);
    }

}