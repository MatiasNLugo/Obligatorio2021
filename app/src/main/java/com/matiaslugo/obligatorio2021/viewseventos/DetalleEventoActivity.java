package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.R;

public class DetalleEventoActivity extends AppCompatActivity {

    protected TextView tvTitulo, tvFecha, tvHora,tvTipo, tvAsistentes,tvDuracion,tvIdCliente, tvIdEvento;
    protected ImageView imvIcono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);

        tvTitulo = findViewById(R.id.tvTitulo);
        tvFecha = findViewById(R.id.tvFecha);
        tvHora = findViewById(R.id.tvHora);
        tvDuracion = findViewById(R.id.tvDuracion);
        tvAsistentes = findViewById(R.id.tvAsistetes);
        tvTipo = findViewById(R.id.tvTipo);
        tvIdCliente = findViewById(R.id.tvIdCliente);
        tvIdEvento = findViewById(R.id.tvIdEvento);
        imvIcono = findViewById(R.id.imvAvatar);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            Evento evento = (Evento)parametros.getSerializable("evento");
            tvIdEvento.setText(String.valueOf(evento.getTitulo()));
            tvTitulo.setText(evento.getTitulo());
            tvFecha.setText(evento.getFecha());
            tvHora.setText(evento.getHora());
            tvDuracion.setText(String.valueOf(evento.getDuracion()));
            tvAsistentes.setText(String.valueOf(evento.getCantAsistentes()));
            tvIdCliente.setText(String.valueOf(evento.getIdCliente()));
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


    }
}