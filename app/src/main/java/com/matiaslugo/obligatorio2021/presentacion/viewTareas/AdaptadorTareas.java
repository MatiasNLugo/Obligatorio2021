package com.matiaslugo.obligatorio2021.presentacion.viewTareas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.compartidos.datatypes.DTTarea;

import java.util.ArrayList;

public class AdaptadorTareas  extends BaseAdapter {

    private Context contexto;
    private ArrayList<DTTarea> tareas;
    CheckBox chkRealizado;

    public AdaptadorTareas(Context contexto, ArrayList<DTTarea> tareas){
        this.tareas = tareas;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        TareasViewHolder tareaViewHolder;

        if(item == null){
            LayoutInflater inflador = LayoutInflater.from(contexto);
            item = inflador.inflate(R.layout.list_item_tarea,null);

            tareaViewHolder = new TareasViewHolder(item);
            item.setTag(tareaViewHolder);
        } else {
            tareaViewHolder = (TareasViewHolder) item.getTag();
        }
        tareaViewHolder.enlazarTarea(tareas.get(position));
        return item;
    }

    protected class TareasViewHolder{
        private TextView tvFecha;
        private TextView tvDescripcion;

        public TareasViewHolder(View item){
            tvFecha = (TextView) item.findViewById(R.id.tvFecha);
            tvDescripcion = (TextView) item.findViewById(R.id.tvDescripcion);
            chkRealizado = item.findViewById(R.id.chkRealizado);

        }

    public void enlazarTarea(DTTarea tarea){
        tvFecha.setText(tarea.getFechaLimite());
        tvDescripcion.setText(tarea.getDescipcion());

        if(tarea.isRealizada()){
            chkRealizado.setChecked(true);
        } else {
            chkRealizado.setChecked(false);
        }
    }
    }
}
