package com.matiaslugo.obligatorio2021.presentacion.viewGastos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.presentacion.Constantes;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListadoGastoFragment extends Fragment {

    public ListadoGastoFragment newInstance(){
        return new ListadoGastoFragment();
    }

    private AdaptadorGastos adapter;
    private ArrayList<DTGasto> gastos;
    private ListView lv;
    private TextView tvTotal;
    private View view;
    private int idEvento;
    protected OnGastoSeleccionadoListener onGastoSeleccionadoListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt(Constantes.ID_EVENTO,1);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  OnGastoSeleccionadoListener){
            onGastoSeleccionadoListener = (OnGastoSeleccionadoListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado_gastos,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
        lv = (ListView) getView().findViewById(R.id.lvGastos);
        tvTotal= (TextView) getView().findViewById(R.id.tvTotal);
        gastos = FabricaLogica.getControladorMantenimientoGasto(getContext()).listaGastos(idEvento);


        verTotal();
        adapter = new AdaptadorGastos(getContext(), gastos);
        lv.setAdapter(adapter);
        } catch (ExcepcionPersonalizada excepcionPersonalizada) {
            try {
                throw new ExcepcionPersonalizada("No se pudo listar los gastos.");
            } catch (ExcepcionPersonalizada personalizada) {
                personalizada.printStackTrace();
            }
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvGastoOnItemClick(parent,view,position);
            }
        });
        
    }

    public void lvGastoOnItemClick(AdapterView<?> parent, View view, int position) {
        if(onGastoSeleccionadoListener != null){
            onGastoSeleccionadoListener.onGastoSeleccionado(
                    (DTGasto)parent.getItemAtPosition(position));
        }
    }

    public interface OnGastoSeleccionadoListener{
        void onGastoSeleccionado(DTGasto gasto);
    }


    public void verTotal(){
        float total = 0;
        for (DTGasto item: gastos) {
            total = total + item.getMonto();
        }

        tvTotal.setText(new StringBuilder().append("TOTAL: $ ").append(String.valueOf(total)));

    }
}
