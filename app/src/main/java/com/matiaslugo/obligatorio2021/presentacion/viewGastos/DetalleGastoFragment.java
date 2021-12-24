package com.matiaslugo.obligatorio2021.presentacion.viewGastos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;

@SuppressWarnings("deprecation")
public class DetalleGastoFragment extends Fragment {


    public static DetalleGastoFragment newInstance(DTGasto gasto){
        return new DetalleGastoFragment();
    }

    private TextView tvIdGasto,tvMotivo,tvProveedor,tvMonto;

    public DetalleGastoFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_gasto,container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvIdGasto = (TextView) getView().findViewById(R.id.tvIdGasto);
        tvMotivo = (TextView)getView().findViewById(R.id.tvMotivo);
        tvProveedor = (TextView)getView().findViewById(R.id.tvProveedor);
        tvMonto = (TextView)getView().findViewById(R.id.tvMonto);
    }

    public void mostrarGasto(DTGasto gasto){
        tvIdGasto.setText(String.valueOf(gasto.getIdGasto()));
        tvMotivo.setText(gasto.getMotivo());
        tvProveedor.setText(gasto.getProveedor());
        tvMonto.setText(String.valueOf(gasto.getMonto()));
    }




}
