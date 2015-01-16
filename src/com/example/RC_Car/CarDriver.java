package com.example.RC_Car;

/**
 * Created by mikolaj on 11.01.15.
 */
public class CarDriver implements Runnable {

    private final IMessageService service;
    private final IControlsHandler controlsHandler;
    private final int sleepTime;
    private final int standBySleepTime;
    private boolean paused;

    public CarDriver(IMessageService service, IControlsHandler controlsHandler, int messagesPerSecond) {
        this.paused = true;
        this.service = service;
        this.controlsHandler = controlsHandler;
        this.sleepTime = 1000 / messagesPerSecond;
        this.standBySleepTime = 500;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void run() {
        byte lastSpeed = 0;
        byte lastDirection = 0;
        while(true) {
            if(!paused) {

                if(((byte) controlsHandler.getSpeed()) != lastSpeed || ((byte) controlsHandler.getDirection()) != lastDirection) {
                    lastSpeed = (byte)controlsHandler.getSpeed();
                    lastDirection = (byte) controlsHandler.getDirection();
                    System.out.println("Speed: "+lastSpeed);
                    System.out.println("Direction: "+lastDirection);
                    service.sendMessage(new byte[] {lastSpeed, lastDirection});
                }

            }
            else {

                try {
                    Thread.sleep(standBySleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted during standby phase");
                    e.printStackTrace();
                }
            }
        }
    }

    private String prepareMessage() {
        return String.valueOf(controlsHandler.getSpeed())
                + String.valueOf(controlsHandler.getDirection());
    }

}
