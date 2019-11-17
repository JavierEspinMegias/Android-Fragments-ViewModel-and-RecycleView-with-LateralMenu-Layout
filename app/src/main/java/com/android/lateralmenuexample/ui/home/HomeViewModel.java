package com.android.lateralmenuexample.ui.home;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.lateralmenuexample.AppUser;
import com.android.lateralmenuexample.MainActivity;
import com.android.lateralmenuexample.R;
import com.android.lateralmenuexample.UserCardAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();












    

    private MutableLiveData<String> mText = new MutableLiveData<>();
    private MutableLiveData<ArrayList<AppUser>> users = new MutableLiveData<>();

    private ArrayList<AppUser> localUsers = new ArrayList<>();

    private AppUser newUser = new AppUser();

    public HomeViewModel() {


        mText.setValue("This is a fragment with a live data recycler view");

        myRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                localUsers.clear();
                for (DataSnapshot pojoUser:dataSnapshot.getChildren()){
                    newUser = (AppUser) pojoUser.getValue(AppUser.class);
                    // Una vez recuperado los agregamos a nuestro Array de Usuarios
                    localUsers.add(newUser);
                }
                // Seteamos nuestra variable LIVEDATA!
                users.setValue(localUsers);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<AppUser>> getUsers(){return users;}
}