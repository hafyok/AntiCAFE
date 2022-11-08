package com.example.anticafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.anticafe.Database.AppDatabase;
import com.example.anticafe.Database.AppRepository;
import com.example.anticafe.R;
import com.example.anticafe.databinding.ActivityMainBinding;
import com.example.anticafe.databinding.RoomsFragmentBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RoomsFragmentBinding binding;
    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceLocator.init(getApplicationContext());

    }

    /*public void addEvent(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        binding = RoomsFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
    }*/

}