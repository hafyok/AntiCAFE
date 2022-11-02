package com.example.anticafe.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    public Employee(String name, long salary) {
        this.name = name;
        this.salary = salary;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public long salary;

    public String getName() {
        return name;
    }

    public long getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    /*public Employee(String name, long salary, long id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }*/

}