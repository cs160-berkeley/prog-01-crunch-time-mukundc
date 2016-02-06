package com.example.mukundc.crunchtimeextra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mukundc on 2/1/16.
 */
public class NotificationFragment extends Fragment {
    NotificationCompat.Builder mBuilder;
    int mNotificationId;
    // Gets an instance of the NotificationManager service
    NotificationManager mNotifyMgr;
    String duration;
    private Button buttonStartTime, buttonStopTime;
    private EditText editTimer;
    private TextView timerTextView; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.notification_fragment, container, false);

        return rootView;
    }


    private void setTimer() {
        int time = 0;
        if (!editTimer.getText().toString().equals("")) {
            time = Integer.parseInt(editTimer.getText().toString());
        } else
            Toast.makeText(getActivity(), "Please Enter Minutes...",
                    Toast.LENGTH_LONG).show();

        totalTimeCountInMilliseconds = 60 * time * 1000;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                timerTextView.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format
                int notificationDuration = Integer.parseInt(duration);
                if (notificationDuration != 0) {
                    if (notificationDuration == 15 && seconds%15 == 0) {
                        mBuilder.setContentTitle("Finished another 15 seconds!");
                        mBuilder.setContentText("Tap for a motivatonal message!");
                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
                    }
                    else if (seconds%(notificationDuration*60) == 0) {
                        // Builds the notification and issues it.
                        mBuilder.setContentTitle("Finished another  " + duration + " minutes!");
                        mBuilder.setContentText("Tap for a motivatonal message!");

                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
                    }
                }


            }

            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished
                timerTextView.setText("Time up!");
                timerTextView.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.GONE);
                editTimer.setVisibility(View.VISIBLE);
            }

        }.start();

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buttonStartTime = (Button) getView().findViewById(R.id.startTime);
        buttonStopTime = (Button) getView().findViewById(R.id.stopTime);
        timerTextView = (TextView) getView().findViewById(R.id.timer);
        editTimer = (EditText) getView().findViewById(R.id.editTime);

        mBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("")
                .setContentText("");

        Intent resultIntent = new Intent(getActivity(), NotificationActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                getActivity(),
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );


        mBuilder.setContentIntent(resultPendingIntent);

        //mBuilder.setContentIntent(PendingIntent.getActivity(getActivity().getApplicationContext(), 0, new Intent(), 0));
        mNotificationId = 001;
        // Gets an instance of the NotificationManager service
        mNotifyMgr = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);


        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click);
                //timerTextView.setTextAppearance(getActivity().getApplicationContext(), R.style.TextAppearance_AppCompat_Medium);
                setTimer();
                buttonStopTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.GONE);
                editTimer.setVisibility(View.GONE);
                editTimer.setText("");
                startTimer();
            }
        });

        buttonStopTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click);
                countDownTimer.cancel();
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.GONE);
                editTimer.setVisibility(View.VISIBLE);
            }
        });


        Spinner spinner = (Spinner) getView().findViewById(R.id.notification_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.notifications_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                duration = ((String) parent.getItemAtPosition(pos)).split(" ")[0];
            }

            public void onNothingSelected(AdapterView<?> parent) {
                duration = "0";
            }

        });

    }

}

