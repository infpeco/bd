package com.example.infante.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ConsultaUsuario extends AppCompatActivity {
    Spinner spinnerUsuarios;
    TextView txtId, txtNombre, txtTelefono;

    ArrayList<String> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuario);

        spinnerUsuarios = findViewById(R.id.spinner_usuarios);
        txtId = findViewById(R.id.txt_id);
        txtNombre = findViewById(R.id.txt_nombre);
        txtTelefono = findViewById(R.id.txt_telefono);
    }
}
