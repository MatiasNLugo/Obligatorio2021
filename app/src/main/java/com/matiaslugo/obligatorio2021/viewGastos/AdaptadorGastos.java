package com.matiaslugo.obligatorio2021.viewGastos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Comercial;
import com.matiaslugo.obligatorio2021.DataTypes.Gasto;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.viewclientes.AdaptadorClientes;

import java.util.ArrayList;

public class AdaptadorGastos extends BaseAdapter {
    private Context contexto;
    private ArrayList<Gasto> gastos = new ArrayList<Gasto>();

    public AdaptadorGastos(Context contexto, ArrayList<Gasto> gastos){
        this.contexto = contexto;
        this.gastos = gastos;
    }


    @Override
    public int getCount() {
        return gastos.size();
    }

    @Override
    public Object getItem(int position) {
        return gastos.get(position);
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

        gastoViewHolder.enlazarGasto(gastos.get(position));

        return item;
    }

    protected class GastoViewHolder{
        private TextView tvMotivo,tvPrecio;

        public GastoViewHolder(View item){
            tvMotivo = item.findViewById(R.id.tvMotivo);
            tvPrecio = item.findViewById(R.id.tvPrecio);
        }
        public void enlazarGasto(Gasto gasto){
            tvMotivo.setText(gasto.getMotivo());
            tvPrecio.setText(String.valueOf(gasto.getMonto()));
        }
    }
}
