package com.example.RC_Car;

import android.widget.ProgressBar;

/**
 * Created by mikolaj on 05.01.15.
 */
public class ControlsHandler implements IControlsHandler {
    private ProgressBar directionSeekBar;
    private ProgressBar speedSeekBar;

    public ControlsHandler(ProgressBar dirSeekBar, ProgressBar speedSeekBar) {
        this.directionSeekBar = dirSeekBar;
        this.speedSeekBar = speedSeekBar;
    }

    /**
     * @return -50 = full speed backwards, 0 = stop, 50 = full speed forward
     */
    @Override
    public int getSpeed() {
        return speedSeekBar.getProgress();
    }

    /**
     * @return -50 = maximum left, 0 = straight, 50 = maximum right
     */
    @Override
    public int getDirection() {
        return directionSeekBar.getProgress();
    }

}
