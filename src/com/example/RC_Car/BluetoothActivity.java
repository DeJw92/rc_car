package com.example.RC_Car;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Dawid Pawlak.
 */
public class BluetoothActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;

    private static final int REQUEST_CHOOSE_DEVICE = 2;

    private Button connectButton;

    private TextView bluetoothStatusView;

    private BluetoothAdapter bluetoothAdapter;

    public static BluetoothService bluetoothService; // TODO : it shouldn't be public static

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUp();
    }

    private void setUp() {
        initializeComponents();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        setBluetoothStatus();
        bluetoothService = new BluetoothService();
    }

    private void initializeComponents() {
        connectButton = (Button) findViewById(R.id.connectBluetoothButton);
        bluetoothStatusView = (TextView) findViewById(R.id.bluetoothStatusView);
    }

    public void connectBluetoothHandler(View view) {
        if(bluetoothAdapter == null) {
            bluetoothStatusView.setText(R.string.bluetoothDeviceNotFound);
            finish();
        }
        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ENABLE_BT) {
            setBluetoothStatus();
        } else if(requestCode == REQUEST_CHOOSE_DEVICE) {
            if(resultCode == Activity.RESULT_OK) {
                String address = data.getExtras().getString(AvailableDevicesListActivity.BLUETOOTH_ADDRESS);
                bluetoothService.connect(address);
                while(bluetoothService.getState() != State.CONNECTED) {

                }
                startRideCarActivity();

            }
        }
    }


    private void setBluetoothStatus() {
        if(bluetoothAdapter.isEnabled()) {
            bluetoothStatusView.setText(R.string.bluetoothEnabled);
            connectButton.setVisibility(View.GONE);
        } else {
            bluetoothStatusView.setText(R.string.bluetoothDisabled);
        }
    }

    public void findDevicesHandler(View view) {
        if(!bluetoothAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), R.string.bluetoothShouldBeEnabled, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, AvailableDevicesListActivity.class);
            startActivityForResult(intent, REQUEST_CHOOSE_DEVICE);
        }
    }


    public void startRideCarActivity() {
        Intent intent = new Intent(this, CarControlActivity.class);
        startActivity(intent);
    }
}
