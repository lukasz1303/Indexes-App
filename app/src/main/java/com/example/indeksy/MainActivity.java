package com.example.indeksy;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private IndeksViewModel mIndeksViewModel;
    private IndeksAdapter mAdapter;
    private EditText mEditText;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mEditText = findViewById(R.id.indeks_edit_text);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels;

        mAdapter = new IndeksAdapter(width,displayMetrics.density);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEditText.setTextSize(width/displayMetrics.density/17);


        mRecyclerView.setAdapter(mAdapter);

        if (viewModelFactory == null){
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }
        mIndeksViewModel = new ViewModelProvider(this,viewModelFactory).get(IndeksViewModel.class);



        mIndeksViewModel.getAllIndeks().observe(this, indeksy -> {
            mAdapter.setIndeksy(indeksy);
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()){
                    mIndeksViewModel.getAllIndeks().observe(MainActivity.this, indeksy -> {
                        mAdapter.setIndeksy(indeksy);
                    });
                } else {
                    mIndeksViewModel.getSingleIndex(s.toString().trim()).observe(MainActivity.this, indeksy -> {
                        mAdapter.setIndeksy(indeksy);
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}

