package com.example.humanresursmanag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.humanresursmanag.repository.EmployRepository;
import com.example.humanresursmanag.viewmodel.EmployViewModel;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    ImageButton employ;
    ImageButton graphic;
    //
    static EmployViewModel employViewModel;
    EmployRepository employRepository;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employViewModel = ViewModelProviders.of(this).get(EmployViewModel.class);
        employ = (ImageButton) findViewById(R.id.imageButton);
        graphic = (ImageButton) findViewById(R.id.imageButton2);
        employ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmployActivity.class);
                startActivity(intent);
            }
        });


    }


}