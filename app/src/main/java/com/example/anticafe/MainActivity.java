package com.example.anticafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;


import com.example.anticafe.Database.AppDatabase;
import com.example.anticafe.Database.AppRepository;
import com.example.anticafe.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ServiceLocator.init(getApplicationContext());

    }

}