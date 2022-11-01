package com.example.anticafe.Test;

import static com.example.anticafe.Test.App.instance;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract EmployeeDao employeeDao();

    Employee employee = new Employee("john", 10000);


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
                                        INSTANCE.employeeDao().insert(INSTANCE.employee);
                                        Log.d("DataValue", "initData");
                                    });
                                }
                            })
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}