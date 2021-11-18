package com.matiaslugo.obligatorio2021.viewseventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.R;
import com.matiaslugo.obligatorio2021.db.DbEventos;

import java.util.ArrayList;
import java.util.List;

public class ListarEventos extends AppCompatActivity {

    protected ArrayList<Evento> eventos;
    protected GridView gvEventos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);

        gvEventos = (GridView) findViewById(R.id.gvEventos);
        eventos = new DbEventos(this).listaEvento();
        AdaptadorEventos adapter = new AdaptadorEventos(this, eventos);
        gvEventos.setAdapter(adapter);


    }
}