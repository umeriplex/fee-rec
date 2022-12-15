package com.example.feerecorder;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddClassDao {

    @Insert
    void insertRecord(AddClassEntity addClass);

    @Query("SELECT EXISTS(SELECT * FROM AddClassEntity WHERE name = :name)")
    Boolean class_exists(String name);

    @Query("SELECT * FROM AddClassEntity")
    List<AddClassEntity> get_all_classes();

    @Query("DELETE FROM AddClassEntity WHERE id = :id")
    void deleteByClassId(int id);

    @Insert
    void addStudent(StudentsEntity studentsEntity);

    @Query("SELECT * FROM StudentsEntity")
    List<StudentsEntity> get_all_students();

    @Query("UPDATE StudentsEntity SET flag=:flag WHERE sid = :id")
    void updateFlag(int id,int flag);

    @Query("DELETE FROM StudentsEntity")
    void delete();

    @Query("UPDATE StudentsEntity SET flag=:flag")
    void clear(int flag);


}
