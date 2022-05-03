package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class MarioCar extends Car {

    private static final String MARIO_CAR_IMAGE_FILE = "MarioCar.gif";
    private int extraLifes = 1;
    private static final int MIN_SPEED_MARIO_CAR = 2;
    private static final int MAX_SPEED_MARIO_CAR = 12;

    public MarioCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_MARIO_CAR);
        setMaxSpeed(MAX_SPEED_MARIO_CAR);
        setRandomSpeed();
        setIconLocation(MARIO_CAR_IMAGE_FILE);
    }
    public int getExtraLifes(){
        return this.extraLifes;
    }
    public void decrease() {
        this.extraLifes--;
    }
}
