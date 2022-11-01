package com.example.anticafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.anticafe.Test.AppDatabase;
import com.example.anticafe.Test.AppRepository;
import com.example.anticafe.Test.Employee;
import com.example.anticafe.Test.EmployeeDao;
import com.example.anticafe.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AppRepository appRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        AppDatabase db =  null;
        db = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();


        EmployeeDao employeeDao = db.employeeDao();

        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "John Smith";
        employee.salary = 10000;

        employeeDao.insert(employee);
        List<Employee> employees = employeeDao.getAll();
        Employee employee1 = employeeDao.getById(1);
*/

        //AppDatabase db = App.getInstance().getDatabase();
        /*db = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();*/

        Log.d("Employee", "Before data");

        AppDatabase db = AppDatabase.getInstance(this);



        Log.d("Employee", db.employeeDao().getAll().toString());


    }

}