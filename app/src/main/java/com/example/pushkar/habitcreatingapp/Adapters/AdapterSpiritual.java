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

public class AdapterSpiritual extends RecyclerView.Adapter <AdapterSpiritual.SpiritualViewHolder>{

    public static final String TAG = "AdapterHealth";

    ArrayList<HabitData> spiritualDataArrayList;
    Context context;

    // Object for the Interface
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        Log.d(TAG, "setOnItemClickListener: Listener initialized");

        this.onItemClickListener = onItemClickListener;
    }

    // constructor
    public AdapterSpiritual(ArrayList<HabitData> spiritualDataArrayList, Context context) {
        this.spiritualDataArrayList = spiritualDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpiritualViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.list_item_habits, viewGroup, false);

        Log.d(TAG, "onCreateViewHolder: onCreateVIewHolder "+ spiritualDataArrayList.size());

        return new SpiritualViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpiritualViewHolder spiritualViewHolder, int position) {

        final HabitData thisHabit = spiritualDataArrayList.get(position);
        spiritualViewHolder.tvHabitName.setText(thisHabit.getHabitName());

        Log.d(TAG, "onBindViewHolder: onBindViewHolder111111111");

        spiritualViewHolder.btnAddHabit.setOnClickListener(new View.OnClickListener() {
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
        return spiritualDataArrayList.size();
    }

    static class SpiritualViewHolder extends RecyclerView.ViewHolder{

        TextView tvHabitName;
        Button btnAddHabit;
        View thisView;

        public SpiritualViewHolder(View itemView) {
            super(itemView);

            this.thisView = itemView;
            tvHabitName = (TextView)itemView.findViewById(R.id.tv_habitName);
            btnAddHabit = (Button)itemView.findViewById(R.id.btn_addHabit);
        }
    }}
