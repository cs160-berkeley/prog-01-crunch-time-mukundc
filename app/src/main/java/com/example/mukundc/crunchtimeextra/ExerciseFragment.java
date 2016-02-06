package com.example.mukundc.crunchtimeextra;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
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
public class ExerciseFragment extends Fragment {
    HashMap<String, Integer> nameToReps = new HashMap<>();
    HashMap<String, Integer> nameToMinutes = new HashMap<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.exercise_fragment, container, false);

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

        Spinner spinner = (Spinner) getView().findViewById(R.id.exercise_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.exercises_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String type = "reps";
            TextView exerciseUnit = (TextView) getView().findViewById((R.id.exerciseUnit));
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String item = (String) parent.getItemAtPosition(pos);
                if (nameToReps.containsKey(item)) {
                    type = "reps";
                }
                else {
                    type = "minutes";
                }
                exerciseUnit.setText(type);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                type = "reps";
            }

        });

        final EditText exerciseQuantity = (EditText) getView().findViewById(R.id.exerciseQuantity);

        Button convert = (Button) getView().findViewById(R.id.button);
        convert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String quantityText = exerciseQuantity.getText().toString();
                if (quantityText == null || quantityText.equals("")) {
                    Toast t = Toast.makeText(getActivity().getApplicationContext(), "Please enter a number of reps or minutes", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                TextView text = (TextView) getView().findViewById(R.id.textViewCategoriesTitle);
                text.setText("Equivalent Exercises");
                text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                text.setVisibility(View.VISIBLE);

                TextView myOutputBox = (TextView) getView().findViewById(R.id.textView);
                Spinner spinner = (Spinner) getView().findViewById(R.id.exercise_spinner);
                String exerciseChosen = spinner.getSelectedItem().toString();

                int temp = Integer.valueOf(exerciseQuantity.getText().toString());
                double calories = 0;

                if (nameToMinutes.containsKey((exerciseChosen))) {
                    calories = temp / (double) nameToMinutes.get(exerciseChosen) * 100;
                } else {
                    calories = temp / (double) nameToReps.get(exerciseChosen) * 100;

                }
                myOutputBox.setText(String.format("%.2f", calories) + " Calories");


                ArrayList<String> listItems = new ArrayList<String>();
                for (String key : nameToReps.keySet()) {
                    if (!key.equals(exerciseChosen)) {
                        double reps = calories * (double) nameToReps.get(key) / 100;
                        listItems.add(key + ": " + String.format("%.2f", reps) + " Reps");
                    }

                }
                for (String key : nameToMinutes.keySet()) {
                    if (!key.equals(exerciseChosen)) {
                        double minutes = calories * (double) nameToMinutes.get(key) / 100;
                        listItems.add(key + ": " + String.format("%.2f", minutes) + " Minutes");
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, listItems.toArray(new String[listItems.size()]));
                    ListView listView = (ListView) getView().findViewById(R.id.ListView);
                    listView.setAdapter(adapter);
                }
            }

        });
    }
}

