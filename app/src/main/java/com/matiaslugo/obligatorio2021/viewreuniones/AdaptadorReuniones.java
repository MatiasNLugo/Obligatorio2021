package com.matiaslugo.obligatorio2021.viewreuniones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.DataTypes.Reunion;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.AdaptadorClientes;

import java.util.ArrayList;

public class AdaptadorReuniones extends BaseAdapter {
    private Context contexto;
    private ArrayList<Reunion> reuniones;

    public AdaptadorReuniones(Context contexto, ArrayList<Reunion> reuniones){
        this.contexto = contexto;
        this.reuniones = reuniones;
    }

    @Override
    public int getCount() {
        return reuniones.size();
    }

    @Override
    public Object getItem(int position) {
        return reuniones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ReunionesViewHolder reunionViewHolder;

        if (item == null){
            //Primeros elementos
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_reunion,null);

            reunionViewHolder = new ReunionesViewHolder(item);
            item.setTag(reunionViewHolder);

        } else {
            //reciclado
            reunionViewHolder = (ReunionesViewHolder) item.getTag();

        }

        reunionViewHolder.enlazarReunion(reuniones.get(position));

        return item;

    }

    protected class ReunionesViewHolder{
        private TextView tvHora;
        private TextView tvFecha;
        private TextView tvObjetivo;

        public ReunionesViewHolder(View item){
            tvHora = (TextView) item.findViewById(R.id.tvHora);
            tvFecha = (TextView) item.findViewById(R.id.tvFecha);
            tvObjetivo = (TextView) item.findViewById(R.id.tvObjetivo);
        }
        public void enlazarReunion(Reunion reunion){
            tvHora.setText(reunion.getHora().toString());
            tvFecha.setText(reunion.getFecha().toString());
            tvObjetivo.setText(reunion.getObjetivo().toString());

        }
    }
}
