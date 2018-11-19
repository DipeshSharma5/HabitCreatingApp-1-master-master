package com.example.pushkar.habitcreatingapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pushkar.habitcreatingapp.Activity.FinanceActivity;
import com.example.pushkar.habitcreatingapp.Activity.HealthActivity;
import com.example.pushkar.habitcreatingapp.Activity.ProductivityActivity;
import com.example.pushkar.habitcreatingapp.Activity.RelationshipsActivity;
import com.example.pushkar.habitcreatingapp.Activity.SocialActivity;
import com.example.pushkar.habitcreatingapp.Activity.SpiritualActivity;
import com.example.pushkar.habitcreatingapp.Adapters.AdapterJourneys;
import com.example.pushkar.habitcreatingapp.Interfaces.OnItemClickListener;
import com.example.pushkar.habitcreatingapp.Models.JourneysData;
import com.example.pushkar.habitcreatingapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_journeys extends Fragment {

    RecyclerView rvJourneyList;
    ArrayList<JourneysData> journeysDataArrayList = generateJourneys();
    AdapterJourneys adapter_journeys;


    public Fragment_journeys() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_journeys, container, false);

        rvJourneyList = (RecyclerView)view.findViewById(R.id.rvJourneys);

        adapter_journeys = new AdapterJourneys(journeysDataArrayList,getActivity());
        rvJourneyList.setAdapter(adapter_journeys);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rvJourneyList.setLayoutManager(layoutManager);

        adapter_journeys.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int itemId, View clickedView) {

                if(itemId == 1){

                    Intent intentHealth = new Intent(getActivity(), HealthActivity.class);
                    intentHealth.putExtra("JourneyId", itemId);
                    startActivity(intentHealth);

                }
                else if (itemId == 2){

                    Intent intentSpiritual = new Intent(getActivity(), SpiritualActivity.class);
                    intentSpiritual.putExtra("JourneyId", itemId);
                    startActivity(intentSpiritual);

                }
                else if (itemId == 3){

                    Intent intentSocial = new Intent(getActivity(), SocialActivity.class);
                    intentSocial.putExtra("JourneyId", itemId);
                    startActivity(intentSocial);

                }
                else if (itemId == 4){

                    Intent intentRelationships = new Intent(getActivity(), RelationshipsActivity.class);
                    intentRelationships.putExtra("JourneyId", itemId);
                    startActivity(intentRelationships);

                }
                else if (itemId == 5){

                    Intent intentProductivity = new Intent(getActivity(), ProductivityActivity.class);
                    intentProductivity.putExtra("JourneyId", itemId);
                    startActivity(intentProductivity);

                }
                else if (itemId == 6){

                    Intent intentFinance = new Intent(getActivity(), FinanceActivity.class);
                    intentFinance.putExtra("JourneyId", itemId);
                    startActivity(intentFinance);

                }


            }
        });

        return view;
    }

    static ArrayList<JourneysData> generateJourneys(){

        ArrayList<JourneysData> journeysData = new ArrayList<>();

        journeysData.add(new JourneysData("Health",1));
        journeysData.add(new JourneysData("Spiritual",2));
        journeysData.add(new JourneysData("Social",3));
        journeysData.add(new JourneysData("Relationships",4));
        journeysData.add(new JourneysData("Productivity",5));
        journeysData.add(new JourneysData("Finance",6));

        return journeysData;
    }

}
