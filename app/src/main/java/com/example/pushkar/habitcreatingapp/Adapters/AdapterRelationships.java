package com.example.pushkar.habitcreatingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pushkar.habitcreatingapp.Interfaces.OnItemClickListener;
import com.example.pushkar.habitcreatingapp.Models.HabitData;
import com.example.pushkar.habitcreatingapp.R;

import java.util.ArrayList;

public class AdapterRelationships extends RecyclerView.Adapter <AdapterRelationships.RelationshipsViewHolder>{

    public static final String TAG = "AdapterRelationships";

    ArrayList<HabitData> relationshipsDataArrayList;
    Context context;

    // Object for the Interface
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        Log.d(TAG, "setOnItemClickListener: Listener initialized");

        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public AdapterRelationships(ArrayList<HabitData> relationshipsDataArrayList, Context context) {
        this.relationshipsDataArrayList = relationshipsDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RelationshipsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.list_item_habits, viewGroup, false);

        Log.d(TAG, "onCreateViewHolder: onCreateVIewHolder "+ relationshipsDataArrayList.size());

        return new RelationshipsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RelationshipsViewHolder relationshipsViewHolder, int position) {

        final HabitData thisHabit = relationshipsDataArrayList.get(position);
        relationshipsViewHolder.tvHabitName.setText(thisHabit.getHabitName());

        Log.d(TAG, "onBindViewHolder: onBindViewHolder111111111");

        relationshipsViewHolder.btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(thisHabit.getHabitId(), view);
                }
                Log.d(TAG, "onClick: View Clicked222222222222222");
            }
        });


    }

    @Override
    public int getItemCount() {
        return relationshipsDataArrayList.size();
    }

    static class RelationshipsViewHolder extends RecyclerView.ViewHolder{

        TextView tvHabitName;
        Button btnAddHabit;
        View thisView;

        public RelationshipsViewHolder(View itemView) {
            super(itemView);

            this.thisView = itemView;
            tvHabitName = (TextView)itemView.findViewById(R.id.tv_habitName);
            btnAddHabit = (Button)itemView.findViewById(R.id.btn_addHabit);
        }
    }}
