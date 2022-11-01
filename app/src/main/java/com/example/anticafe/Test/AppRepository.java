package com.example.anticafe.Test;

import android.app.Application;

import java.util.List;

public class AppRepository {
    private EmployeeDao employeeDao;
    private List<Employee> allEmployee;

    public AppRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        employeeDao = appDatabase.employeeDao();
    }

}
