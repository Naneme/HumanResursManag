package com.example.humanresursmanag.calculation;

import com.example.humanresursmanag.model.Employ;

import java.util.ArrayList;
import java.util.List;

public class Calculation {

    private Calculation() {
    }

    public static Double middleSalary(List<Employ> list) {
        List<Double>middle = new ArrayList<Double>();
        for (int i = 0; i < list.size(); i++) {
            middle.add(list.get(i).getSalary());
        }
        Double sum = 0.0;
        for (int i = 0; i < middle.size(); i++) {
            sum += middle.get(i);
        }

        return sum/middle.size();

    }
}
