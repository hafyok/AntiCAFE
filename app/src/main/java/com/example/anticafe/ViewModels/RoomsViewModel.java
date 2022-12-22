package com.example.anticafe.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.anticafe.Database.Repository.AppRepository;
import com.example.anticafe.Model.Entity.EmployeeEntity;
import com.example.anticafe.ServiceLocator;

import java.util.List;

public class RoomsViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    private LiveData<List<EmployeeEntity>> mEmployees;

    public RoomsViewModel(@NonNull Application application) {
        super(application);

        mRepository = ServiceLocator.getInstance().getRepository();
        mEmployees = mRepository.getAllEmployee();
    }

    public LiveData<List<EmployeeEntity>> getEmployees(){
        return mEmployees;
    }
}
