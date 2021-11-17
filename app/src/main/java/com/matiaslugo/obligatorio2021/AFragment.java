package com.matiaslugo.obligatorio2021;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.db.DbClientes;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class AFragment extends Fragment {
    protected String mensaje;
    protected FloatingActionButton btnAgregar;
    private ListView lv;
    private SearchView searchView;
    AdaptadorClientes adapter;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    DbClientes dbClientes;
    View view;

    public static AFragment newInstance(String mensaje){
        if(mensaje == null || mensaje.isEmpty()){
            mensaje = "ClienteFragmentoLista";
        }
        Bundle argumentos = new Bundle();
        argumentos.putString("mensaje", mensaje);

        AFragment fragmento = new AFragment();
        fragmento.setArguments(argumentos);

        return fragmento;
    }


    public AFragment(){
        //Requiere constructor por defecto
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mensaje = getArguments().getString("mensaje");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.lista_fragment_cliente,container,false);
       btnAgregar = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv = (ListView)view.findViewById(R.id.lvEmpleados);


        dbClientes = new DbClientes(getActivity());


        clientes = dbClientes.listaClientes();


        adapter = new AdaptadorClientes(getActivity().getApplicationContext(),clientes);
        lv.setAdapter(adapter);
        dbClientes.close();



    }
}
