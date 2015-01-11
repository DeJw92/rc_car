package com.example.RC_Car;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Dawid Pawlak.
 */
public class Test extends Activity {

    private EditText textArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controll);
        textArea = (EditText) findViewById(R.id.textArea);
    }


    public void sendMessageHandler(View view) {
        String text = textArea.getText().toString();
        BluetoothActivity.bluetoothService.sendMessage(text);
    }
}
