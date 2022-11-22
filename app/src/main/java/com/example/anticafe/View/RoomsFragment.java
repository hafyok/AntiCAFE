package com.example.anticafe.View;

import static java.util.stream.Collectors.toList;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.anticafe.Database.SpacesDao;
import com.example.anticafe.MainActivity;
import com.example.anticafe.Model.Employee;
import com.example.anticafe.Model.Records;
import com.example.anticafe.Model.Spaces;
import com.example.anticafe.ViewModels.RoomsViewModel;
import com.example.anticafe.databinding.RoomsFragmentBinding;

import com.example.anticafe.View.adapters.RoomsAdapter;

import java.util.Calendar;

public class RoomsFragment extends Fragment {
    private RoomsViewModel mViewModel;
    RoomsFragmentBinding binding;
    Button btnAddEvent;
    public SpacesDao spacesDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = RoomsFragmentBinding.inflate(inflater, container, false);

        btnAddEvent = binding.btnAddEvent;

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });

        View v = binding.getRoot();

        binding.RoomsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
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

    public void addEvent(){
        Log.d("Calendar", "Begin func");
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis() + 24*60*60*1000;
        long endTime = startTime + 60 * 180 * 1000;
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Мероприятие в AntiCAFE");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "AntiCAFE, Moscow");
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);

        startActivity(calendarIntent);

        Log.d("Calendar", "End func");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}