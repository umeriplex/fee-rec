package com.example.feerecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.feerecorder.databinding.ActivityStudentsBinding;
import com.example.feerecorder.databinding.AddStudentDialogeBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    ActivityStudentsBinding binding;
    List<StudentsEntity> sList;
    StudentAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        binding.headerClassName.setText(name);
        binding.imageBack.setOnClickListener(view -> {onBackPressed();});


        //Getting Students
        AddClassDB db = Room.databaseBuilder(getApplicationContext(), AddClassDB.class, "add-class-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        AddClassDao dao = db.addClassDao();
        sList = new ArrayList<>();
        sList = dao.get_all_students();
        if (sList.isEmpty()){
            binding.noStudent.setVisibility(View.VISIBLE);
        }else {
            binding.noStudent.setVisibility(View.GONE);
            binding.studentRecView.setVisibility(View.VISIBLE);
            binding.studentRecView.setLayoutManager(new LinearLayoutManager(this));
            sAdapter = new StudentAdapter(this,sList);
            binding.studentRecView.setAdapter(sAdapter);
        }

        //Adding Students
        binding.addStudentImg.setOnClickListener(view -> {
            AddStudentDialogeBinding b = AddStudentDialogeBinding.inflate(getLayoutInflater());
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(b.getRoot())
                    .create();
            //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
            dialog.show();

            b.addStudentNameBtn.setOnClickListener(v->{
                dao.addStudent(new StudentsEntity(0,b.studentNameDialoge.getText().toString(),1));
                sAdapter.notifyDataSetChanged();
                dialog.dismiss();
                finish();
                startActivity(getIntent());
            });

        });

        clear();
        delete();


    }

    private void delete() {
        binding.deleteBtn.setOnClickListener(v->{
            AddClassDB db = Room.databaseBuilder(getApplicationContext(), AddClassDB.class, "add-class-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            AddClassDao dao = db.addClassDao();
            dao.delete();
            sList.clear();
            sAdapter.notifyDataSetChanged();
            finish();
            startActivity(getIntent());
        });
    }

    private void clear(){
        binding.clearBtn.setOnClickListener(v->{
            AddClassDB db = Room.databaseBuilder(getApplicationContext(), AddClassDB.class, "add-class-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            AddClassDao dao = db.addClassDao();
            dao.clear(1);
            sAdapter.notifyDataSetChanged();
            finish();
            startActivity(getIntent());
        });

    }
}