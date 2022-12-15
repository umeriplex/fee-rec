package com.example.feerecorder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.feerecorder.databinding.ClassRowBinding;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.classViewHolder> {

    Context context;
    List<AddClassEntity> list;

    public ClassAdapter(Context context, List<AddClassEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public classViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new classViewHolder(LayoutInflater.from(context).inflate(R.layout.class_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull classViewHolder holder, int position) {

        holder.binding.className.setText(list.get(position).getName());
        holder.binding.classId.setText(list.get(position).getId() + "");
        holder.binding.classItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete Class")
                        .setMessage("are you sure ?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> {
                            //set what would happen when positive button is clicked
                            AddClassDB db = Room.databaseBuilder(context, AddClassDB.class, "add-class-db").allowMainThreadQueries().build();
                            AddClassDao dao = db.addClassDao();
                            dao.deleteByClassId(list.get(position).getId());
                            list.remove(position);
                            dao.delete();
                            notifyDataSetChanged();

                        })
                        .setNegativeButton("No", (dialogInterface, i) -> {

                        })
                        .show();
                return true;
            }
        });
        holder.binding.classItem.setOnClickListener(v->{
            Intent intent = new Intent(context,StudentsActivity.class);
            intent.putExtra("name",list.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty())
            return list.size();
        else
            return 0;
    }

    class classViewHolder extends RecyclerView.ViewHolder {
        ClassRowBinding binding;

        public classViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ClassRowBinding.bind(itemView);
        }
    }
}
