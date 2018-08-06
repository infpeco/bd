package com.example.infante.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infante.myapplication.entidades.Mascota;
import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

public class DetalleMascota extends AppCompatActivity {

    ConexionSQLiteHelper conn;

    TextView campoIdMascota, campoNombreMascota, campoRazaMascota;
    TextView campoIdPersona, campoNombrePersona, campoTelefonoPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios", null, 1);

        campoIdMascota = findViewById(R.id.txt_id_mascota);
        campoNombreMascota = findViewById(R.id.txt_nombre_mascota);
        campoRazaMascota = findViewById(R.id.txt_raza);
        campoIdPersona = findViewById(R.id.txt_id);
        campoNombrePersona = findViewById(R.id.txt_id_mascota);
        campoTelefonoPersona = findViewById(R.id.txt_telefono);

        Bundle objetoEnviado = getIntent().getExtras();

        Mascota mascota = null;

        if (objetoEnviado!=null){

            mascota = (Mascota) objetoEnviado.getSerializable("mascota");

            campoIdMascota.setText(mascota.getIdMascota().toString());
            campoNombreMascota.setText(mascota.getNombreMascota().toString());
            campoRazaMascota.setText(mascota.getRaza().toString());
            consultaPersona(mascota.getIdDuenio());
        }
    }

    private void consultaPersona(Integer idPersona) {

        SQLiteDatabase db = conn.getWritableDatabase();
        String [] paranametros = {idPersona.toString()};
        String [] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};

        Toast.makeText(getApplicationContext(), "El documento "+idPersona, Toast.LENGTH_SHORT).show();



        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",paranametros,null,null,null);
            cursor.moveToFirst();
            campoIdPersona.setText(idPersona.toString());
            campoNombrePersona.setText(cursor.getString(0));
            campoTelefonoPersona.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){

            Toast.makeText(this, "Error al realizar la consulta", Toast.LENGTH_SHORT).show();

        }
    }
}
