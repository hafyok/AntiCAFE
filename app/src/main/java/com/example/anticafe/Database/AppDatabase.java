package com.example.anticafe.Database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.anticafe.Model.Employee;
import com.example.anticafe.Model.Spaces;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class, Spaces.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract EmployeeDao employeeDao();
    public abstract SpacesDao spacesDao();

    Employee[] employees = {
            new Employee("john,", 1222),
            new Employee("lakjdf", 3324)
    };

    Spaces[] spaces = {
            new Spaces("Комната отдыха", "Комната отдыха", 1000),
            new Spaces("Коворкинг", "Комната для коворкинга", 1500)
    };



    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "datebayo")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("DataValue", "preInitData");

                                    AppDatabase.databaseWriteExecutor.execute(() -> {
                                        INSTANCE.employeeDao().insertAll(INSTANCE.employees);
                                        INSTANCE.spacesDao().insertAll(INSTANCE.spaces);

                                        Log.d("DataValue", "initData");
                                    });


                                }
                            })
                            //.allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}