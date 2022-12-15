package com.example.feerecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.example.feerecorder.databinding.ActivityAddClassBinding;

public class AddClassActivity extends AppCompatActivity {

    ActivityAddClassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addBTN.setOnClickListener(v->{
            addClass();
            Toast.makeText(this, "Class Added!", Toast.LENGTH_LONG).show();
        });



    }

    private void addClass(){
        AddClassDB db = Room.databaseBuilder(getApplicationContext(), AddClassDB.class, "add-class-db").allowMainThreadQueries().build();
        AddClassDao dao = db.addClassDao();

        boolean check = dao.class_exists(binding.nameET.getText().toString());
        if (check == false){
            dao.insertRecord(new AddClassEntity(0,binding.nameET.getText().toString()));

        }else {
            binding.nameET.setError("Given name is already exist.");
        }






    }
}