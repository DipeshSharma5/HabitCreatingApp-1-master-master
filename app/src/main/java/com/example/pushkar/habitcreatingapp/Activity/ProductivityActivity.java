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

import com.example.pushkar.habitcreatingapp.Adapters.AdapterProductivity;
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

public class ProductivityActivity extends AppCompatActivity {

    RecyclerView rvProductivity;
    AdapterProductivity adapterProductivity;
    ArrayList<HabitData> productivityDataList = generateProductivity();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "Productivity Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productivity);

        Log.d(TAG, "onCreate: Activity start");

        rvProductivity = (RecyclerView)findViewById(R.id.rvProductivity);
        rvProductivity.setLayoutManager(new LinearLayoutManager(this));
        adapterProductivity = new AdapterProductivity(productivityDataList, this);
        rvProductivity.setAdapter(adapterProductivity);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: AdapterProductivity has been set");

        adapterProductivity.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId==501)
                {
                    name = "Plan Efficiently";
                    day = "Morning";
                    hour = 7;
                    min = 0;
                }
                else if(itemId==502)
                {
                    name = "Take Breaks";
                    day = "Evening";
                    hour = 13;
                    min = 30;
                }
                else if(itemId==503)
                {
                    name = "Use A To-Do List";
                    day = "Morning";
                    hour = 8;
                    min = 0;
                }
                else if(itemId==504)
                {
                    name ="Eliminate Distractions";
                    day = "Evening";
                    hour = 17;
                    min = 0;
                }
                else if(itemId==505)
                {
                    name = "Make Goals";
                    day = "Night";
                    hour = 21;
                    min = 30;
                }
                else if(itemId==506)
                {
                    name = "Get Enough Sleep";
                    day = "Night";
                    hour = 22;
                    min = 0;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(ProductivityActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateProductivity(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(501,"Plan Efficiently", "Morning", 00, 7,5));
        habitData.add(new HabitData(502,"Take Breaks", "Evening", 30, 13,5));
        habitData.add(new HabitData(503,"Use A To-Do List", "Morning", 00, 8,5));
        habitData.add(new HabitData(504,"Eliminate Distractions", "Evening", 00, 17,5));
        habitData.add(new HabitData(505,"Make Goals", "Night", 30, 21,5));
        habitData.add(new HabitData(506,"Get Enough Sleep", "Night", 00, 22,5));

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
