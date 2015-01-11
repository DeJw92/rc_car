package com.example.RC_Car;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * @author Mikołaj
 */
public class CarControlActivity extends Activity {
    private IMessageService messageService;
    private CarDriver carDriver;
    private int messagesPerSecond = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.car_controls);
        ProgressBar directionBar = (ProgressBar) findViewById(R.id.directionBar);
        ProgressBar velocityBar = (ProgressBar) findViewById(R.id.velocityBar);

        IControlsHandler controlsHandler = new ControlsHandler(directionBar, velocityBar);
        messageService = new BluetoothService();
        carDriver = new CarDriver(messageService, controlsHandler, messagesPerSecond);
        new Thread(carDriver).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        carDriver.setPaused(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        carDriver.setPaused(true);
    }
}