package com.example.historicodecompras;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Compra.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract CompraDao compraDao();

}
