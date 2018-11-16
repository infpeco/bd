package com.example.infante.myapplication.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.infante.myapplication.R;
import com.example.infante.myapplication.entidades.Usuario;

import java.util.ArrayList;

public class ListaPersonaAdapter extends RecyclerView.Adapter<ListaPersonaAdapter.PersonasViewHolder> {

    ArrayList<Usuario> listaUsuario;

    public ListaPersonaAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }


    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_personas,null,false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {

        holder.documento.setText(listaUsuario.get(position).getId().toString());
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView documento, nombre, telefono;

        public PersonasViewHolder(View itemView) {
            super(itemView);

            documento = itemView.findViewById(R.id.txt_documento);
            nombre = itemView.findViewById(R.id.txt_nombre);
            telefono = itemView.findViewById(R.id.txt_telefono);

        }
    }
}
