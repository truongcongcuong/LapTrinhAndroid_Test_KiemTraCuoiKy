package com.example.laptrinhandroid_test_ktck;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

////Employee (not Product)
@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "full_name")
    public String fullName;
    @ColumnInfo(name = "job")
    public String job;

    public Product() {
    }

    public Product(String fullName, String job) {
        this.fullName = fullName;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
