package com.example.historicodecompras;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CompraDao {

    @Query("SELECT * FROM compra")
    List<Compra> getAll();

    @Query("SELECT * FROM compra WHERE produto LIKE :nomeProduto LIMIT 1")
    Compra findByName(String nomeProduto);

    @Insert
    void insert(Compra compra);

    @Insert
    void insertAll(List<Compra> compras);

    @Update
    void update(Compra compra);

    @Delete
    void delete(Compra compra);

    @Query("SELECT * FROM compra WHERE mes = :txt_mes")
    List<Compra> getByMonth(String txt_mes);


}
