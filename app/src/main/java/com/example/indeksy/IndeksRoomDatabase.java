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

            databaseWriteExecutor.execute(() -> {
                IndeksDAO dao = INSTANCE.indeksDAO();
                dao.deleteAll();

                String[] mIndeksy = new String[] {
                        "Marecka Róża 145861 I1 L1",
                        "Jesko Ludmiła 175392 I1 L2",
                        "Raczyńska Ramona 145913 I2 L3",
                        "Sawka Zdzisława 134204 I3 L1",
                        "Orszulak Jadwiga 148645 I4 L3",
                        "Tarnowska Izabela 187656 I3 L5",
                        "Bieniek Zdzisława 101357 I4 L4",
                        "Wierzba Czesława 154608 I1 L3",
                        "Kosmicka Daria 112609 I2 L1",
                        "Siwik Marcin 113610 I3 L1",
                        "Trzeciak Adrian 146111 I3 L4",
                        "Dzikowski Walenty 123412 I2 L5",
                        "Strozewski Edward 196413 I2 L3",
                        "Wolkow Jacek 164814 I2 L2",
                        "Cyran Agata 123015 I3 L1"};


                List<Indeks> mIndeksyData = new ArrayList<Indeks>();

                for (String i:mIndeksy){
                    String[] singleIndex = i.split(" ");
                    mIndeksyData.add(new Indeks(singleIndex[0],singleIndex[1],singleIndex[2],singleIndex[3],singleIndex[4]));
                }

                for (Indeks i: mIndeksyData) {
                    dao.insert(i);
                }
            });
        }
    };



}
