package com.example.indeksy;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "indeksy")
public class Indeks {
    @NonNull
    @ColumnInfo(name = "new_group")
    private String new_group;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "surname")
    private String mSurname;

    @NonNull
    @ColumnInfo(name = "group")
    private String mGroup;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "indeks")
    private String mIndeks;


    public Indeks(String surname, String name, String indeks, String group, String new_group){
        mName = name;
        mSurname = surname;
        mGroup = group;
        this.new_group = new_group;
        mIndeks = indeks;
    }

    @Ignore
    public Indeks(String surname, String name, String indeks, String group){
        mName = name;
        mSurname = surname;
        mGroup = group;
        mIndeks = indeks;
    }

    @Ignore
    public Indeks(){}

    @NonNull
    public String getGroup() {
        return mGroup;
    }
    @NonNull
    public String getIndeks() {
        return mIndeks;
    }
    @NonNull
    public String getSurname() {
        return mSurname;
    }
    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getNew_group() {
        return new_group;
    }
}

