package com.example.infante.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.infante.myapplication.entidades.Mascota;
import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarMascota extends AppCompatActivity {

    ListView listViewMascotas;

    ArrayList<String> listaInformacion;
    ArrayList<Mascota> listaMascota;

    SQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_mascota);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        listViewMascotas = findViewById(R.id.listview_mascota);

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewMascotas.setAdapter(adaptador);

        listViewMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String informacion = "id: "+listaUsuarios.get(position).getId()+"\n";
//                informacion+="Nombre: "+listaUsuarios.get(position).getNombre()+"\n";
//                informacion+="Telefono: "+listaUsuarios.get(position).getTelefono()+"\n";

                //Toast.makeText(getApplicationContext(),informacion, Toast.LENGTH_SHORT).show();

                Mascota mascota = listaMascota.get(position);

                Intent intent = new Intent(ConsultarMascota.this, DetalleMascota.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("mascota", mascota);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private void consultarListaPersonas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Mascota mascota = null;

        listaMascota = new ArrayList<Mascota>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MASCOTA, null);

        while (cursor.moveToNext()){

            mascota = new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));

            listaMascota.add(mascota);
        }

        obtenerLista();
    }

    private void obtenerLista() {

        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaMascota.size(); i++){

            listaInformacion.add(listaMascota.get(i).getIdMascota()+" "
                    +listaMascota.get(i).getNombreMascota());

        }
    }
}
