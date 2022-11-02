package com.example.anticafe.View;

import static java.util.stream.Collectors.toList;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticafe.MainActivity;
import com.example.anticafe.Model.Employee;
import com.example.anticafe.R;
import com.example.anticafe.ViewModels.RoomsViewModel;
import com.example.anticafe.databinding.RoomsFragmentBinding;

import java.util.stream.Collectors;

import adapters.RoomsAdapter;

public class RoomsFragment extends Fragment {
    private RoomsViewModel mViewModel;
    RoomsFragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = RoomsFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        binding.RoomsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
        //return inflater.inflate(R.layout.rooms_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RoomsViewModel.class);

        mViewModel.getEmployees().observe(getViewLifecycleOwner(), employees -> {
            binding.RoomsRecyclerView.setAdapter(
                    new RoomsAdapter(
                            this,
                            employees.stream().map(Employee::getName).collect(toList())
                    )
            );
        }
        );
    }
}