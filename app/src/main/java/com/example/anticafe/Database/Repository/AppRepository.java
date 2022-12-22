package com.example.anticafe.Database.Repository;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.anticafe.Database.AppDatabase;
import com.example.anticafe.Database.EmployeeDao;
import com.example.anticafe.Database.PersonDao;
import com.example.anticafe.Model.Entity.EmployeeEntity;
import com.example.anticafe.Model.Entity.PersonEntity;
import com.example.anticafe.Model.Person;


import java.util.List;

public class AppRepository implements RepositoryTasks{
    private EmployeeDao employeeDao;
    private PersonDao personDao;
    private LiveData<List<EmployeeEntity>> allEmployee;
    private LiveData<List<PersonEntity>> allPerson;
    private Context context;
    private PersonDao mPersonDao;


    public AppRepository(Context context){//Application application
        this.context = context;
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        employeeDao = appDatabase.employeeDao();
        allEmployee = employeeDao.getAll();

        personDao = appDatabase.personDao();
        allPerson = personDao.getAllPeople();

    }

    public LiveData<List<EmployeeEntity>> getAllEmployee(){return allEmployee;}
    public LiveData<List<PersonEntity>> getAllPerson(){
        return allPerson;
    }

    @Override
    public LiveData<PersonEntity> findPerson(String email, String password, LifecycleOwner owner) {
        MutableLiveData<PersonEntity> answer = new MutableLiveData<>();

        personDao.getPersonByEmailAndPassword(email, password).observe(owner, answer::setValue);

        return answer;
    }


    @Override
    public <T extends Person> LiveData<T> findPerson(String email, LifecycleOwner owner) {
        return null;
    }

    @Override
    public void addPerson(Person person) {
        PersonEntity dto = PersonEntity.convertFromPerson(person);

        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPersonDao.addPerson(dto);
        });
    }

    @Override
    public void updatePerson(Person person) {
        PersonEntity dto = PersonEntity.convertFromPerson(person);

        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPersonDao.updatePersonInfo(dto);
        });
    }
}
