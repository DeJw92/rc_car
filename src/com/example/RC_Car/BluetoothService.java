package com.example.RC_Car;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Dawid Pawlak.
 */
public class BluetoothService implements Serializable {

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private State state = State.DISCONNECTED;

    private BluetoothAdapter bluetoothAdapter;

    private ConnectThread connectThread;

    private ConnectedThread connectedThread;

    public State getState() {
        return state;
    }

    public BluetoothService() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    }

    public void sendMessage(String message) {
        if(connectedThread != null) {
            connectedThread.write(message.getBytes());
        }
    }


    public synchronized void connect(String address) {
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
        connectThread = new ConnectThread(bluetoothDevice);
        connectThread.start();
    }


    private class ConnectThread extends Thread implements Serializable {
        private final BluetoothSocket socket;

        public ConnectThread(BluetoothDevice bluetoothDevice) {
            BluetoothSocket bluetoothSocket = null;

            try {
                bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = bluetoothSocket;
        }

        public void run() {
            bluetoothAdapter.cancelDiscovery();
            try {
                socket.connect();
            } catch (IOException connectException) {
                try {
                    socket.close();
                } catch (IOException closeException) { }
                return;
            }
            connected(socket);
        }

        private void connected(BluetoothSocket socket) {
            connectedThread = new ConnectedThread(socket);
            connectedThread.start();
            state = com.example.RC_Car.State.CONNECTED;
        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) { }
        }
    }


    private class ConnectedThread extends Thread implements Serializable {
        private final BluetoothSocket bluetoothSocket;

        private final OutputStream outputStream;

        public ConnectedThread(BluetoothSocket socket) {
            bluetoothSocket = socket;
            OutputStream out = null;
            try {
                out = bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream = out;
        }
        public void run() {
            while (true) {

            }
        }

        public void write(byte[] buffer) {
            try {
                outputStream.write(buffer);
            } catch (IOException e) {
            }
        }

    }

}
