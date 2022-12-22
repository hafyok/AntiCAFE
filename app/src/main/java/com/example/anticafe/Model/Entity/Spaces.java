package com.example.anticafe.Model.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Spaces {

    public Spaces(String nameRoom, String description, int price) {
        this.nameRoom = nameRoom;
        this.description = description;
        this.price = price;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String nameRoom;

    @ColumnInfo
    public String description;

    @ColumnInfo
    public int price;

    @ColumnInfo
    public int booking;

    public int getId() {
        return id;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
