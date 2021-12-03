package com.matiaslugo.obligatorio2021.viewclientes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaCliente;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListadoClienteFragment extends Fragment {


    public static ListadoClienteFragment newInstance(String mensaje){

        return new ListadoClienteFragment();

    }


    protected FloatingActionButton btnAgregar;
    private ListView lv;
    private SearchView searchView;
    AdaptadorClientes adapter;
    private ArrayList<DTCliente> clientes = new ArrayList<>();
    PersistenciaCliente persistenciaCliente;
    View view;
    protected OnClienteSeleccionadoListener onClienteSeleccionadoListener;

    public ListadoClienteFragment(){
        //Requiere constructor por defecto
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //Context es la misma activity a la que me adjunto.
        if(context instanceof OnClienteSeleccionadoListener){
            onClienteSeleccionadoListener = (OnClienteSeleccionadoListener) context;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view = inflater.inflate(R.layout.fragment_cliente_listado,container,false);
       btnAgregar = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv = (ListView)getView().findViewById(R.id.lvEmpleados);


        try {
            clientes = FabricaLogica.getControladorMantenimientoCliente(getContext()).listaClientes();
        } catch (ExcepcionPersonalizada excepcionPersonalizada) {
            excepcionPersonalizada.printStackTrace();
        }

        adapter = new AdaptadorClientes(getActivity().getApplicationContext(),clientes);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvClientesOnItemClick(parent,view,position,id);
            }
        });



    }

   /* public void lvClientesOnItemLongClickListener(AdapterView<?> parent, View view, int position, long id){
        if(onClienteSeleccionadoListener != null){
            onClienteSeleccionadoListener.onClienteSelecionado(
                    (DTCliente)parent.getItemAtPosition(position));
        }
    }*/

    public void lvClientesOnItemClick(AdapterView<?> parent, View view, int position, long id) {

        //parent es el listview
        if(onClienteSeleccionadoListener != null){
            onClienteSeleccionadoListener.onClienteSelecionado(
                    (DTCliente)parent.getItemAtPosition(position));
        }
    }

    public interface OnClienteSeleccionadoListener{
        void onClienteSelecionado(DTCliente cliente);
    }

}
