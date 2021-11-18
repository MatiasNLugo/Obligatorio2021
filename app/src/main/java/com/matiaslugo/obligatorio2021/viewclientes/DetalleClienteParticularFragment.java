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
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbClientes;

@SuppressWarnings("ALL")
public class DetalleClienteParticularFragment extends Fragment {

    protected String mensaje;
    private SearchView searchView;
    private Cliente cliente;
    DbClientes dbClientes;
    private Adapter adapter;
    View view;

    private TextView tvIdCliente, tvNombreCliete,tvCedula,tvDireccion,tvTelefono,tvCorreo;




    public static DetalleClienteParticularFragment newInstance(Cliente cliente){

        return new DetalleClienteParticularFragment();

    }

    public DetalleClienteParticularFragment(){
        //Requiere constructor por defecto
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_particular_cliente_detalle,null);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       tvIdCliente = getView().findViewById(R.id.tvIdCliente);
       tvNombreCliete = getView().findViewById(R.id.tvNombre);
       tvCedula = getView().findViewById(R.id.tvCedula);
       tvDireccion = getView().findViewById(R.id.tvDireccion);
       tvTelefono = getView().findViewById(R.id.tvTelefono);
      /* if(cliente != null) {
           tvIdCliente.setText(String.valueOf(cliente.getIdCliente()));
           tvNombreCliete.setText(((Particular) cliente).getNombre());
           tvCedula.setText(((Particular) cliente).getCedula());
           tvDireccion.setText(cliente.getDireccion());
           tvTelefono.setText(cliente.getTelefono());
           tvCorreo.setText(cliente.getCorreo());
       }*/


    }


}
