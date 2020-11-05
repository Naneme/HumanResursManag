package com.example.humanresursmanag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.humanresursmanag.model.Employ;
import com.example.humanresursmanag.recycle.EmployAdapter;
import com.example.humanresursmanag.viewmodel.EmployViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.humanresursmanag.AddEmploy.EXTRA_ID;

public class EmployActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    static EmployViewModel employViewModel = MainActivity.employViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ);

        FloatingActionButton buttonAddEmploy = findViewById(R.id.button_add_employ);
        FloatingActionButton buttonGraphic = findViewById(R.id.button_graphic);

        buttonAddEmploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployActivity.this, AddEmploy.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        buttonGraphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployActivity.this, GraphicsActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final EmployAdapter adapter = new EmployAdapter();
        recyclerView.setAdapter(adapter);

         // employViewModel = ViewModelProviders.of(this).get(EmployViewModel.class);
          employViewModel.getAllEmploy().observe(this, new Observer<List<Employ>>() {

            @Override
            public void onChanged(List<Employ> employs) {
                adapter.submitList(employs);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Employ employ = adapter.getEmployAt(viewHolder.getAdapterPosition());
                employViewModel.delete(adapter.getEmployAt(viewHolder.getAdapterPosition()));
                Toast.makeText(EmployActivity.this, "Employ are deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new EmployAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Employ employ) {
                Intent intent = new Intent(EmployActivity.this, AddEmploy.class);
                intent.putExtra("serializbleInput", employ);
                intent.putExtra(EXTRA_ID, employ.getId());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {

            employViewModel.insert((Employ) data.getSerializableExtra("serializedEmploy"));
            Toast.makeText(this, "Employ save", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Employ can`t be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            employViewModel.update((Employ) data.getSerializableExtra("serializedEmploy"));
            Toast.makeText(this, "Employ updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Employ not save", Toast.LENGTH_SHORT).show();
        }
    }
}