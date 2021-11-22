package com.matiaslugo.obligatorio2021.viewclientes;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorClientes extends BaseAdapter {

    private Context contexto;
    private ArrayList<Cliente> clientes;



    public AdaptadorClientes(Context contexto, ArrayList<Cliente> clientes){
       this.clientes = clientes;
       this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Cliente getItem(int position) {
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
        public void enlazarCliente(Cliente cliente){
            tvId.setText( id + String.valueOf(cliente.getIdCliente()));
            if(cliente instanceof Particular){
                tvNombre.setText(((Particular) cliente).getNombre());
            }
            if(cliente instanceof Comercial){
                tvNombre.setText(((Comercial) cliente).getRazonSocial());
            }


        }
    }
}
