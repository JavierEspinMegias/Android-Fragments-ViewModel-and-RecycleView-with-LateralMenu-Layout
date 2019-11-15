package com.android.lateralmenuexample.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lateralmenuexample.AppUser;
import com.android.lateralmenuexample.R;
import com.android.lateralmenuexample.UserCardAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    //Contendrá los datos del modelo
    private UserCardAdapter adapter;
    private ArrayList<AppUser> users = new ArrayList<AppUser>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        final RecyclerView rvUsers = (RecyclerView) root.findViewById(R.id.recyclerViewUsers);



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        // Obtenemos los datos de los usuarios
        for (int i=0; i<15; i++){
            // Los hardcodeo porque se deben recuperar de una base de datos
            AppUser newUser = new AppUser("User_name_"+i,"User_id_"+i,"User_photo"+i,i+18);

            // Una vez recuperado los agregamos a nuestro Array de Usuarios
            this.users.add(newUser);
        }


        //Creamos el adaptador
        adapter = new UserCardAdapter(users);

        //Enlazamos la recycler view con su adaptador
        rvUsers.setAdapter(adapter);
        //Establecemos el contexto de su layout
        rvUsers.setLayoutManager(new LinearLayoutManager(root.getContext()));



        return root;
    }
}