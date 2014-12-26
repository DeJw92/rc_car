package com.example.RC_Car;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;

    private Button connectButton;
    private Button disconnectButton;
    private Button showPairedDevicesButton;
    private Button searchNewDevicesButton;
    private TextView textView;

    private BluetoothAdapter bluetoothAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUp();
    }

    private void setUp() {
        initializeComponents();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null) {
            textView.setText(R.string.bluetooth_connection_error);
            finish();
        }
        setBluetoothStatus();
    }

    private void setBluetoothStatus() {
        if(bluetoothAdapter.isEnabled()) {
            textView.setText(R.string.bluetooth_connection_on);
            connectButton.setVisibility(View.GONE);
            disconnectButton.setVisibility(View.VISIBLE);
            showPairedDevicesButton.setVisibility(View.VISIBLE);
            searchNewDevicesButton.setVisibility(View.VISIBLE);
        } else {
            textView.setText(R.string.bluetooth_connection_off);
            disconnectButton.setVisibility(View.GONE);
            showPairedDevicesButton.setVisibility(View.GONE);
            searchNewDevicesButton.setVisibility(View.GONE);
            connectButton.setVisibility(View.VISIBLE);
        }
    }

    private void initializeComponents() {
        connectButton = (Button) findViewById(R.id.connectButton);
        disconnectButton = (Button) findViewById(R.id.disconnectButton);
        textView = (TextView) findViewById(R.id.bluetooth_status);
        showPairedDevicesButton = (Button) findViewById(R.id.showPairedDevicesButton);
        searchNewDevicesButton = (Button) findViewById(R.id.searchNewDevicesButton);
    }

    public void connectButtonHandler(View view) {
        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setBluetoothStatus();
    }

    public void disconnectButtonHandler(View view) {
        bluetoothAdapter.disable();
        setBluetoothStatus();
    }

    public void showPairedDevicesButtonHandler(View view) {
    }

    public void searchNewDevicesButtonHandler(View view) {
    }
}
