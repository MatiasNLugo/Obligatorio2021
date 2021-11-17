package com.matiaslugo.obligatorio2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClienteMantenimiento extends AppCompatActivity {



    protected FloatingActionButton btnAgregar;
    protected Fragment fragmentoA;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);

         fragmentoA = new AFragment().newInstance("Fragmento A");

         cambiarFragmento(fragmentoA,false);

         btnAgregar = (FloatingActionButton)findViewById(R.id.floatingActionButton);
         lv = findViewById(R.id.lvEmpleados);

         //Al realizar un click
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 onItemClickListener();
             }
         });







    }

    protected void onItemClickListener(){
        //TODO  crear fragmento2
        //cambiarFragmento(fragmentoB,false);
    }

    public void btnOnClickAgregarCliente(View view) {
       //TODO  layout agregar cliente y pasar parametro
        // Intent enviar = new Intent(this,AgregarCliente.class);
        //startActivity(enviar);

    }

    protected void cambiarFragmento(Fragment nuevoFragmento,boolean agregarAPIalVolver){
        FragmentTransaction transactionFragmentos = getSupportFragmentManager().beginTransaction();
        transactionFragmentos.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transactionFragmentos.replace(R.id.frlZonaIntercambio,nuevoFragmento);
        if(agregarAPIalVolver){
            transactionFragmentos.addToBackStack(nuevoFragmento.getClass().getSimpleName());
        }
        transactionFragmentos.commit();
    }

}