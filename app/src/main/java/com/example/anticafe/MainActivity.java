package com.example.anticafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.example.anticafe.View.ComputersFragment;
import com.example.anticafe.View.PeopleFragment;
import com.example.anticafe.View.RoomsFragment;
import com.example.anticafe.databinding.ActivityMainBinding;
import com.example.anticafe.databinding.RoomsFragmentBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new RoomsFragment());

        ServiceLocator.init(getApplicationContext());

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new RoomsFragment());
                    break;
                case R.id.people:
                    replaceFragment(new PeopleFragment());
                    break;
                case R.id.computer:
                    replaceFragment(new ComputersFragment());
                    break;

            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }

    /*private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container)
    }*/

}