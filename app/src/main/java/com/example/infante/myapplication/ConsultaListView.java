package com.example.infante.myapplication;

//hecho por infante 20180804
//del tutorial de Cristian henao
//        https://youtu.be/j1SFFVUt64I

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaListView extends AppCompatActivity {

    ListView listViewPersonas;

    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_list_view);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        listViewPersonas = findViewById(R.id.listview_personas);
        consultarListaPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String informacion = "id: "+listaUsuarios.get(position).getId()+"\n";
//                informacion+="Nombre: "+listaUsuarios.get(position).getNombre()+"\n";
//                informacion+="Telefono: "+listaUsuarios.get(position).getTelefono()+"\n";

                //Toast.makeText(getApplicationContext(),informacion, Toast.LENGTH_SHORT).show();

                Usuario user = listaUsuarios.get(position);

                Intent intent = new Intent(ConsultaListView.this, DetalleUsuario.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("usuario", user);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private void consultarListaPersonas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;

        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()){

            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuario);
        }

        obtenerLista();
    }

    private void obtenerLista() {

        listaInformacion = new ArrayList<String>();

        for (int i=0; i<listaUsuarios.size(); i++){

            listaInformacion.add(listaUsuarios.get(i).getId()+" "
                    +listaUsuarios.get(i).getNombre()+" "+listaUsuarios.get(i).getTelefono());

        }
    }
}
