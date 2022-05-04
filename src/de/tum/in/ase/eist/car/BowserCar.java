package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class BowserCar extends Car {

    private static final String BOWSER_Car_IMAGE_FILE = "BowserCar.png";

    private static final int MIN_SPEED_BOWSER_Car = 2;
    private static final int MAX_SPEED_BOWSER_Car = 6;

    public  BowserCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_BOWSER_Car);
        setMaxSpeed(MAX_SPEED_BOWSER_Car);
        setRandomSpeed();
        setIconLocation(BOWSER_Car_IMAGE_FILE);
    }
}
