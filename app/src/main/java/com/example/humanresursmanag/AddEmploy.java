package com.example.humanresursmanag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.humanresursmanag.model.Employ;

public class AddEmploy extends AppCompatActivity {
    public static Employ employ = new Employ();
    public static final String EXTRA_ID =
            "com.example.humanresursmanag.EXTRA_ID";

    private EditText editTextName;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPasport;
    private EditText editTextIdentityNumber;
    private EditText editTextSalary;
    private Spinner spinner;
    private String getPosition;

    String [] dataSpinner = {"Supervisor","Worker"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employ);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,dataSpinner);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner = (Spinner)findViewById(R.id.action_spinner);
        spinner.setAdapter(adapter);

        spinner.setPrompt("Position");

        editTextName = findViewById(R.id.edit_text_name);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextPasport = findViewById(R.id.edit_text_pasport);
        editTextIdentityNumber = findViewById(R.id.edit_text_identityNumber);
        editTextSalary = findViewById(R.id.edit_text_salary);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPosition = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Employ");
            employ = (Employ)intent.getSerializableExtra("serializbleInput");
            editTextName.setText(employ.getName());
            editTextFirstName.setText(employ.getFirsName());
            editTextLastName.setText(employ.getLastName());
            editTextPasport.setText(employ.getPasport());
            editTextIdentityNumber.setText(employ.getIdentityNumber());
            editTextSalary.setText(String.valueOf(employ.getSalary()));
            spinner.setSelection(adapter.getPosition(employ.getPosition()));
        } else {
            setTitle("Add Employ");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_employ_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_employ:
                saveEmploy();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveEmploy() {
        employ.setName(editTextName.getText().toString());
        employ.setFirsName(editTextFirstName.getText().toString());
        employ.setLastName(editTextLastName.getText().toString());
        employ.setPasport(editTextPasport.getText().toString());
        employ.setIdentityNumber(editTextIdentityNumber.getText().toString());
        employ.setSalary(Double.valueOf(editTextSalary.getText().toString()));
        employ.setPosition(getPosition);

        if (employ.getName().trim().isEmpty() || employ.getFirsName().isEmpty() || employ.getLastName().isEmpty()) {
            Toast.makeText(this, "Please insert a name, first name, last name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra("serializedEmploy", employ);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}