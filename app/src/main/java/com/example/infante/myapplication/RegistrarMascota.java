package com.example.infante.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class RegistrarMascota extends AppCompatActivity {

    EditText nombreMascota, razaMascota;
    Spinner comboDuenio;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        nombreMascota = findViewById(R.id.editText_nombre_mascota);
        razaMascota = findViewById(R.id.editText_raza_mascota);
        comboDuenio = findViewById(R.id.spinner_combo_duenio);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);

        consultaListaPersona();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);

        comboDuenio.setAdapter(adaptador);




    }

    private void consultaListaPersona() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;

        personasList = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona = new Usuario();

            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personasList.add(persona);
        }

        obtenerLista();
    }

    private void obtenerLista() {

        listaPersonas = new ArrayList<String>();
        listaPersonas.add("seleccione");

        for (int i=0; i<personasList.size(); i++){


            listaPersonas.add(personasList.get(i).getNombre());
        }
    }


    public void onClick(View view){
         registrarMascota();

    }

    private void registrarMascota() {

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA, nombreMascota.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA, razaMascota.getText().toString());

        int idCombo = (int) comboDuenio.getSelectedItemId();

        if (idCombo != 0){
            Log.i("TAMANO", personasList.size()+"");
            Log.i("id combo", idCombo+"");
            Log.i("id combo - 1", (idCombo-1)+"");
            int idDuenio = personasList.get(idCombo).getId();
            Log.i("id Duenio", idDuenio+"");

            values.put(Utilidades.CAMPO_ID_DUENIO, idDuenio);

            Long idResultante = db.insert(Utilidades.TABLA_MASCOTA, Utilidades.CAMPO_ID_MASCOTA, values);

            Toast.makeText(getApplicationContext(), "Id registro: "+idResultante, Toast.LENGTH_SHORT).show();

            db.close();
        }else{
            Toast.makeText(getApplicationContext(), "Debes selecionar un Duenio", Toast.LENGTH_SHORT).show();
        }
    }

}
