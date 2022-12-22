package com.example.anticafe;


import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.anticafe.Database.Repository.AppRepository;
import com.example.anticafe.Database.Repository.RepositoryTasks;
import com.example.anticafe.Model.Entity.PersonEntity;
import com.example.anticafe.Model.Person;
import com.example.anticafe.Network.VK_API_Logic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import okhttp3.internal.Internal;

public class ServiceLocator {
    private static ServiceLocator serviceLocator;
    private AppRepository mRepository;
    private Context context;
    private Person mPerson;
    private RepositoryTasks mTasks;
    private VK_API_Logic mVK_API;
    private Gson mGson;
    private LiveData<List<PersonEntity>> mPersons;

    /*private ServiceLocator(Context context){
        serviceLocator = this;

        mRepository = new AppRepository(context);
    }*/
    private static ServiceLocator instance = null;
    private ServiceLocator(){};

    public static ServiceLocator getInstance(){
        //return serviceLocator;
        if (instance == null){
            instance = new ServiceLocator();
        }
        return instance;
    }

    public void initBase(Application app){
        if (mRepository == null) {
            mRepository = new AppRepository(app);
        }
    }

    public AppRepository getRepository(){
        return mRepository;
    }

    public Person getPerson(){
        return mPerson;
    }

    public void setPerson(Person person){
        this.mPerson = person;
    }

    public RepositoryTasks getTasksRepository(){
        if (mRepository == null){
            return (RepositoryTasks) mRepository.getAllPerson();
        }
        return mRepository;
    }

    public VK_API_Logic getVK_API() {
        if (mVK_API == null) {
            mVK_API = new VK_API_Logic();
        }
        return mVK_API;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Gson getGson() {
        if (mGson == null) {
            mGson = new GsonBuilder()
                    .registerTypeAdapter(
                            LocalDateTime.class,
                            (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(
                                    json.getAsString(),
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                            )
                    )
                    .registerTypeAdapter(
                            LocalDateTime.class,
                            (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(src))
                    )
                    .create();
        }
        return mGson;
    }
}
