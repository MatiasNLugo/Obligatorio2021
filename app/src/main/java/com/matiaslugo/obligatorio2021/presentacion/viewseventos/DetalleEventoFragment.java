package com.matiaslugo.obligatorio2021.presentacion.viewseventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.R;

import com.matiaslugo.obligatorio2021.compartidos.excepciones.ExcepcionPersonalizada;
import com.matiaslugo.obligatorio2021.logica.FabricaLogica;
import com.matiaslugo.obligatorio2021.persistencia.PersistenciaEvento;

@SuppressWarnings("deprecation")
public class DetalleEventoFragment extends Fragment {

    private TextView  tvNombreCliente,tvTitulo,tvFecha,tvHora,tvDuracion,
    tvAsistentes, tvTipo, tvIdCliente, tvIdEvento;
    private ImageView imvIcono;
    private PersistenciaEvento persistenciaEvento;
    private Adapter adapter;
    private View view;
    private DTEvento evento;


    public static DetalleEventoFragment newInstance(DTEvento evento){
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

    public void mostrarEvento(DTEvento evento) throws ExcepcionPersonalizada {


        tvIdEvento.setText(String.valueOf(evento.getIdEvento()));
        tvTitulo.setText(evento.getTitulo());
        tvFecha.setText(evento.getFecha());
        tvHora.setText(evento.getHora());
        tvDuracion.setText(String.valueOf(evento.getDuracion()));
        tvAsistentes.setText(String.valueOf(evento.getCantAsistentes()));
        tvIdCliente.setText(String.valueOf(evento.getIdCliente()));
        try {
            buscarCliente(evento.getIdCliente());
            switch (evento.getTipo()) {
                case 1:
                    tvTipo.setText("Familiar");
                    //CreaR clase constante para los iconos.
                    imvIcono.setImageResource(R.drawable.evento_1);
                    return;
                case 2:
                    tvTipo.setText("Empresarial");
                    imvIcono.setImageResource(R.drawable.evento_2);
                    return;
                case 3:
                    tvTipo.setText("Deportivo");
                    imvIcono.setImageResource(R.drawable.evento_3);
                    return;
                case 4:
                    tvTipo.setText("Social");
                    imvIcono.setImageResource(R.drawable.evento_4);
                    return;
                case 5:
                    tvTipo.setText("Politico");
                    imvIcono.setImageResource(R.drawable.evento_5);
                    return;


            }
        }catch(Exception ex) {
            throw new ExcepcionPersonalizada("No se pudo buscar el cliente.");
        }

}
    protected void buscarCliente(Integer id) throws ExcepcionPersonalizada {

        DTCliente cliente = FabricaLogica.getControladorMantenimientoCliente(getContext()).buscarCliente(id);

        if(cliente instanceof DTParticular){
            tvNombreCliente.setText(((DTParticular) cliente).getNombre());
        } else {
            tvNombreCliente.setText(((DTComercial) cliente).getRazonSocial());
        }

    }
}


