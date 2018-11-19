package com.example.pushkar.habitcreatingapp.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class HealthActivity extends AppCompatActivity {

    RecyclerView rvHealth;
    AdapterHealth adapterHealth;
    ArrayList<HabitData> healthDataList = generateHealth();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "Health Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: Activity start");

        rvHealth = (RecyclerView)findViewById(R.id.rvHealth);
        rvHealth.setLayoutManager(new LinearLayoutManager(this));
        adapterHealth = new AdapterHealth(healthDataList, this);
        rvHealth.setAdapter(adapterHealth);

        Log.d(TAG, "onCreate: AdapterHealth has been set");

        adapterHealth.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId==101)
                {
                    name = "Drink Water";
                    day = "Morning";
                    hour = 7;
                    min = 0;
                }
                else if(itemId==102)
                {
                    name = "Eat Breakfast";
                    day = "Morning";
                    hour = 7;
                    min = 30;
                }
                else if(itemId==103)
                {
                    name = "Exercise";
                    day = "Evening";
                    hour = 19;
                    min = 0;
                }
                else if(itemId==104)
                {
                    name = "Count Your Step";
                    day = "Night";
                    hour = 21;
                    min = 0;
                }
                else if(itemId==105)
                {
                    name = "Sleep Well";
                    day = "Night";
                    hour = 22;
                    min = 0;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(HealthActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateHealth(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(101,"Drink Water", "Morning", 00, 7,1));
        habitData.add(new HabitData(102,"Eat Breakfast", "Morning", 30, 7,1));
        habitData.add(new HabitData(103,"Exercise", "Evening", 00, 19,1));
        habitData.add(new HabitData(104,"Count Your Step", "Night", 00, 21,1));
        habitData.add(new HabitData(105,"Sleep Well", "Night", 00, 22,1));

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
