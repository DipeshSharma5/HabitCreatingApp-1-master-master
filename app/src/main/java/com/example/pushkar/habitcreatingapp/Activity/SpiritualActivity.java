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

import com.example.pushkar.habitcreatingapp.Adapters.AdapterHealth;
import com.example.pushkar.habitcreatingapp.Adapters.AdapterSpiritual;
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

public class SpiritualActivity extends AppCompatActivity {

    RecyclerView rvSpiritual;
    AdapterHealth adapterSpiritual;
    ArrayList<HabitData> spiritualDataList = generateSpiritual();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "Spiritual Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiritual);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: Activity start");

        rvSpiritual = (RecyclerView)findViewById(R.id.rvSpiritual);
        rvSpiritual.setLayoutManager(new LinearLayoutManager(this));
        adapterSpiritual = new AdapterHealth(spiritualDataList, this);
        rvSpiritual.setAdapter(adapterSpiritual);

        Log.d(TAG, "onCreate: AdapterSpiritual has been set");

        adapterSpiritual.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId==201)
                {
                    name = "Greet To Your Parernts";
                    day = "Morning";
                    hour = 7;
                    min = 0;
                }
                else if(itemId==202)
                {
                    name = "Meditate";
                    day = "Morning";
                    hour = 7;
                    min = 30;
                }
                else if(itemId==203)
                {
                    name = "Grateful To 3 Things";
                    day = "Evening";
                    hour = 20;
                    min = 0;
                }
                else if(itemId==204)
                {
                    name = "Forgive";
                    day = "Night";
                    hour = 21;
                    min = 0;
                }
                else if(itemId==205)
                {
                    name = "Spend Time Alone";
                    day = "Night";
                    hour = 22;
                    min = 0;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(SpiritualActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateSpiritual(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(201,"Greet To Your Parernts", "Morning", 00, 7,2));
        habitData.add(new HabitData(202,"Meditate", "Morning", 30, 7,2));
        habitData.add(new HabitData(203,"Grateful To 3 Things", "Evening", 00, 20,2));
        habitData.add(new HabitData(204,"Forgive", "Night", 00, 21,2));
        habitData.add(new HabitData(205,"Spend Time Alone", "Night", 00, 22,2));

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
