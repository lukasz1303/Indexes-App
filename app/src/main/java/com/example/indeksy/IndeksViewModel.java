package com.example.indeksy;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IndeksViewModel extends AndroidViewModel {

    private IndeksRepository mRepository;

    private LiveData<List<Indeks>> mAllIndeks;

    public IndeksViewModel (Application application) {
        super(application);
        mRepository = new IndeksRepository(application);
        mAllIndeks = mRepository.getAllIndeks();
    }

    public LiveData<List<Indeks>> getAllIndeks() { return mAllIndeks; }

    public void insert(Indeks indeks) { mRepository.insert(indeks); }

    public LiveData<List<Indeks>> getSingleIndex(String indeks){
        return mRepository.getSingleIndex(indeks);
    }
}
