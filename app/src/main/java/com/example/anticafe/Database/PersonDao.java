package com.example.anticafe.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.anticafe.Model.Entity.PersonEntity;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert
    void addPerson(PersonEntity person);

    @Query("SELECT * FROM person WHERE email = :email")
    LiveData<PersonEntity> getPersonByEmail(String email);

    @Query("SELECT * FROM person WHERE email = :email AND password = :password")
    LiveData<PersonEntity> getPersonByEmailAndPassword(String email, String password);

    @Query("SELECT * FROM person")
    LiveData<List<PersonEntity>> getAllPeople();

    @Update
    void updatePersonInfo(PersonEntity person);

}


