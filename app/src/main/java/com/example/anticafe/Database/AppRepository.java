package com.example.anticafe.Database;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.anticafe.Database.AppDatabase;
import com.example.anticafe.Model.Employee;


import java.util.List;

public class AppRepository {
    private EmployeeDao employeeDao;
    private LiveData<List<Employee>> allEmployee;
    private Context context;


    public AppRepository(Context context){//Application application
        this.context = context;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        employeeDao = appDatabase.employeeDao();
        allEmployee = employeeDao.getAll();
    }

    public LiveData<List<Employee>> getAllEmployee(){return allEmployee;}

}
