package com.example.feerecorder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudentsEntity {
    @ColumnInfo(name = "sid")
    @PrimaryKey(autoGenerate = true)
    int sid;

    @ColumnInfo(name = "sname")
    String sname;

    @ColumnInfo(name = "flag")
    int flag = 1;

    public StudentsEntity(int sid, String sname, int flag) {
        this.sid = sid;
        this.sname = sname;
        this.flag = flag;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
