package com.example.indeksy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IndeksDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Indeks indeks);

    @Query("DELETE FROM indeksy")
    void deleteAll();

    @Query("SELECT * from indeksy ORDER BY surname ASC")
    LiveData<List<Indeks>> getAlphabetizedWords();

    @Query("SELECT * FROM indeksy WHERE indeks LIKE '%' || :indeks || '%' or name LIKE '%' || :indeks || '%' or surname LIKE '%' || :indeks || '%' or `group` LIKE '%' || :indeks || '%'  or `new_group` LIKE '%' || :indeks || '%' ORDER BY surname ASC")
    LiveData<List<Indeks>> getSingleIndex(String indeks);
}

