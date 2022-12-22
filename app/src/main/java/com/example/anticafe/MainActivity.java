package com.example.anticafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


import com.example.anticafe.View.ComputersFragment;
import com.example.anticafe.View.PeopleFragment;
import com.example.anticafe.View.RoomsFragment;
import com.example.anticafe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //replaceFragment(new RoomsFragment());


        ServiceLocator.getInstance().initBase(getApplication());

        //Uri income = getIntent().getData();

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

}