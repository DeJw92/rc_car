package com.example.RC_Car;

import android.widget.ProgressBar;
import android.widget.SeekBar;

/**
 * Created by mikolaj on 05.01.15.
 */
public class CarHandler implements ICarHandler{
    private ProgressBar directionSeekBar;
    private ProgressBar speedSeekBar;

    public CarHandler(ProgressBar dirSeekBar, ProgressBar speedSeekBar) {
        this.directionSeekBar = dirSeekBar;
        this.speedSeekBar = speedSeekBar;
    }

    private double percentageExporter(ProgressBar progressBar) {
        int progress = progressBar.getProgress();
        int max = progressBar.getMax();
        double half = max / 2.0;
        return ((progress - half) / max) * 100;
    }

    /**
     * @return -100 = full speed backwards, 0 = stop, 100 = full speed forward
     */
    @Override
    public double getSpeed() {
        return percentageExporter(speedSeekBar);
    }

    /**
     * @return -100 = maximum left, 0 = straight, 100 = maximum right
     */
    @Override
    public double getDirection() {
        return percentageExporter(directionSeekBar);
    }
}
