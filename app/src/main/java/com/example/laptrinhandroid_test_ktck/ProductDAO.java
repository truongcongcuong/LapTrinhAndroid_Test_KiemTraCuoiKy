package com.example.laptrinhandroid_test_ktck;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * from product")
    List<Product> getAll();

    @Insert
    void insertAll(Product... products);

    @Delete
    void delete(Product product);

    @Update
    void update(Product... products);

    @Query("Select * from product where full_name like '%'||:fullName||'%'")
    List<Product> findByName(String fullName);

    @Query("Select * from product where full_name like '%'||:fullName||'%' and job like '%'||:job||'%'")
    List<Product> findByNameAndJob(String fullName, String job);
}
