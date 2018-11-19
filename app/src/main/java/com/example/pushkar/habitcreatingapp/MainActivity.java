package com.example.pushkar.habitcreatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pushkar.habitcreatingapp.Fragments.Fragment_home;
import com.example.pushkar.habitcreatingapp.Fragments.Fragment_journeys;
import com.example.pushkar.habitcreatingapp.Fragments.Fragment_progress;
import com.example.pushkar.habitcreatingapp.Models.RitualData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String username,z;
    List<RitualData> ritualDataList;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        myRef = mFirebaseDatabase.getReference();
        username = user.getEmail();
        userID = user.getUid();
        ritualDataList = new ArrayList<RitualData>();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new Fragment_home());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Setting) {
            Intent sett = new Intent(MainActivity.this,setting.class);
            startActivity(sett);
        }
        else
        if (id == R.id.refresh) {

            myRef.child("Rituals").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child: dataSnapshot.getChildren()) {
                        RitualData ritualData = child.getValue(RitualData.class);
                        ritualDataList.add(ritualData);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment){

        if(fragment != null)
        {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }

        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()){

            case R.id.navigation_home :
                fragment = new Fragment_home();
                break;

            case R.id.navigation_progress :
                fragment = new Fragment_progress();
                break;

            case R.id.navigation_journeys:
                fragment = new Fragment_journeys();
                break;

        }

        return  loadFragment(fragment);
    }

    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }

}
