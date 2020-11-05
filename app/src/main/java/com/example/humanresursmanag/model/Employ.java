package com.example.humanresursmanag.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(tableName = "employ_table")
public class Employ implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String firsName;

    private String lastName;

    private String pasport;

    private String identityNumber;

    @ColumnInfo(name = "salaryCol")
    private Double salary;

    private String position;

    public Employ(String name, String firsName, String lastName, String pasport, String identityNumber
    ,Double salary, String position) {
        this.name = name;
        this.firsName = firsName;
        this.lastName = lastName;
        this.pasport = pasport;
        this.identityNumber = identityNumber;
        this.salary = salary;
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasport() {
        return pasport;
    }

    public void setPasport(String pasport) {
        this.pasport = pasport;
    }


}
