package com.example.mukundc.crunchtimeextra;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by mukundc on 2/1/16.
 */
public class CaloriesFragment extends Fragment {
    HashMap<String, Integer> nameToReps = new HashMap<>();
    HashMap<String, Integer> nameToMinutes = new HashMap<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.calories_fragment, container, false);

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameToReps.put("Pushup", 350);
        nameToReps.put("Situp", 200);
        nameToReps.put("Squats", 225);
        nameToMinutes.put("Leg-lift", 25);
        nameToMinutes.put("Plank", 25);
        nameToMinutes.put("Jumping Jacks", 10);
        nameToReps.put("Pullup", 100);
        nameToMinutes.put("Cycling", 12);
        nameToMinutes.put("Walking", 20);
        nameToMinutes.put("Jogging", 12);
        nameToMinutes.put("Swimming", 13);
        nameToMinutes.put("Stair-Climbing", 15);

        String[] exercises = {"Pushup", "Situp", "Squats", "Leg-lift", "Plank", "Jumping Jacks", "Pullup", "Cycling", "Walking", "Jogging", "Swimming", "Stair-Climbing"};


        final EditText caloriesQuantity = (EditText) getView().findViewById(R.id.caloriesQuantity);

        Button convert1 = (Button) getView().findViewById(R.id.buttonCaloriesFragment);
        convert1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String quantityText = caloriesQuantity.getText().toString();
                if (quantityText == null || quantityText.equals("")) {
                    Toast t = Toast.makeText(getActivity().getApplicationContext(), "Please enter a number of calories", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                TextView text = (TextView) getView().findViewById(R.id.textViewCategoriesTitle2);
                text.setText("Equivalent Exercises");
                text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                text.setVisibility(View.VISIBLE);
                double calories = Integer.valueOf(caloriesQuantity.getText().toString());
                ArrayList<String> listItems = new ArrayList<String>();
                for (String key : nameToReps.keySet()) {
                    double reps = calories * (double) nameToReps.get(key) / 100;
                    listItems.add(key + ": " + String.format("%.2f", reps) + " Reps");
                }
                for (String key : nameToMinutes.keySet()) {
                        double minutes = calories * (double) nameToMinutes.get(key) / 100;
                        listItems.add(key + ": " + String.format("%.2f", minutes) + " Minutes");

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listItems.toArray(new String[listItems.size()]));
                ListView listView = (ListView) getView().findViewById(R.id.ListViewCaloriesFragment);
                listView.setAdapter(adapter);
            }
        });
    }
}

