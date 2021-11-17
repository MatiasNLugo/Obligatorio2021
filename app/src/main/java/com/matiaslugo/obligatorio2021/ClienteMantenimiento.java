package com.matiaslugo.obligatorio2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClienteMantenimiento extends AppCompatActivity {



    protected FloatingActionButton btnAgregar;
    protected Fragment fragmentoA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_mantenimiento);

         fragmentoA = new AFragment().newInstance("Fragmento A");


         cambiarFragmento(fragmentoA,false);

         btnAgregar = (FloatingActionButton)findViewById(R.id.floatingActionButton);




    }

    public void btnOnClickAgregarCliente(View view) {


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