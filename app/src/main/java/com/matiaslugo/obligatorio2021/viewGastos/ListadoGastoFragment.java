package com.matiaslugo.obligatorio2021.viewGastos;

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

import com.matiaslugo.obligatorio2021.DataTypes.Gasto;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbGastos;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class ListadoGastoFragment extends Fragment {

    public ListadoGastoFragment newInstance(){
        return new ListadoGastoFragment();
    }
    private AdaptadorGastos adapter;
    private ArrayList<Gasto> gastos = new ArrayList<Gasto>();
    private DbGastos dbGastos;
    private ListView lv;
    private View view;
    private int idEvento;
    protected OnGastoSeleccionadoListener onGastoSeleccionadoListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            idEvento = getArguments().getInt("idEvento",1);
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

        lv = (ListView) getView().findViewById(R.id.lvGastos);

        dbGastos = new DbGastos(getActivity());
        gastos = dbGastos.listaGastos(idEvento);

        adapter = new AdaptadorGastos(getContext(),gastos);
        lv.setAdapter(adapter);
        dbGastos.close();

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
                    (Gasto)parent.getItemAtPosition(position));
        }
    }

    public interface OnGastoSeleccionadoListener{
        void onGastoSeleccionado(Gasto gasto);
    }
}
