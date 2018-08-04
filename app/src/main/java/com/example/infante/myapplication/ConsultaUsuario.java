package com.example.infante.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaUsuario extends AppCompatActivity {
    Spinner comboPersona;
    TextView txtId, txtNombre, txtTelefono;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuario);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);

        comboPersona = findViewById(R.id.spinner_usuarios);
        txtId = findViewById(R.id.txt_id);
        txtNombre = findViewById(R.id.txt_nombre);
        txtTelefono = findViewById(R.id.txt_telefono);

        consultaListaPersona();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);

        comboPersona.setAdapter(adaptador);

        comboPersona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0){
                    txtId.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getTelefono());
                }else{
                    txtId.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultaListaPersona() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;

        personasList = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);

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

        listaPersonas.add("Seleccione");

        for (int i=0; i<personasList.size(); i++){

            listaPersonas.add(personasList.get(i).getNombre());
        }
    }
}
