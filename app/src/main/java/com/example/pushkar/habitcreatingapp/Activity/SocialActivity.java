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

import com.example.pushkar.habitcreatingapp.Adapters.AdapterSocial;
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

public class SocialActivity extends AppCompatActivity {

    RecyclerView rvSocial;
    AdapterSocial adapterSocial;
    ArrayList<HabitData> socialDataList = generateSocial();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "social Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: Activity start");

        rvSocial = (RecyclerView)findViewById(R.id.rvSocial);
        rvSocial.setLayoutManager(new LinearLayoutManager(this));
        adapterSocial = new AdapterSocial(socialDataList, this);
        rvSocial.setAdapter(adapterSocial);

        Log.d(TAG, "onCreate: AdapterSocial has been set");

        adapterSocial.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId==301)
                {
                    name = "Greet 3 People";
                    day = "Morning";
                    hour = 9;
                    min = 0;
                }
                else if(itemId==302)
                {
                    name = "Ask For Help If Needed";
                    day = "Evening";
                    hour = 7;
                    min = 30;
                }
                else if(itemId==303)
                {
                    name = "Time Off Your Phone";
                    day = "Evening";
                    hour = 19;
                    min = 0;
                }
                else if(itemId==304)
                {
                    name = "Make Plans";
                    day = "Night";
                    hour = 21;
                    min = 0;
                }
                else if(itemId==305)
                {
                    name = "Time Off of Social Media";
                    day = "Evening";
                    hour = 14;
                    min = 0;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(SocialActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateSocial(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(301,"Greet 3 People", "Morning", 00, 9,3));
        habitData.add(new HabitData(302,"Ask For Help If Needed", "Evening", 30, 7,3));
        habitData.add(new HabitData(303,"Time Off Your Phone", "Evening", 00, 19,3));
        habitData.add(new HabitData(304,"Make Plans", "Night", 00, 21,3));
        habitData.add(new HabitData(305,"Time Off of Social Media", "Evening", 00, 14,3));

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
