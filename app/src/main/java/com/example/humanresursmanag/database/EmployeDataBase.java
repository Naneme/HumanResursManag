package com.example.humanresursmanag.database;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.humanresursmanag.dao.EmployDao;
import com.example.humanresursmanag.model.Employ;

@Database(entities = {Employ.class}, version = 1)
public abstract class EmployeDataBase extends RoomDatabase {
    private static EmployeDataBase instance;

    public abstract EmployDao employDao();

    public static synchronized EmployeDataBase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeDataBase.class, "employ_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static synchronized EmployeDataBase getInstance() {
        return instance;
    }
}
