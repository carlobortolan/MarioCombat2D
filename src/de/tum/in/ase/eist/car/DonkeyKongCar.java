package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class DonkeyKongCar extends Car {

    private static final String DonkeyKong_Car_IMAGE_FILE = "DonkeyKongCar.png";

    private static final int MIN_SPEED_DonkeyKong_Car = 2;
    private static final int MAX_SPEED_DonkeyKong_Car = 6;

    public  DonkeyKongCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_DonkeyKong_Car);
        setMaxSpeed(MAX_SPEED_DonkeyKong_Car);
        setRandomSpeed();
        setIconLocation(DonkeyKong_Car_IMAGE_FILE);
    }
}
