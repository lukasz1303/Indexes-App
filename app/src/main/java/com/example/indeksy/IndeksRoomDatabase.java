package com.example.indeksy;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Indeks.class}, version = 1, exportSchema = false)
public abstract class IndeksRoomDatabase extends RoomDatabase {

    public abstract IndeksDAO indeksDAO();
    private static volatile IndeksRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static IndeksRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IndeksRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IndeksRoomDatabase.class, "index_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                IndeksDAO dao = INSTANCE.indeksDAO();
                dao.deleteAll();

                String[] mIndeksy = new String[] {
                        "Marecka Róża 145861 I1",
                        "Jesko Ludmiła 175392 I1",
                        "Raczyńska Ramona 145913 I2",
                        "Sawka Zdzisława 134204 I3",
                        "Orszulak Jadwiga 148645 I4",
                        "Tarnowska Izabela 187656 I3",
                        "Bieniek Zdzisława 101357 I4",
                        "Wierzba Czesława 154608 I1",
                        "Kosmicka Daria 112609 I2",
                        "Siwik Marcin 113610 I3",
                        "Trzeciak Adrian 146111 I3",
                        "Dzikowski Walenty 123412 I2",
                        "Strozewski Edward 196413 I2",
                        "Wolkow Jacek 164814 I2",
                        "Cyran Agata 123015 I3"};

                List<Indeks> mIndeksyData = new ArrayList<Indeks>();

                for (String i:mIndeksy){
                    String[] singleIndex = i.split(" ");
                    mIndeksyData.add(new Indeks(singleIndex[0],singleIndex[1],singleIndex[2],singleIndex[3]));
                }

                for (Indeks i: mIndeksyData) {
                    dao.insert(i);
                    //.e(MainActivity.class.getSimpleName(), "ssssssssssssss" + String.valueOf(mIndeksViewModel.getAllIndeks().) +"xxxxxxxxxxxxxxxxxxx");
                }
            });
        }
    };



}
