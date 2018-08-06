package com.example.infante.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.infante.myapplication.entidades.Usuario;

public class DetalleUsuario extends AppCompatActivity {

    TextView txtId, txtNombre, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        txtId = findViewById(R.id.txt_id);
        txtNombre = findViewById(R.id.txt_nombre);
        txtTelefono = findViewById(R.id.txt_telefono);

        Bundle objetoEnviado = getIntent().getExtras();

        Usuario user = null;

        if (objetoEnviado!=null){

            user = (Usuario) objetoEnviado.getSerializable("usuario");

            txtId.setText(user.getId().toString());
            txtNombre.setText(user.getNombre().toString());
            txtTelefono.setText(user.getTelefono().toString());
        }
    }
}
