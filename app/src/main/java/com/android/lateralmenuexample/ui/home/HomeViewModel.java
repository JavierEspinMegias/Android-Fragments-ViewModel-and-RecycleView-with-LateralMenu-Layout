package com.android.lateralmenuexample.ui.home;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lateralmenuexample.AppUser;
import com.android.lateralmenuexample.R;
import com.android.lateralmenuexample.UserCardAdapter;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    //Contendrá los datos del modelo
    private MutableLiveData<ArrayList<AppUser>> userRefresh;
    private UserCardAdapter adapter;
    private ArrayList<AppUser> users = new ArrayList<AppUser>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment and its mutable!");



        // Obtenemos los datos de los usuarios
        for (int i=0; i<15; i++){
            // Los hardcodeo porque se deben recuperar de una base de datos
            AppUser newUser = new AppUser("User_name_"+i,"User_id_"+i,"User_photo"+i,i+18);

            // Una vez recuperado los agregamos a nuestro Array de Usuarios
            this.users.add(newUser);
        }



    }
    public LiveData<String> getText() {
        return mText;
    }

}