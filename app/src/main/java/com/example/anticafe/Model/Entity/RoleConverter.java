package com.example.anticafe.Model.Entity;

import androidx.room.TypeConverter;

import com.example.anticafe.Model.Person;

public class RoleConverter {
    @TypeConverter
    public int fromRole(Person.Role role){
        if (role == Person.Role.Admin){
            return 1;
        }else if (role == Person.Role.Moder){
            return 2;
        }else {
            return 3;
        }
    }

    @TypeConverter
    public Person.Role toRole(int role){
        if (role == 1){
            return Person.Role.Admin;
        }else if(role == 2){
            return Person.Role.Moder;
        }else {
            return Person.Role.User;
        }
    }

}
