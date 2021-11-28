package com.matiaslugo.obligatorio2021.viewreuniones;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.R;


@SuppressWarnings("deprecation")
public class DetalleReunionFragment extends Fragment {

    private TextView tvIdReunion,tvObjetivo,tvFecha,tvHora,tvDescripcion,
            tvLugar,tvNotificar, tvIdCliente;

    public DetalleReunionFragment() {
        // Required empty public constructor
    }


    public static DetalleReunionFragment newInstance(Reunion reunion) {
        return new DetalleReunionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_reunion, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvIdReunion = getView().findViewById(R.id.tvIdReunion);
        tvObjetivo = getView().findViewById(R.id.tvObjetivo);
        tvFecha = getView().findViewById(R.id.tvFecha);
        tvHora= getView().findViewById(R.id.tvHora);
        tvDescripcion= getView().findViewById(R.id.tvDescripcion);
        tvLugar= getView().findViewById(R.id.tvLugar);
        tvNotificar= getView().findViewById(R.id.tvNotificar);

    }

    public void mostrarReunion(Reunion reunion){
        tvIdReunion.setText(String.valueOf(reunion.getIdReunion()));
        tvObjetivo.setText(reunion.getObjetivo());
        tvFecha.setText(reunion.getFecha());
        tvHora.setText(reunion.getHora());
        tvDescripcion.setText(reunion.getDescripcion());
        tvLugar.setText(reunion.getLugar());
        if(reunion.isNotificar()) {
            tvNotificar.setText(" Requiere notificar al cliente.");
        } else {
            tvNotificar.setText("No requiere notificar al cliente.");
        }
    }
}