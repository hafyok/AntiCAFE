package com.example.anticafe.Database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.anticafe.Model.Entity.EmployeeEntity;
import com.example.anticafe.Model.Entity.PersonEntity;
import com.example.anticafe.Model.Entity.Spaces;
import com.example.anticafe.Model.Person;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EmployeeEntity.class, Spaces.class, PersonEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //public static Executor databaseWriteExecutor;

    public abstract EmployeeDao employeeDao();
    public abstract SpacesDao spacesDao();
    public abstract PersonDao personDao();


    EmployeeEntity[] employees = {
            new EmployeeEntity("Комната отдыха", 1222),
            new EmployeeEntity("Коворкинг", 3324),
            new EmployeeEntity("Общий зал", 1500)
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


                                        PersonEntity admin = new PersonEntity();
                                        admin.setEmail("admin@mail.ru");
                                        admin.setPassword("admin");
                                        admin.setRole(Person.Role.Admin);

                                        getInstance(context).personDao().addPerson(admin);

                                        PersonEntity moder = new PersonEntity();
                                        moder.setEmail("moder@mail.ru");
                                        moder.setPassword("moder");
                                        moder.setRole(Person.Role.Moder);

                                        getInstance(context).personDao().addPerson(moder);

                                        PersonEntity user = new PersonEntity();
                                        user.setEmail("user@mail.ru");
                                        user.setRole(Person.Role.User);

                                        getInstance(context).personDao().addPerson(user);


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