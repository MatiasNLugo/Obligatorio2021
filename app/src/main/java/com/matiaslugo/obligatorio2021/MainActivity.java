package com.matiaslugo.obligatorio2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.matiaslugo.obligatorio2021.DataTypes.Cliente;
import com.matiaslugo.obligatorio2021.DataTypes.Evento;
import com.matiaslugo.obligatorio2021.DataTypes.Particular;
import com.matiaslugo.obligatorio2021.db.DbClientes;
import com.matiaslugo.obligatorio2021.db.DbEventos;
import com.matiaslugo.obligatorio2021.viewclientes.ClienteMantenimiento;
import com.matiaslugo.obligatorio2021.viewseventos.ListarEventos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregar, btnAgregarEvento, btnListarEvento;
    private TextView tv;
    private String paraMandar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = findViewById(R.id.btnAgregarCliente);
        btnAgregarEvento = findViewById(R.id.btnAgregarEvento);
        btnListarEvento = findViewById(R.id.btnIrListarEventos);
        tv = findViewById(R.id.tv);

    btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DbClientes  dbClientes = new DbClientes(getApplicationContext());

            Cliente unCliente =  new Particular();
            ((Particular)unCliente).setCedula("42891681");
            ((Particular)unCliente).setNombre("Matias Nicolas Lugo");
            unCliente.setCorreo("Algo");
            unCliente.setTelefono("099332922");
            unCliente.setDireccion("algo");

            dbClientes.insertarCliente(unCliente);

            List<Cliente> clientes = new ArrayList<>();
            clientes = dbClientes.listaClientes();
            try{
            for (Cliente item : clientes) {

                if (item instanceof Particular) {

                    paraMandar = paraMandar + " // " + ((Particular) item).getNombre() + " - " + ((Particular) item).getCedula() + " - " + item.getIdCliente() + " - " + item.getDireccion() + " - " + item.getTelefono() + " - " + item.getCorreo();
                }

                tv.setText(paraMandar.toString());


            }
                IrAclientes();
            } catch (Exception ex) {
                String mensaje = ex.toString();
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
            } finally {
                dbClientes.close();
            }
        }
    });

    btnAgregarEvento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnOnClickAgregarEvento(v);
        }
    });

    btnListarEvento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnOnClickListarEventos(v);
        }
    });

    }

    protected void IrAclientes(){
        Intent enviar = new Intent(this, ClienteMantenimiento.class);
        startActivity(enviar);
    }


    protected void btnOnClickAgregarEvento(View view) {

        Evento evento = new Evento();
        DbEventos dbEventos = new DbEventos(this);
        dbEventos.insertarEvento(evento);
    }


    protected void btnOnClickListarEventos(View view) {
        Intent enviar = new Intent(this, ListarEventos.class);
        startActivity(enviar);
    }
}