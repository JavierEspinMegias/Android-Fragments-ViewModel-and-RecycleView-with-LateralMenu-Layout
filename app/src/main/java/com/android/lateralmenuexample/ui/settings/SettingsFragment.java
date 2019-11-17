package com.android.lateralmenuexample.ui.settings;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.lateralmenuexample.R;
import com.android.lateralmenuexample.ui.home.HomeViewModel;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    private View root;
    private Switch checkDayNight;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.settings_fragment, container, false);
        checkDayNight = (Switch)root.findViewById(R.id.switch1);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        checkDayNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                   if (isChecked){
                       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For day mode theme
                   }else{
                       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //For night mode theme

                   }
               }
           }
        );

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

}
