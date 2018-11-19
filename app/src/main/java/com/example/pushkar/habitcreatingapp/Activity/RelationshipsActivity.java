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

import com.example.pushkar.habitcreatingapp.Adapters.AdapterRelationships;
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

public class RelationshipsActivity extends AppCompatActivity {

    RecyclerView rvRelationships;
    AdapterRelationships adapterRelationships;
    ArrayList<HabitData> relationshipsDataList = generateRelationships();
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser user;
    String id1;
    String username;
    Calendar calendar;
    String name,day;
    int hour,min;

    public static final String TAG = "Relationships Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationships);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Rituals");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        username = user.getEmail();

        Log.d(TAG, "onCreate: Activity start");

        rvRelationships = (RecyclerView)findViewById(R.id.rvRelationships);
        rvRelationships.setLayoutManager(new LinearLayoutManager(this));
        adapterRelationships = new AdapterRelationships(relationshipsDataList, this);
        rvRelationships.setAdapter(adapterRelationships);

        Log.d(TAG, "onCreate: AdapterHealth has been set");

        adapterRelationships.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId==401)
                {
                    name = "Spend Quality Time Together";
                    day = "Evening";
                    hour = 18;
                    min = 0;
                }
                else if(itemId==402)
                {
                    name = "Share A Meal";
                    day = "Evening";
                    hour = 13;
                    min = 30;
                }
                else if(itemId==403)
                {
                    name = "Talk Out The Differences";
                    day = "Evening";
                    hour = 19;
                    min = 0;
                }
                else if(itemId==404)
                {
                    name = "Express Your Gratitude";
                    day = "Night";
                    hour = 21;
                    min = 0;
                }
                else if(itemId==405)
                {
                    name = "Cook Together";
                    day = "Night";
                    hour = 20;
                    min = 0;
                }
                else if(itemId==406)
                {
                    name = "Exercise Together";
                    day = "Morning";
                    hour = 6;
                    min = 0;
                }

                id1 = myRef.push().getKey();
                ritual rituals = new ritual(name,day,hour,min,false);

                myRef.child(id1).setValue(rituals);
                Toast.makeText(RelationshipsActivity.this, "Data inserted succesfully",Toast.LENGTH_SHORT).show();

                calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                        hour, min, 0);
                long ab = calendar.getTimeInMillis();
                AddRitual.alarmid++;
                setAlarm(ab);

            }
        });
    }

    static ArrayList<HabitData> generateRelationships(){

        ArrayList<HabitData> habitData = new ArrayList<>();

        habitData.add(new HabitData(401,"Spend Quality Time Together", "Evening", 00, 18,4));
        habitData.add(new HabitData(402,"Share A Meal", "Evening", 30, 13,4));
        habitData.add(new HabitData(403,"Talk Out The Differences", "Evening", 00, 19,4));
        habitData.add(new HabitData(404,"Express Your Gratitude", "Night", 00, 21,4));
        habitData.add(new HabitData(405,"Cook Together", "Night", 00, 20,4));
        habitData.add(new HabitData(406,"Exercise Together", "Morning", 00, 6,4));


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
