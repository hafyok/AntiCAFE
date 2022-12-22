package com.example.anticafe.Database.Repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.anticafe.Model.Person;

public interface RepositoryTasks {
    <T extends Person>LiveData<T> findPerson(String email, LifecycleOwner owner);
    <T extends Person> LiveData<T> findPerson(String email, String password, LifecycleOwner owner);
    void addPerson(Person person);
    void updatePerson(Person person);
}
