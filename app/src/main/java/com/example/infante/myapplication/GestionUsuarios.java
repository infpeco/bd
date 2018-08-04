package com.example.infante.myapplication;

//Por Infante 20180804
//del tutorial de Cristian Henao
//https://youtu.be/YbpcUFa2gQI

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infante.myapplication.utilidades.Utilidades;

public class GestionUsuarios extends AppCompatActivity {

    EditText edtId, edtNombre, edtTelefono;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuarios);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);

        edtId = findViewById(R.id.editText_id);
        edtNombre = findViewById(R.id.editText_nombre);
        edtTelefono = findViewById(R.id.editText_telefono);
    }


    public void onClick(View view){

        switch (view.getId()){
            case R.id.btn_buscar: consultarUsuario();
                break;
            case R.id.btn_actualizar: actualizarUsuario();
                break;
            case R.id.btn_eliminar: eliminarUsuario();
                break;
        }

    }

    private void eliminarUsuario() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {edtId.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID+"=?", parametros);

        Toast.makeText(this, "Registro eliminado", Toast.LENGTH_SHORT).show();
        limpiarCampos();
        db.close();

    }

    private void actualizarUsuario() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] paranametros = {edtId.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CAMPO_NOMBRE, edtNombre.getText().toString());
        valores.put(Utilidades.CAMPO_TELEFONO, edtTelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO, valores, Utilidades.CAMPO_ID+"=?", paranametros);
        Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
        limpiarCampos();
        db.close();

    }

    private void consultarUsuario() {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] paranametros = {edtId.getText().toString()};
        String [] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};



        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",paranametros,null,null,null);
            cursor.moveToFirst();
            edtNombre.setText(cursor.getString(0));
            edtTelefono.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){

            Toast.makeText(this, "Error al realizar la consulta", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {

        edtId.setText("");
        edtNombre.setText("");
        edtTelefono.setText("");
        edtId.requestFocus();
    }
}
