package com.example.humanresursmanag.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.humanresursmanag.model.Employ;

import java.util.List;

@Dao
public interface EmployDao {
    @Insert
    void insert(Employ employ);

    @Update
    void update(Employ employ);

    @Delete
    void delete(Employ employ);

    @Query("DElETE FROM employ_table")
    void deleteAll();

    @Query("SELECT * FROM employ_table ORDER BY lastName DESC")
    LiveData<List<Employ>>getAll();

    @Query("SELECT salaryCol FROM employ_table")
    List<Double>getSalary();

}
