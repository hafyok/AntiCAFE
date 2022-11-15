package com.example.anticafe.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Records {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String name_room;
    @ColumnInfo
    public int number_record;
    @ColumnInfo
    public String name;

    public Records(String name_room, int number_record) {
        this.name_room = name_room;
        this.number_record = number_record;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName_room() {
        return name_room;
    }

    public int getNumber_record() {
        return number_record;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_room(String name_room) {
        this.name_room = name_room;
    }

    public void setNumber_record(int number_record) {
        this.number_record = number_record;
    }

    public void setName(String name) {
        this.name = name;
    }
}
