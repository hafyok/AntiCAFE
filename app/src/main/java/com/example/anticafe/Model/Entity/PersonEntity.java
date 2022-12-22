package com.example.anticafe.Model.Entity;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.anticafe.Model.Person;
import com.example.anticafe.ServiceLocator;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

@Entity(tableName = "person", primaryKeys = {"id"}, ignoredColumns = {"connections"})
@TypeConverters(RoleConverter.class)
public class PersonEntity extends Person{

    @ColumnInfo
    private String connections;

    public String getConnectionsEntity(){
        return connections;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setConnectionsDTO(String connectionsDTO) {
        this.connections = connectionsDTO;
        super.setConnections(ServiceLocator.getInstance().getGson().fromJson(connectionsDTO, new TypeToken<Map<String, String>>() {}.getType()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Map<String, String> getConnections() {
        if (super.getConnections() == null || super.getConnections().isEmpty()) {
            super.setConnections(ServiceLocator.getInstance().getGson().fromJson(
                    this.connections,
                    new TypeToken<Map<String, String>>() {}.getType()
            ));
        }
        return super.getConnections();
    }

    public static PersonEntity convertFromPerson(Person person) {
        PersonEntity dto = new PersonEntity();

        dto.setId(person.getId());
        dto.setFirst_name(person.getFirst_name());
        dto.setLast_name(person.getLast_name());
        dto.setEmail(person.getEmail());
        dto.setPassword(person.getPassword());
        dto.setPhone(person.getPhone());
        dto.setRole(person.getRole());
        dto.setConnections(person.getConnections());

        return dto;
    }


}
