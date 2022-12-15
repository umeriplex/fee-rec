package com.example.feerecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.feerecorder.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<AddClassEntity> class_list;
    ClassAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clickListeners();
        get_all_classes();
    }

    private void clickListeners() {
        binding.fbButton.setOnClickListener(v->{startActivity(new Intent(MainActivity.this,AddClassActivity.class));});
    }

    private void get_all_classes(){
        AddClassDB db = Room.databaseBuilder(getApplicationContext(), AddClassDB.class, "add-class-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        AddClassDao dao = db.addClassDao();
        class_list = new ArrayList<>();
        class_list = dao.get_all_classes();
        if (class_list.isEmpty()){
            binding.noClass.setVisibility(View.VISIBLE);
        }else {
            binding.noClass.setVisibility(View.GONE);
            binding.recView.setVisibility(View.VISIBLE);
            binding.recView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ClassAdapter(this,class_list);
            adapter.notifyDataSetChanged();
            binding.recView.setAdapter(adapter);
        }
    }


}