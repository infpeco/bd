package com.example.infante.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.infante.myapplication.adaptadores.ListaPersonaAdapter;
import com.example.infante.myapplication.entidades.Usuario;
import com.example.infante.myapplication.utilidades.Utilidades;

import java.util.ArrayList;

public class ListaPersonasRecycler extends AppCompatActivity{

    ArrayList<Usuario> listaUsuario;

    RecyclerView recyclerViewUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_persona_recycler);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listaUsuario = new ArrayList<>();
        recyclerViewUsuarios = findViewById(R.id.recycler_view);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultaListaPersonas();

        ListaPersonaAdapter adapter = new ListaPersonaAdapter(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);
    }

    private void consultaListaPersonas() {

        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()){

            usuario = new Usuario();

            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuario.add(usuario);
        }
    }
}
