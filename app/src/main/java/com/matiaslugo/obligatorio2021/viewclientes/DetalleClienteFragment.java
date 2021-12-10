package com.matiaslugo.obligatorio2021.viewclientes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaCliente;

@SuppressWarnings("ALL")
public class DetalleClienteFragment extends Fragment {

    private String mensaje;
    private SearchView searchView;
    private DTCliente cliente;
    PersistenciaCliente persistenciaCliente;
    private Adapter adapter;
    View view;

    private TextView tvIdCliente, tvNombreCliete,tvCedula,tvDireccion,tvTelefono,tvCorreo
            ,tvRut, tvRazonSocial,tvTituloCedula,tvTituloNombre,tvTituloRut,tvTituloRazonSocial;




    public static DetalleClienteFragment newInstance(DTCliente cliente){
        return new DetalleClienteFragment();

    }

    public DetalleClienteFragment(){
        //Requiere constructor por defecto
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cliente_detalle,null);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       tvIdCliente = getView().findViewById(R.id.tvIdCliente);
       tvNombreCliete = getView().findViewById(R.id.tvNombreCliente);
        tvRazonSocial = getView().findViewById(R.id.tvRazonSocial);
       tvCedula = getView().findViewById(R.id.tvCedula);
        tvRut = getView().findViewById(R.id.tvRut);
       tvDireccion = getView().findViewById(R.id.tvDireccion);
       tvTelefono = getView().findViewById(R.id.tvTelefono);
       tvCorreo = getView().findViewById(R.id.tvCorreo);
       tvTituloCedula = getView().findViewById(R.id.tvTituloCedula);
       tvTituloNombre = getView().findViewById(R.id.tvTituloNombre);
       tvTituloRut = getView().findViewById(R.id.tvTituloRut);
       tvTituloRazonSocial = getView().findViewById(R.id.tvTituloRazonSocial);
    }

    public  void mostrarCliente(DTCliente cliente){

        tvIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        tvDireccion.setText(cliente.getDireccion());
        tvCorreo.setText(cliente.getCorreo());
        tvTelefono.setText(cliente.getTelefono());


        if(cliente instanceof DTParticular){
            tvCedula.setText(String.valueOf(((DTParticular) cliente).getCedula()));
            tvNombreCliete.setText(((DTParticular) cliente).getNombre());


            tvCedula.setVisibility(View.VISIBLE);
            tvNombreCliete.setVisibility(View.VISIBLE);
            tvTituloCedula.setVisibility(view.VISIBLE);
            tvTituloNombre.setVisibility(view.VISIBLE);

            tvRut.setVisibility(View.GONE);
            tvRazonSocial.setVisibility(View.GONE);
            tvTituloRazonSocial.setVisibility(View.GONE);
            tvTituloRut.setVisibility(View.GONE);
        }
        if (cliente instanceof DTComercial){
            tvRut.setText(String.valueOf(((DTComercial) cliente).getRut()));
            tvRazonSocial.setText(((DTComercial) cliente).getRazonSocial());

            tvCedula.setVisibility(View.GONE);
            tvNombreCliete.setVisibility(View.GONE);
            tvTituloCedula.setVisibility(view.GONE);
            tvTituloNombre.setVisibility(view.GONE);


            tvRut.setVisibility(View.VISIBLE);
            tvRazonSocial.setVisibility(View.VISIBLE);
            tvTituloRazonSocial.setVisibility(View.VISIBLE);
            tvTituloRut.setVisibility(View.VISIBLE);

        }
    }


}
