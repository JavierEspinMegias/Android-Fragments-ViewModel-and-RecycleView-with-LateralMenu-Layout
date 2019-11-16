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
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<AppUser>> users;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");




        ArrayList<AppUser> localUsers = new ArrayList<>();
        // Obtenemos los datos de los usuarios
        for (int i=0; i<25; i++){
            // Los hardcodeo porque se deben recuperar de una base de datos
            AppUser newUser = new AppUser("User_name_"+i,"User_id_"+i,"User_photo"+i,i+18);

            // Una vez recuperado los agregamos a nuestro Array de Usuarios
            localUsers.add(newUser);

        }

        users = new MutableLiveData<>();
        users.setValue(localUsers);


    }
    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<AppUser>> getUsers(){return users;}
}