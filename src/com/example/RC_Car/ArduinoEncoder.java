package com.example.RC_Car;

/**
 * Created by Dawid Pawlak.
 */
public class ArduinoEncoder {
    private int encodeStart;
    private int encodeEnd;

    // -100 = 0
    // -50 = 64
    // 0 = 128
    // 50 = 192
    // 100 = 255
    public ArduinoEncoder(int encodeStart, int encodeEnd) {

        this.encodeStart = encodeStart;
        this.encodeEnd = encodeEnd;
    }

    public byte encode(double value) {
        if(value < 0) {
            return (byte) ((encodeEnd - encodeStart)/2 * (-value));
        }
        return (byte) ((encodeEnd - encodeStart)* value);
    }
}
