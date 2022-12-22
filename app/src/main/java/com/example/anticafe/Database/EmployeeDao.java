package com.example.anticafe.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.anticafe.Model.Entity.EmployeeEntity;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM EmployeeEntity")
    LiveData<List<EmployeeEntity>> getAll();

    @Query("SELECT * FROM EmployeeEntity WHERE id = :id")
    EmployeeEntity getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeEntity employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(EmployeeEntity[] employees);

    @Update
    void update(EmployeeEntity employee);

    @Delete
    void delete(EmployeeEntity employee);

}