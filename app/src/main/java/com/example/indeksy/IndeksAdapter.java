package com.example.indeksy;

import android.content.Context;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndeksAdapter extends RecyclerView.Adapter<IndeksAdapter.IndeksViewHolder> {

    private List<Indeks> mIndeksy;
    public Context mContext;
    private static float mWidth;
    private static float mDensity;

    public static class IndeksViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private TextView mSurnameTextView;
        private TextView mIndeksTextView;
        private TextView mGroupTextView;

        public IndeksViewHolder (View itemView){
            super(itemView);
            float textSize = mWidth/mDensity/16;

            mNameTextView = itemView.findViewById(R.id.tv_name);
            mSurnameTextView = itemView.findViewById(R.id.tv_surname);
            mIndeksTextView = itemView.findViewById(R.id.tv_indeks);
            mGroupTextView = itemView.findViewById(R.id.tv_group);
            mNameTextView.setTextSize(textSize);
            mSurnameTextView.setTextSize(textSize);
            mIndeksTextView.setTextSize(textSize);
            mGroupTextView.setTextSize(textSize);

        }
    }

    public IndeksAdapter(float width, float density){
        mWidth = width;
        mDensity = density;
    }

    @NonNull
    @Override
    public IndeksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indeks_item,parent,false);
        IndeksViewHolder viewHolder = new IndeksViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IndeksViewHolder holder, int position) {
        holder.mSurnameTextView.setText(mIndeksy.get(position).getSurname());
        holder.mNameTextView.setText(mIndeksy.get(position).getName());
        holder.mIndeksTextView.setText(mIndeksy.get(position).getIndeks());
        holder.mGroupTextView.setText(mIndeksy.get(position).getGroup());

        mContext = holder.itemView.getContext();
    }

    @Override
    public int getItemCount() {
        if (mIndeksy != null){
            return mIndeksy.size();
        }
        return 0;
    }

    public void setIndeksy(List<Indeks> indeksy) {
        mIndeksy = indeksy;
        notifyDataSetChanged();
    }
}
