package com.example.anticafe.View;


import static java.util.stream.Collectors.toList;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticafe.Game;
import com.example.anticafe.Model.Employee;
import com.example.anticafe.PaginatedGamesPOJO;
import com.example.anticafe.View.adapters.PeopleAdapter;
import com.example.anticafe.ViewModels.PeopleViewModel;
import com.example.anticafe.databinding.FragmentPeopleBinding;

public class PeopleFragment extends Fragment {
    private PeopleViewModel mViewModel;
    private FragmentPeopleBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPeopleBinding.inflate(inflater, container, false);

        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        View v = binding.getRoot();
        mViewModel.getAllGames().observe(getViewLifecycleOwner(), games -> {
            /*Log.d("PeopleFragment_Anticafe", games.get(0).getName());
            Log.d("PeopleFragment_Anticafe", games.get(0).getBackground_img());*/
            binding.peopleRecyclerView.setAdapter(
                    new PeopleAdapter(
                            this,
                            games.stream().map(Game::getName).collect(toList())
                    )
            );

            /*for (int i = 1; i < 20; i++){
                Log.d("PeopleFragment_Anticafe", games.get(i).getName());
                if (games.get(i).getBackground_img() == null){
                    Log.d("PeopleFragment_Anticafe", "Not found img");
                }else{
                    Log.d("PeopleFragment_Anticafe", games.get(i).getBackground_img());
                }

            }*/
        });

        binding.peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
}