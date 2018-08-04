package com.example.infante.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infante.myapplication.utilidades.Utilidades;

/*Hecho por Infante 20180802
Del video tutorial https://youtu.be/TjJ3cHcPWw8*/

public class NuevoRegistro extends AppCompatActivity {

    EditText campoId;
    EditText campoNombre;
    EditText campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_registro);

        campoId = findViewById(R.id.editText_id);
        campoNombre = findViewById(R.id.editText_nombre);
        campoTelefono = findViewById(R.id.editText_telefono);

    }

    public void onClick (View view){

        registrarNuevoUsuario();

    }

    private void registrarNuevoUsuario() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CAMPO_ID, campoId.getText().toString());
        valores.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        valores.put(Utilidades.CAMPO_TELEFONO, campoTelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, valores);

        Toast.makeText(getApplicationContext(), "id Registro: "+idResultante,Toast.LENGTH_LONG).show();

        db.close();

    }
}
