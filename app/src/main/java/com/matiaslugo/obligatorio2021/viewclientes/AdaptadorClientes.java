package com.matiaslugo.obligatorio2021.viewclientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTCliente;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTComercial;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTParticular;
import com.matiaslugo.obligatorio2021.R;

import java.util.ArrayList;

public class AdaptadorClientes extends BaseAdapter {

    private Context contexto;
    private ArrayList<DTCliente> clientes;



    public AdaptadorClientes(Context contexto, ArrayList<DTCliente> clientes){
       this.clientes = clientes;
       this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public DTCliente getItem(int position) {
        return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ClienteViewHolder clienteViewHolder;


        if (item == null){
            //Primeros elementos
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_cliente,null);

            clienteViewHolder = new ClienteViewHolder(item);
            item.setTag(clienteViewHolder);



        } else {
            //reciclado
            clienteViewHolder = (ClienteViewHolder) item.getTag();

        }

        clienteViewHolder.enlazarCliente(clientes.get(position));

        return item;

    }

    protected class ClienteViewHolder{
        private  TextView tvId;
        private TextView tvNombre;
        String id = "ID: ";
        public ClienteViewHolder(View item){
            tvId = item.findViewById(R.id.tvIdCliente);
            tvNombre = item.findViewById(R.id.tvNombre);
        }
        public void enlazarCliente(DTCliente cliente){
            tvId.setText( id + String.valueOf(cliente.getIdCliente()));
            if(cliente instanceof DTParticular){
                tvNombre.setText(((DTParticular) cliente).getNombre());
            }
            if(cliente instanceof DTComercial){
                tvNombre.setText(((DTComercial) cliente).getRazonSocial());
            }


        }
    }
}
