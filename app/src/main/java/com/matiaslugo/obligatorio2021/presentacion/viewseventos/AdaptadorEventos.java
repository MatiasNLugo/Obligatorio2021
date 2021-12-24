package com.matiaslugo.obligatorio2021.presentacion.viewseventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTEvento;
import com.matiaslugo.obligatorio2021.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorEventos extends BaseAdapter {
   private Context contexto;
   private List<DTEvento> eventos;
   private Map<Integer,Integer> idsImagenes;



   public AdaptadorEventos(Context contexto, List<DTEvento> eventos){
       this.eventos = eventos;
       this.contexto = contexto;

       idsImagenes = new HashMap<Integer,Integer>();
       idsImagenes.put(1, R.drawable.evento_1);
       idsImagenes.put(2, R.drawable.evento_2);
       idsImagenes.put(3, R.drawable.evento_3);
       idsImagenes.put(4, R.drawable.evento_4);
       idsImagenes.put(5, R.drawable.evento_5);

   }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        EventoViewHolder eventoViewHolder;

        if(item == null){

            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_evento,null);
            eventoViewHolder = new EventoViewHolder(item);
            item.setTag(eventoViewHolder);

        } else {

            eventoViewHolder = (EventoViewHolder) item.getTag();
        }

        eventoViewHolder.enlazarEvento(eventos.get(position));

       return item;
    }

    protected  class EventoViewHolder {
       private ImageView imgAvatar;
       private TextView tvTitulo;
       private TextView tvFecha;
       private TextView tvHora;


        public EventoViewHolder(View view){
           imgAvatar = (ImageView) view.findViewById(R.id.imvAvatar);
            tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
           tvFecha = (TextView) view.findViewById(R.id.tvFecha);
            tvHora = (TextView) view.findViewById(R.id.tvHora);
        }

        public void enlazarEvento(DTEvento evento){
           // int id = 70010;
            int id = (int) idsImagenes.get(evento.getTipo());
            if ( idsImagenes.get(evento.getTipo()) == null){
                id= 70010;
            }
            imgAvatar.setImageResource(id);

            tvTitulo.setText(evento.getTitulo());
            tvFecha.setText(evento.getFecha());
            tvHora.setText(evento.getHora());

        }

    }
}
