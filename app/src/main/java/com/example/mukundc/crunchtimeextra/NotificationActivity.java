package com.example.mukundc.crunchtimeextra;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> messages = new ArrayList<String>();
        messages.add("Fear is what stops you... courage is what keeps you going!");
        messages.add("Clear your mind of can’t!");
        messages.add("Strength does not come from physical capacity. It comes from an indomitable will. \n" +
                "-Mahatma Gandhi");
        messages.add("It never gets easier. You just get stronger!");
        messages.add("You don't have to be great to start, but you have to start to be great!");
        messages.add("Good is not enough if better is possible!");
        messages.add("No pain, no gain!");
        messages.add("When the going gets tough the tough get going!");
        messages.add("Sweat. Smile. Repeat.");
        messages.add("Making excuses burns 0 calories per hour!");
        messages.add("Sweat is just fat crying!");
        messages.add("Just keep running, biking, swimming, lifting...you'll get there!");
        messages.add("Running is nothing more than a series of arguments between the part of your brain that wants to stop and the part that wants to keep going!");
        messages.add("The key is to make it!");
        messages.add("There will be roadblocks. But we will overcome it!");
        messages.add("They don't want you to exercise. So we're gonna exercise");
        Random generator = new Random();
        int i = generator.nextInt(messages.size());
        TextView message = (TextView) findViewById(R.id.motivationalText);
        message.setText(messages.get(i));
    }

}
