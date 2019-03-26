package com.example.louis.plateautournant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.louis.plateautournant.Bluetooth.Peripherique;
import com.example.louis.plateautournant.Fragment.ProgrammedRotation;
import com.example.louis.plateautournant.Fragment.RealTimeRotation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String data ="";

    private Spinner spinnerMode;

    private EditText accelerationNumber;
    private EditText speedNumber;
    private EditText stepsmotorNumber;
    private EditText stepstableNumber;

    private SeekBar accelerationSeekBar;
    private SeekBar speedSeekBar;

    private Switch directionSwitch;

    private Button sendButton;

    private ArrayList<String> spinnerModeItems = new ArrayList<String>();

    private RealTimeRotation realTimeRotation = new RealTimeRotation();
    private ProgrammedRotation programmedRotation = new ProgrammedRotation();

    private Peripherique peripherique;

    private int mode;
    private int direction;
    private int acceleration;
    private int speed;
    private int rotation_number;
    private int rotation_time;
    private int frame;
    private int camera_number;
    private int pause_between_camera;
    private int steps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peripherique = Peripherique.peripherique;

        spinnerMode = findViewById(R.id.spinnerMode);

        accelerationNumber = findViewById(R.id.numberAcceleration);
        accelerationSeekBar = findViewById(R.id.seekbarAcceleration);

        speedNumber = findViewById(R.id.numberSpeed);
        speedSeekBar = findViewById(R.id.seekbarSpeed);




        directionSwitch = findViewById(R.id.directionSwitch);

        sendButton = findViewById(R.id.buttonSend);


        accelerationNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int position=accelerationNumber.length();
                    accelerationNumber.setSelection(position);
                    accelerationSeekBar.setProgress(Integer.parseInt(s.toString()));
                } catch(Exception ex) {}

            }
        });
        accelerationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                accelerationNumber.setText(Integer.toString(accelerationSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        accelerationNumber.clearFocus();

        speedNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    int position=speedNumber.length();
                    speedNumber.setSelection(position);
                    speedSeekBar.setProgress(Integer.parseInt(s.toString()));
                } catch(Exception ex) {}

            }
        });
        speedSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedNumber.setText(Integer.toString(speedSeekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        speedNumber.clearFocus();

        spinnerModeItems.add("Mode Programmé");
        spinnerModeItems.add("Mode Temps Réel");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_spinner, spinnerModeItems);
        adapter.setDropDownViewResource(R.layout.custom_spinner);
        spinnerMode.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //realTimeRotation = new RealTimeRotation();
                        mode=0;
                        getFragmentManager().beginTransaction().replace(R.id.fragment, programmedRotation).commit();
                        break;
                    case 1:
                        mode=1;
                        getFragmentManager().beginTransaction().replace(R.id.fragment, realTimeRotation).commit();
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                direction=1;
                acceleration = Integer.parseInt(accelerationNumber.getText().toString());
                speed = Integer.parseInt(speedNumber.getText().toString());
                steps=3600;

                switch(mode){
                    case 1:
                        if (realTimeRotation.isModeTime()){
                            rotation_number=-1;
                            rotation_time=Integer.parseInt(realTimeRotation.getNumberText().getText().toString());
                        }else{
                            rotation_number=Integer.parseInt(realTimeRotation.getNumberText().getText().toString());
                            rotation_time=-1;
                        }
                        frame=-1;
                        camera_number=-1;
                        pause_between_camera=-1;
                        break;
                    case 0:
                        rotation_number = -1;
                        rotation_time = -1;
                        frame = Integer.parseInt(programmedRotation.getFrameNumber().getText().toString());
                        camera_number = Integer.parseInt(programmedRotation.getCameraNumber().getText().toString());
                        pause_between_camera = Integer.parseInt(programmedRotation.getTimeBetweenPhotosNumber().getText().toString());
                        break;
                }
                
                data="";
                data +=Integer.toString(mode)+",";
                data +=Integer.toString(acceleration)+",";
                data +=Integer.toString(speed)+",";
                data +=Integer.toString(direction)+",";
                data +=Integer.toString(rotation_number)+",";
                data +=Integer.toString(rotation_time)+",";
                data +=Integer.toString(frame)+",";
                data +=Integer.toString(camera_number)+",";
                data +=Integer.toString(pause_between_camera)+",";
                data +=Integer.toString(steps);

                peripherique.envoyer(data);
            }
        });
    }
}
