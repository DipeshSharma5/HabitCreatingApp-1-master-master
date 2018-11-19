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

public class AdapterSocial extends RecyclerView.Adapter <AdapterSocial.SocialViewHolder>{

    public static final String TAG = "AdapterSocial";

    ArrayList<HabitData> socialDataArrayList;
    Context context;

    // Object for the Interface
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        Log.d(TAG, "setOnItemClickListener: Listener initialized");

        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public AdapterSocial(ArrayList<HabitData> socialDataArrayList, Context context) {
        this.socialDataArrayList = socialDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SocialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.list_item_habits, viewGroup, false);

        Log.d(TAG, "onCreateViewHolder: onCreateVIewHolder "+ socialDataArrayList.size());

        return new SocialViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialViewHolder socialViewHolder, int position) {

        final HabitData thisHabit = socialDataArrayList.get(position);
        socialViewHolder.tvHabitName.setText(thisHabit.getHabitName());

        Log.d(TAG, "onBindViewHolder: onBindViewHolder111111111");

        socialViewHolder.btnAddHabit.setOnClickListener(new View.OnClickListener() {
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
        return socialDataArrayList.size();
    }

    static class SocialViewHolder extends RecyclerView.ViewHolder{

        TextView tvHabitName;
        Button btnAddHabit;
        View thisView;

        public SocialViewHolder(View itemView) {
            super(itemView);

            this.thisView = itemView;
            tvHabitName = (TextView)itemView.findViewById(R.id.tv_habitName);
            btnAddHabit = (Button)itemView.findViewById(R.id.btn_addHabit);
        }
    }}
