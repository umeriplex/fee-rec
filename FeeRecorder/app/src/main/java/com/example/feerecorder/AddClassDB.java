package com.example.feerecorder;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AddClassEntity.class,StudentsEntity.class}, version = 2)
public abstract class AddClassDB extends RoomDatabase {
    public abstract AddClassDao addClassDao();
}
