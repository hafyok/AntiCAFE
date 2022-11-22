package com.example.anticafe.View;

import static java.util.stream.Collectors.toList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticafe.Game;
import com.example.anticafe.GameRepository;
import com.example.anticafe.Model.Employee;
import com.example.anticafe.R;
import com.example.anticafe.View.adapters.RoomsAdapter;
import com.example.anticafe.ViewModels.PeopleViewModel;
import com.example.anticafe.databinding.FragmentPeopleBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragment extends Fragment {
    private PeopleViewModel mViewModel;
    private FragmentPeopleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPeopleBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(PeopleViewModel.class);

        View v = binding.getRoot();
        mViewModel.getAllGames().observe(getViewLifecycleOwner(), games -> {
            Log.d("PeopleFragment_Anticafe", games.get(0).getName());
        });

        return v;
    }
}