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

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;

@SuppressWarnings("ALL")
public class DetalleClienteFragment extends Fragment {

    private String mensaje;
    private SearchView searchView;
    private Cliente cliente;
    DbClientes dbClientes;
    private Adapter adapter;
    View view;

    private TextView tvIdCliente, tvNombreCliete,tvCedula,tvDireccion,tvTelefono,tvCorreo
            ,tvRut, tvRazonSocial;




    public static DetalleClienteFragment newInstance(Cliente cliente){

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

       /* if(cliente != null) {
           tvIdCliente.setText(String.valueOf(cliente.getIdCliente()));
           tvNombreCliete.setText(((Particular) cliente).getNombre());
           tvCedula.setText(((Particular) cliente).getCedula());
           tvDireccion.setText(cliente.getDireccion());
           tvTelefono.setText(cliente.getTelefono());
           tvCorreo.setText(cliente.getCorreo());
       }*/


    }

    public  void mostrarCliente(Cliente cliente){

        tvIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        tvDireccion.setText(cliente.getDireccion());
        tvCorreo.setText(cliente.getCorreo());
        tvTelefono.setText(cliente.getTelefono());


        if(cliente instanceof Particular){
            tvCedula.setText(String.valueOf(((Particular) cliente).getCedula()));
            tvNombreCliete.setText(((Particular) cliente).getNombre());


            tvCedula.setVisibility(View.VISIBLE);
            tvNombreCliete.setVisibility(View.VISIBLE);
            tvRut.setVisibility(View.GONE);
            tvRazonSocial.setVisibility(View.GONE);
        }
        if (cliente instanceof Comercial){
            tvRut.setText(String.valueOf(((Comercial) cliente).getRut()));
            tvRazonSocial.setText(((Comercial) cliente).getRazonSocial());

            tvCedula.setVisibility(View.GONE);
            tvNombreCliete.setVisibility(View.GONE);
            tvRut.setVisibility(View.VISIBLE);
            tvRazonSocial.setVisibility(View.VISIBLE);

        }
    }


}
