package com.example.anticafe.View;


import static java.util.stream.Collectors.toList;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticafe.Model.Game;
import com.example.anticafe.Model.Person;
import com.example.anticafe.ServiceLocator;
import com.example.anticafe.View.adapters.PeopleAdapter;
import com.example.anticafe.ViewModels.PeopleViewModel;
import com.example.anticafe.databinding.FragmentPeopleBinding;

public class PeopleFragment extends Fragment {
    private PeopleViewModel mViewModel;
    private FragmentPeopleBinding binding;
    private Person.Role role;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPeopleBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        switch (ServiceLocator.getInstance().getPerson().getRole()){

        }

        View v = binding.getRoot();
        mViewModel.getAllGames().observe(getViewLifecycleOwner(), games -> {
            binding.peopleRecyclerView.setAdapter(
                    new PeopleAdapter(
                            this,
                            games.stream().map(Game::getName).collect(toList()),
                            games.stream().map(Game::getBackground_img).collect(toList())
                    )
            );
        });

        binding.peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return v;
    }
}