package com.squigg1ers.showertimer;

import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

public class TimerFragment extends Fragment {
    private View view;

    final static String TIMEWHENSTOPPED = "TIMEWHENSTOPPED";
    final static String TIMERPAUSED = "TIMERPAUSED";
    private Chronometer chrono;
    private Button startButton;
    private Button resetButton;
    private boolean timerPaused;
    private long timeWhenStopped;

    public TimerFragment() {
        timerPaused = true;
        timeWhenStopped = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_timer, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chrono = (Chronometer) view.findViewById(R.id.timer);
        startButton = (Button) view.findViewById(R.id.start);
        resetButton = (Button) view.findViewById(R.id.reset);

        //set current time of chrono
        if(savedInstanceState != null) {
            timeWhenStopped = savedInstanceState.getLong(TIMEWHENSTOPPED);
            timerPaused = savedInstanceState.getBoolean(TIMERPAUSED);

            chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            if(!timerPaused) {
                chrono.start();
                startButton.setText(R.string.pause);
            }
            else if(timeWhenStopped != 0)
                startButton.setText(R.string.resume);
        }

        //set start button functions
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerPaused) {   //resume chrono
                    chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chrono.start();
                    timerPaused = false;
                    startButton.setText(R.string.pause);
                }
                else {              //pause chrono
                    chrono.stop();
                    timerPaused = true;
                    timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
                    startButton.setText(R.string.resume);
                }
            }
        });

        //press reset button to chrono back to 0 and stop it
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setBase(SystemClock.elapsedRealtime());
                timerPaused = true;
                timeWhenStopped = 0;
                startButton.setText(R.string.start);
            }
        });
    }

    //save time of chrono
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(timerPaused)
            outState.putLong(TIMEWHENSTOPPED, timeWhenStopped);
        else
            outState.putLong(TIMEWHENSTOPPED, chrono.getBase() - SystemClock.elapsedRealtime());
        outState.putBoolean(TIMERPAUSED, timerPaused);
    }
}
