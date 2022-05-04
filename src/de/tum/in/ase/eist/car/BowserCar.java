package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class BowserCar extends Car {

    private static final String BOWSER_CAR_IMAGE_FILE = "BowserCar.png";

    private static final int MIN_SPEED_BOWSER_CAR = 2;
    private static final int MAX_SPEED_BOWSER_CAR = 6;

    public  BowserCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_BOWSER_CAR);
        setMaxSpeed(MAX_SPEED_BOWSER_CAR);
        setRandomSpeed();
        setIconLocation(BOWSER_CAR_IMAGE_FILE);
    }
}
