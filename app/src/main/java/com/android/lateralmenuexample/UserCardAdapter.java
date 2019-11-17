package com.android.lateralmenuexample;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Random;


//Los adaptadores heredan de RecyclerView.Adapter
public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {


    private List<AppUser> users;
    public boolean isIcon = false;

    private StorageReference myStorageRef = FirebaseStorage.getInstance().getReference();

    //En un adaptador es obligatorio definir una clase que herede de RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        //La clase ViewHolder hará referencia a los elementos de la vista creada para el recycler view
        public TextView name;
        public TextView id;
        public Button deleteUser, iconLetter;
        public ImageView imageUser;
        private LinearLayout layoutOrientation;

        //Su constructor debera enlazar las variables del controlador con la vista
        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.user_name);
            this.id = (TextView) itemView.findViewById(R.id.user_id);
            this.deleteUser=(Button)itemView.findViewById(R.id.custom_buttom_1);
            this.imageUser = (ImageView)itemView.findViewById(R.id.image_user);
            this.iconLetter = (Button)itemView.findViewById(R.id.iconLetter);
            this.layoutOrientation = (LinearLayout)itemView.findViewById(R.id.layout_custom_card_child);
        }
    }

    //El constructor deberá enlazar los datos del modelos con los del controlador
    public UserCardAdapter(List<AppUser> users) {
        this.users = users;
    }

    //El constructor deberá enlazar los datos del modelos con los del controlador
    public UserCardAdapter(List<AppUser> users, boolean isAnIcon) {
        this.users = users;
        isIcon = isAnIcon;
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
        viewHolder.id.setText("-id-");
        viewHolder.deleteUser.setText("Remove");


        viewHolder.deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0, users.size());
            }
        });



        int newColor = getRandomColor();
        viewHolder.deleteUser.setTextColor(newColor);

        if (isColorDark(newColor)){
            viewHolder.iconLetter.setTextColor(viewHolder.itemView.getContext().getColor(R.color.colorAccent));
        }else{
//            viewHolder.deleteUser.setBackgroundColor(viewHolder.deleteUser.getContext().getColor(R.color.colorPrimaryDark));
        }



        if (isIcon){
            user.setPhoto(null);
            viewHolder.iconLetter.setVisibility(View.VISIBLE);
            viewHolder.imageUser.setVisibility(View.GONE);
            Drawable buttonDrawable = viewHolder.iconLetter.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, newColor);
            viewHolder.iconLetter.setBackground(buttonDrawable);

            viewHolder.iconLetter.setText(""+user.name.charAt(0));

            viewHolder.layoutOrientation.setOrientation(LinearLayout.HORIZONTAL);
        }else {
            viewHolder.imageUser.setVisibility(View.VISIBLE);
            viewHolder.layoutOrientation.setOrientation(LinearLayout.VERTICAL);
            viewHolder.iconLetter.setVisibility(View.GONE);

            myStorageRef.child("productos").child(user.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    user.setPhoto(uri);
                    Glide.with(viewHolder.imageUser.getContext().getApplicationContext()).load(users.get(position).getPhoto()).into(viewHolder.imageUser);
                }
            });
        }

    }


    public int getRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

    public boolean isColorDark(int color){
        double darkness = 1-(0.299*Color.red(color) + 0.587*Color.green(color) + 0.114*Color.blue(color))/255;
        if(darkness<0.5){
            return false;
        }else{
            return true;
        }
    }

    //Debemos sobrecargar getItemCount que devuelve el número de elementos que habrá en la vista
    //Si estamos utilizando una clase contenedor de Java nos bastará, la mayoría de la veces, con devolver el valor de su método size
    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
