package com.example.humanresursmanag.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.humanresursmanag.GraphicsActivity;
import com.example.humanresursmanag.dao.EmployDao;
import com.example.humanresursmanag.database.EmployeDataBase;
import com.example.humanresursmanag.model.Employ;

import java.util.List;

import lombok.AllArgsConstructor;

public class EmployRepository extends Application {
    private EmployDao employDao;
    private LiveData<List<Employ>> allEmploy;
    private EmployeDataBase dataBase;

    public EmployRepository(Application application) {
        dataBase = EmployeDataBase.getInstance(application);
        employDao = dataBase.employDao();
        allEmploy = employDao.getAll();
    }


    public void insert(Employ employ) {
        new InsertEmployAnsyncTasck(employDao).execute(employ);
    }

    public void update(Employ employ) {
        new UpdateEmployAnsycTasck(employDao).execute(employ);
    }

    public void delete(Employ employ) {
        new DeleteEmployAnsycTasck(employDao).execute(employ);
    }

    public void deleteAll() {
        new DeleteAllEmployAnsycTasck(employDao).execute();
    }

    public LiveData<List<Employ>> getAllEmploy() {
        return allEmploy;
    }

    public EmployeDataBase getDataBase(){
        return dataBase;
    }


    @AllArgsConstructor
    private static class InsertEmployAnsyncTasck extends AsyncTask<Employ, Void, Void> {
        private EmployDao employDao;

        @Override
        protected Void doInBackground(Employ... employs) {
            employDao.insert(employs[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class UpdateEmployAnsycTasck extends AsyncTask<Employ, Void, Void> {
        private EmployDao employDao;

        @Override
        protected Void doInBackground(Employ... employs) {
            employDao.update(employs[0]);
            return null;
        }
    }
    @AllArgsConstructor
    private static class DeleteEmployAnsycTasck extends AsyncTask<Employ, Void, Void> {
        private EmployDao employDao;

        @Override
        protected Void doInBackground(Employ... employs) {
            employDao.delete(employs[0]);
            return null;
        }
    }

    @AllArgsConstructor
    private static class DeleteAllEmployAnsycTasck extends AsyncTask<Void, Void, Void> {
        private EmployDao noteDao;

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }

}
