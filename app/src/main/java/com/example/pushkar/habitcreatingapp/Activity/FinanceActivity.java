package com.example.pushkar.habitcreatingapp.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pushkar.habitcreatingapp.Adapters.AdapterFinance;
import com.example.pushkar.habitcreatingapp.Adapters.AdapterHealth;
import com.example.pushkar.habitcreatingapp.AddRitual;
import com.example.pushkar.habitcreatingapp.Interfaces.OnItemClickListener;
import com.example.pushkar.habitcreatingapp.Models.HabitData;
import com.example.pushkar.habitcreatingapp.Models.MyAlarm;
import com.example.pushkar.habitcreatingapp.R;
import com.example.pushkar.habitcreatingapp.ritual;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class FinanceActivity extends AppCompatActivity {

    RecyclerView rvFinance;
    AdapterFinance adapterFinance;
    ArrayList<HabitData> financeDataList = generateFinance();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "Finance Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        Log.d(TAG, "onCreate: Activity start");

        rvFinance = (RecyclerView)findViewById(R.id.rvFinance);
        rvFinance.setLayoutManager(new LinearLayoutManager(this));
        adapterFinance = new AdapterFinance(financeDataList, this);
        rvFinance.setAdapter(adapterFinance);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: AdapterFinance has been set");

        adapterFinance.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {
                if(itemId==601)
                {
                   name = "Don't Carry Too Much Cash";
                   day = "Morning";
                   hour = 8;
                   min = 0;
                }
                else if(itemId==602)
                {
                    name = "Make A Budget";
                    day = "Morning";
                    hour = 7;
                    min = 30;
                }
                else if(itemId==603)
                {
                    name = "Track Daily Expenses";
                    day = "Evening";
                    hour = 20;
                    min = 0;
                }
                else if(itemId==604)
                {
                    name = "Save and Invest";
                    day = "Night";
                    hour = 20;
                    min = 0;
                }
                else if(itemId==605)
                {
                    name = "Have Multiple Sources Of Income";
                    day = "Night";
                    hour = 22;
                    min = 0;
                }
                else if(itemId==606)
                {
                    name = "Reward Yourself Once A Week";
                    day = "Night";
                    hour = 22;
                    min = 30;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(FinanceActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateFinance(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(601,"Don't Carry Too Much Cash", "Morning", 00, 8,6));
        habitData.add(new HabitData(602,"Make A Budget", "Morning", 30, 7,6));
        habitData.add(new HabitData(603,"Track Daily Expenses", "Evening", 00, 20,6));
        habitData.add(new HabitData(604,"Save and Invest", "Night", 00, 21,6));
        habitData.add(new HabitData(605,"Have Multiple Sources Of Income", "Night", 00, 22,6));
        habitData.add(new HabitData(606,"Reward Yourself Once A Week", "Night", 30, 22,6));

        return habitData;
    }

    private void setAlarm(long time) {
        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this,MyAlarm.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, AddRitual.alarmid, i, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }
}
