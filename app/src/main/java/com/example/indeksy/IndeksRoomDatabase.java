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
                        "Mumot Bartłomiej 141287 I2 L8",
                        "Bronka Joanna 141024 I4 L2",
                        "Matyla Olga 141276 I3 L4",
                        "Ollek Mateusz 141291 I3 L4",
                        "Rękawek Zuzanna 141304 I3 L7",
                        "Szymański Piotr 141322 I3 L2",
                        "Wiecheć Bartosz 141334 I3 L5",
                        "Mirkiewicz Martyna 141285 I3 L4",
                        "Opielewicz Szymon 141293 I3 L6",
                        "Stryszowska Aleksandra 141314 I3 L6",
                        "Tylczyński Piotr 141331 I3 L5",
                        "Wojciechowska Karolina 141336 I3 -",
                        "Kluba Mateusz 141246 I2 L8",
                        "Kowalewski Michał 141253 I2 L4",
                        "Lewandowski Wojciech 141265 I2 L4",
                        "Małecki Krzesimir 141271 I2 L8",
                        "Kulesa Katarzyna 141259 I5 L4",
                        "Olszewski Michał 141292 I3 L4",
                        "Adamczyk Joanna 141181 I1 L3",
                        "Andrysiak Agata 141183 I1 L10",
                        "Bekas Michał 141187 I1 L10",
                        "Bosacki Gerard 141193 I1 L2",
                        "Cichocki Ernest 141200 I1 L8",
                        "Cichowski Kacper 141201 I1 L2",
                        "Cwajda Hubert 141203 I1 L8",
                        "Czaplewska Eliza 141204 I1 L2",
                        "Firlej Oskar 141211 I1 L2",
                        "Gleska Piotr 141220 I1 L9",
                        "Głębowska Daria 141221 I1 L9",
                        "Gosławski Jakub 141222 I1 L5",
                        "Górczak Wiktor 141223 I1 L10",
                        "Góreczny Piotr 141224 I1 L9",
                        "Makałowski Dawid 141270 I1 L10",
                        "Manikowska Michalina 141272 I1 L10",
                        "Mąkowski Joachim 141278 I1 L4",
                        "Mularski Krzysztof 146893 I1 L9",
                        "Oberenkowski Roman 141289 I1 L7",
                        "Okonek Marcin 141290 I1 L10",
                        "Radzi Weronika 141303 I1 L8",
                        "Sroka Szymon 141312 I1 L8",
                        "Szczepaniak Michał 141317 I1 L10",
                        "Szewczyk Maciej 141318 I1 -",
                        "Szóstak Filip 141320 I1 L10",
                        "Szulc Albert 141321 I1 L6",
                        "Szymczak Aleksander 141323 I1 L9",
                        "Świerkowska Aleksandra 141325 I1 L10",
                        "Todek Jan 141326 I1 L6",
                        "Tomczyk Adam 141327 I1 L2",
                        "Trafas Zuzanna 141329 I1 L8",
                        "Trzeciak Kacper 141330 I1 L6",
                        "Walkowiak Bogumiła 141332 I1 L5",
                        "Wiśniewski Michał 141335 I1 L5",
                        "Handke Adam 141230 I2 L3",
                        "Hercog Maciej 141231 I2 L3",
                        "Januszkiewicz Kamil 127203 I2 L1",
                        "Jaroński Piotr 141237 I2 L6",
                        "Kamiński Piotr 141241 I2 L9",
                        "Karlic Mateusz 141243 I2 L7",
                        "Kolanowski Jan 141247 I2 L8",
                        "Koszela Wojciech 141251 I2 L8",
                        "Kosmalski Tomasz 141250 I2 L7",
                        "Krawczyk Adam 141257 I2 L7",
                        "Leski Kacper 141262 I2 L7",
                        "Mędzin Michał 141279 I2 L4",
                        "Mieczyński Paweł 141280 I2 L6",
                        "Mikołajczak Maria 141282 I2 L4",
                        "Mrozek Weronika 141286 I2 L10",
                        "Nowinowski Antoni 141288 I2 L8",
                        "Pawłowski Adam 141297 I2 L8",
                        "Pawłowski Igor 140759 I2 -",
                        "Pilarczyk Bartosz 141300 I2 L2",
                        "Popadowska Julia 136783 I2 -",
                        "Przybylak Dawid 141301 I2 -",
                        "Ravliv Pavlo 135412 I2 L9",
                        "Stachowiak Julia 141313 I2 L10",
                        "Strzyżewski Kajetan 141315 I2 -",
                        "Szalczyk Paweł 138859 I2 L1",
                        "Szot Grzegorz 141319 I2 L5",
                        "Bączkiewicz Agata 141186 I3 L3",
                        "Bolimowski Maksymilian 141191 I3 L5",
                        "Burdziński Radosław 141195 I3 L2",
                        "Cybulski Damian 120573 I3 -",
                        "Dzięgielewski Szymon 141030 I3 L5",
                        "Frankowski Marcin 135382 I3 L7",
                        "Grzeszczak Justyna 136244 I3 L1",
                        "Jakubiec Szymon 126997 I3 -",
                        "Karłowicz Miłosz 136564 I3 L7",
                        "Krzyżanowicz Adrian 141070 I3 L7",
                        "Mickiewicz Szymon 136771 I3 -",
                        "Lindenau Hubert 141077 I3 L6",
                        "Milewski Cezary 141284 I3 L4",
                        "Piechocki Michał 141298 I3 L5",
                        "Putra Marcin 140766 I3 L5",
                        "Radecki Radosław 115588 I3 L3",
                        "Samelak Klaudyna 141306 I3 L5",
                        "Szymkowiak Bartłomiej 141324 I3 L6",
                        "Tylski Kacper 140797 I3 L2",
                        "Wojciechowski Adam 136376 I3 L1",
                        "Ambroży Przemysław 141182 I4 L3",
                        "Basiński Lubomir 141185 I4 L7",
                        "Boruta Paweł 141192 I4 L8",
                        "Busse Antonina 141196 I4 -",
                        "Celmer Błażej 141197 I4 L3",
                        "Ciechanowicz Jan 141202 I4 L5",
                        "Czart Feliks 141205 I4 L2",
                        "Czyżewska Natalia 141207 I4 L3",
                        "Derenowski Piotr 141208 I4 L3",
                        "Frątczak Łukasz 141213 I4 L3",
                        "Gardecki Jakub 141218 I4 L7",
                        "Hanusek Kamil 132238 I4 -",
                        "Jabłońska Magdalena 141234 I4 L2",
                        "Kempa Mateusz 139952 I4 L7",
                        "Klaczyński Mateusz 136735 I4 L1",
                        "Kotyński Daniel 146905 I4 L7",
                        "Kustra Adam 141071 I4 -",
                        "Lemański Mateusz 136276 I4 L9",
                        "Lisiak Jędrzej 141078 I4 -",
                        "Martin Krzysztof 141275 I4 L4",
                        "Mietła Michał 141281 I4 -",
                        "Ogrodowczyk Mateusz 136777 I4 L9",
                        "Pietrzak Jan 141299 I4 L2",
                        "Przypaśniak Michał 141302 I4 L8",
                        "Pylnyk Roman 139056 I4 L6",
                        "Różycki Jakub 141105 I4 L2",
                        "Sztuczka Przemysław 141119 I4 L10",
                        "Weber Jerzy 140801 I4 L2",
                        "Wojtyniak Tomasz 140807 I4 L9",
                        "Zięba Marek 141337 I4 L9",
                        "Zwierzchowski Daniel 141338 I4 L5",
                        "Żelazowski Mateusz 140810 I4 L3",
                        "Falbogowski Maciej 141210 I5 L8",
                        "Frieske Jakub 141214 I5 L3",
                        "Futymski Piotr 141216 I5 L2",
                        "Gajewski Mateusz 141217 I5 L3",
                        "Graczyk Stanisław 146889 I5 L9",
                        "Gruszka Anna 141226 I5 L3",
                        "Grygorowicz Kamil 141227 I5 L9",
                        "Gumowski Przemysław 141229 I5 L10",
                        "Hromniuk Denys 141232 I5 L9",
                        "Jankowiak Tomasz 141235 I5 L3",
                        "Jankowski Szymon 141236 I5 L7",
                        "Juszczak Zuzanna 141238 I5 L3",
                        "Kaczmarek Stanisław 141240 I5 L4",
                        "Kamiński Wojciech 141242 I5 L7",
                        "Kasprzak Jakub 141244 I5 L6",
                        "Kellner Adrian 141245 I5 L4",
                        "Kosmala Eryk 141249 I5 L4",
                        "Kowalski Bartosz 141254 I5 L6",
                        "Kozłowski Filip 141256 I5 L5",
                        "Kurzawa Maciej 141260 I5 L6",
                        "Kwartnik Radosław 127321 I5 L1",
                        "Lewandowski Michał 141264 I5 L6",
                        "Maćkowiak Wiktor 141269 I5 L5",
                        "Marciniak Adam 141273 I5 L4",
                        "Marciniak Wojciech 141274 I5 L5",
                        "Papież Aleksander 141295 I5 L7",
                        "Pauter Tobiasz 141296 I5 L6",
                        "Poda Daria 139154 I5 L1",
                        "Steczkowska Zofia 136629 I5 -",
                        "Turczynowski Michał 136822 I5 L1"};


                List<Indeks> mIndeksyData = new ArrayList<Indeks>();

                for (String i:mIndeksy){
                    String[] singleIndex = i.split(" ");
                    mIndeksyData.add(new Indeks(singleIndex[0],singleIndex[1],singleIndex[2],singleIndex[3],singleIndex[4]));
                }

                for (Indeks i: mIndeksyData) {
                    dao.insert(i);
                    //.e(MainActivity.class.getSimpleName(), "ssssssssssssss" + String.valueOf(mIndeksViewModel.getAllIndeks().) +"xxxxxxxxxxxxxxxxxxx");
                }
            });
        }
    };



}
