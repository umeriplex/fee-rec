package com.example.feerecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.feerecorder.databinding.StudentsRowBinding;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    Context context;
    List<StudentsEntity> list;

    public StudentAdapter(Context context, List<StudentsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.students_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        holder.binding.studentNameList.setText(list.get(position).getSname());
        holder.binding.studentId.setText(list.get(position).getSid()+"");

        if (list.get(position).getFlag() == 0){
            holder.binding.checkB.setChecked(true);
        }

        holder.binding.checkB.setOnClickListener(v->{
            list.get(position).setFlag(0);
            AddClassDB db = Room.databaseBuilder(context, AddClassDB.class, "add-class-db").allowMainThreadQueries().build();
            AddClassDao dao = db.addClassDao();
            dao.updateFlag(list.get(position).getSid(),0);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        StudentsRowBinding binding;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = StudentsRowBinding.bind(itemView);
        }
    }
}
