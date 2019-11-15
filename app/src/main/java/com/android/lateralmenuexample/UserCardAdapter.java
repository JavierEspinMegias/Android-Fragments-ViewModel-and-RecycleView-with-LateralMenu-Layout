package com.android.lateralmenuexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//Los adaptadores heredan de RecyclerView.Adapter
public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {

    //En un adaptador es obligatorio definir una clase que herede de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //La clase ViewHolder hará referencia a los elementos de la vista creada para el recycler view
        public TextView name;
        public TextView id;
        public Button deleteUser;

        //Su constructor debera enlazar las variables del controlador con la vista
        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.user_name);
            this.id = (TextView) itemView.findViewById(R.id.user_id);
            this.deleteUser=(Button)itemView.findViewById(R.id.custom_buttom_1);
        }
    }

    //Resto de variables de la clase
    private List<AppUser> users;

    //El constructor deberá enlazar los datos del modelos con los del controlador
    public UserCardAdapter(List<AppUser> mails) {
        this.users = mails;
    }

    //Debemos crear el método onCreateViewHolder que enlace la clase ViewHolder creada con la vista
    //Debe devolver un manejador de vistas enlazado a la vista que vayamos utilizar para cada item del adaptador
    @Override
    public UserCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Especificamos el fichero XML que se utilizará como vista
        View contactView = inflater.inflate(R.layout.user_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }



    //Debemos sobrecargar onBindViewHolder que enlaza los datos del modelo con la vista
    @Override
    public void onBindViewHolder(final UserCardAdapter.ViewHolder viewHolder, final int position) {
        //Vamos obteniendo mail por mail
        final AppUser user = this.users.get(position);
        //Enlazamos los elementos de la vista con el modelo
        viewHolder.name.setText(user.name);
        viewHolder.id.setText(user.id);

        viewHolder.deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0, users.size());
            }
        });

    }

    //Debemos sobrecargar getItemCount que devuelve el número de elementos que habrá en la vista
    //Si estamos utilizando una clase contenedor de Java nos bastará, la mayoría de la veces, con devolver el valor de su método size
    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
