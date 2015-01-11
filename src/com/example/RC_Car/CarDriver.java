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
        while(true) {
            if(!paused) {
                service.sendMessage(prepareMessage());

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
