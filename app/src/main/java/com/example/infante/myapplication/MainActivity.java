package com.example.infante.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
    }


    public void onClick(View view){
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btn_nuevoRegistro:
                miIntent = new Intent(MainActivity.this, NuevoRegistro.class);
                break;
            case R.id.btn_consultar:
                miIntent = new Intent(MainActivity.this, ConsultarUsuarios.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}
