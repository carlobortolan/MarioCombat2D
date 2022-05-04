package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class MarioCar extends Car {

    private static final String MARIO_CAR_IMAGE_FILE = "MarioCar.png";
    private static final String MARIO_CAR_IMAGE_FILE2 = "MarioCar2.png";
    private static final int MIN_SPEED_MARIO_CAR = 2;
    //private static final int maxCollisionsBeforeCooldown = 5;
    private static final int MAX_SPEED_MARIO_CAR = 10;
//    private int cooldown = maxCollisionsBeforeCooldown;

    public MarioCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_MARIO_CAR);
        setMaxSpeed(MAX_SPEED_MARIO_CAR);
        setRandomSpeed();
        setIconLocation(MARIO_CAR_IMAGE_FILE);
        setIcon2Location(MARIO_CAR_IMAGE_FILE2);
    }

    /*@Override
    public void crunch() {
            if(this.getCooldown() != 0) {
                if (this.getCooldown() == 1) {
                    this.decrease();
                    this.recharge();
                } else {
                    this.decrease();
                }
            }
    }

    public int getCooldown(){
        return this.cooldown;
    }
    public void decrease() {
        this.cooldown--;
    }
    public void recharge() {
        //time
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

       *//* while (elapsedTime < 5000) {
            elapsedTime = (new Date()).getTime() - startTime;
        }*//*
        this.cooldown = this.maxCollisionsBeforeCooldown;
    }*/
}
