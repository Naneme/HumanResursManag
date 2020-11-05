package com.example.humanresursmanag.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.humanresursmanag.model.Employ;
import com.example.humanresursmanag.repository.EmployRepository;

import java.util.List;

public class EmployViewModel extends AndroidViewModel {

    static EmployRepository employRepository;
    private LiveData<List<Employ>> allEmploy;
    private List<Double> allSalary;

    public EmployViewModel(@NonNull Application application) {
        super(application);
        employRepository = new EmployRepository(application);
        allEmploy = employRepository.getAllEmploy();
    }

    public void insert(Employ employ) {
        employRepository.insert(employ);
    }

    public void update(Employ employ) {
        employRepository.update(employ);
    }

    public void delete(Employ employ) {
        employRepository.delete(employ);
    }

    public void deleteAll() {
        employRepository.deleteAll();
    }

    public LiveData<List<Employ>> getAllEmploy() {
        return allEmploy;
    }

    public List<Double>getAllSalary(){
        return allSalary;
    }


}
