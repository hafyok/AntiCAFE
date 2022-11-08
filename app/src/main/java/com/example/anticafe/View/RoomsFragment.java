package com.example.anticafe.View;

import static java.util.stream.Collectors.toList;

import android.content.Intent;
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

import com.example.anticafe.MainActivity;
import com.example.anticafe.Model.Employee;
import com.example.anticafe.ViewModels.RoomsViewModel;
import com.example.anticafe.databinding.RoomsFragmentBinding;

import com.example.anticafe.View.adapters.RoomsAdapter;

import java.util.Calendar;

public class RoomsFragment extends Fragment {
    private RoomsViewModel mViewModel;
    RoomsFragmentBinding binding;
    Button btnAddEvent;

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

    public void addEvent(){
        Log.d("Calendar", "Begin func");
        /*Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
        Calendar beginTime = Calendar.getInstance().set(2012, 0, 19, 7, 30);
        Calendar endTime = Calendar.getInstance().set(2012, 0, 19, 10, 30);
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
        calendarIntent.putExtra(CalendarContract.Events.TITLE, "Testsss");
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Locationnn");*/
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "Бронь комнаты");
        startActivity(intent);
        Log.d("Calendar", "End func");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}