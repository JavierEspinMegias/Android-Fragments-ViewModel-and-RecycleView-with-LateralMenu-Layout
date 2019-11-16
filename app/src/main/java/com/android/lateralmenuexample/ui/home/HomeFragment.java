package com.android.lateralmenuexample.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.lateralmenuexample.AppUser;
import com.android.lateralmenuexample.R;
import com.android.lateralmenuexample.UserCardAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.convert;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ArrayList<AppUser> users = new ArrayList<AppUser>();
    private UserCardAdapter adapter;
    private RecyclerView rvUsers;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);



        final TextView textView = root.findViewById(R.id.text_home);

        //Contendrá los datos del modelo
        rvUsers = (RecyclerView) root.findViewById(R.id.recyclerViewUsers);


        //Creamos el adaptador
        adapter = new UserCardAdapter(users);

        //Enlazamos la recycler view con su adaptador
        rvUsers.setAdapter(adapter);
        //Establecemos el contexto de su layout
        rvUsers.setLayoutManager(new LinearLayoutManager(root.getContext()));


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        homeViewModel.getUsers().observe(this, new Observer<ArrayList<AppUser>>() {
            @Override
            public void onChanged(ArrayList<AppUser> appUsers) {
                users = appUsers;
                adapter =new UserCardAdapter(users);
                rvUsers.setAdapter(adapter);

                if (root.getContext().getResources().getConfiguration().orientation == 1){
                    rvUsers.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
                }else{
                    rvUsers.setLayoutManager(new StaggeredGridLayoutManager(6,StaggeredGridLayoutManager.VERTICAL));
                }
            }
        });


        return root;
    }

}