package com.matiaslugo.obligatorio2021.viewseventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Adapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;

import com.matiaslugo.obligatorio2021.db.DbClientes;
import com.matiaslugo.obligatorio2021.db.DbEventos;

@SuppressWarnings("deprecation")
public class DetalleEventoFragment extends Fragment {

    private TextView  tvNombreCliente,tvTitulo,tvFecha,tvHora,tvDuracion,
    tvAsistentes, tvTipo, tvIdCliente, tvIdEvento;
    private ImageView imvIcono;
    private DbEventos dbEventos;
    private Adapter adapter;
    private View view;
    private Evento evento;


    public static DetalleEventoFragment newInstance(Evento evento){
        return new DetalleEventoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_evento,null);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvNombreCliente = getView().findViewById(R.id.tvNombreCliente);
        tvTitulo = getView().findViewById(R.id.tvTitulo);
        tvFecha = getView().findViewById(R.id.tvFecha);
        tvHora = getView().findViewById(R.id.tvHora);
        tvDuracion = getView().findViewById(R.id.tvDuracion);
        tvAsistentes = getView().findViewById(R.id.tvAsistetes);
        tvTipo = getView().findViewById(R.id.tvTipo);
        tvIdCliente = getView().findViewById(R.id.tvIdCliente);
        tvIdEvento = getView().findViewById(R.id.tvIdEvento);
        imvIcono = getView().findViewById(R.id.imvAvatar);


    }

    public void mostrarEvento(Evento evento){


        tvIdEvento.setText(String.valueOf(evento.getIdEvento()));
        tvTitulo.setText(evento.getTitulo());
        tvFecha.setText(evento.getFecha());
        tvHora.setText(evento.getHora());
        tvDuracion.setText(String.valueOf(evento.getDuracion()));
        tvAsistentes.setText(String.valueOf(evento.getCantAsistentes()));
        tvIdCliente.setText(String.valueOf(evento.getIdCliente()));
        buscarCliente(evento.getIdCliente());
        switch (evento.getTipo()){
            case 1:
                tvTipo.setText("Familiar");
                //CreaR clase constante para los iconos.
                imvIcono.setImageResource(R.drawable.evento_1);
                return;
            case 2:
                tvTipo.setText("Empresarial");
                //CreaR clase constante para los iconos.
                imvIcono.setImageResource(R.drawable.evento_2);
                return;
            case 3:
                tvTipo.setText("Deportivo");
                //CreaR clase constante para los iconos.
                imvIcono.setImageResource(R.drawable.evento_3);
                return;
            case 4:
                tvTipo.setText("Social");
                //CreaR clase constante para los iconos.
                imvIcono.setImageResource(R.drawable.evento_4);
                return;
            case 5:
                tvTipo.setText("Politico");
                //CreaR clase constante para los iconos.
                imvIcono.setImageResource(R.drawable.evento_5);
                return;


        }

}
    protected void buscarCliente(Integer id){
        DbClientes dbClientes = new DbClientes(getContext());
        Cliente cliente =  dbClientes.buscarCliente(id);;
        if(cliente instanceof Particular){
            tvNombreCliente.setText(((Particular) cliente).getNombre());
        } else {
            tvNombreCliente.setText(((Comercial) cliente).getRazonSocial());
        }


    }
}


