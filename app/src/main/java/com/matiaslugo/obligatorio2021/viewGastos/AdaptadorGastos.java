package com.matiaslugo.obligatorio2021.viewGastos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTGasto;
import com.matiaslugo.obligatorio2021.R;

import java.util.ArrayList;

public class AdaptadorGastos extends BaseAdapter {
    private Context contexto;
    private ArrayList<DTGasto> DTGastos = new ArrayList<DTGasto>();

    public AdaptadorGastos(Context contexto, ArrayList<DTGasto> DTGastos){
        this.contexto = contexto;
        this.DTGastos = DTGastos;
    }


    @Override
    public int getCount() {
        return DTGastos.size();
    }

    @Override
    public Object getItem(int position) {
        return DTGastos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        GastoViewHolder gastoViewHolder;

        if (item == null){
            //Primeros elementos
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_gastos,null);

            gastoViewHolder = new AdaptadorGastos.GastoViewHolder(item);
            item.setTag(gastoViewHolder);



        } else {
            //reciclado
            gastoViewHolder = (AdaptadorGastos.GastoViewHolder) item.getTag();

        }

        gastoViewHolder.enlazarGasto(DTGastos.get(position));

        return item;
    }

    protected class GastoViewHolder{
        private TextView tvMotivo,tvPrecio;

        public GastoViewHolder(View item){
            tvMotivo = item.findViewById(R.id.tvMotivo);
            tvPrecio = item.findViewById(R.id.tvPrecio);
        }
        public void enlazarGasto(DTGasto DTGasto){
            tvMotivo.setText(DTGasto.getMotivo());
            tvPrecio.setText(String.valueOf(DTGasto.getMonto()));
        }
    }
}
