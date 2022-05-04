package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class DonkeyKongCar extends Car {

    private static final String DONKEYKONG_CAR_IMAGE_FILE = "DonkeyKongCar.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILE2 = "DonkeyKongCar2.png";

    private static final int MIN_SPEED_DONKEYKONG_CAR = 2;
    private static final int MAX_SPEED_DONKEKONG_CAR = 6;

    public  DonkeyKongCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_DONKEYKONG_CAR);
        setMaxSpeed(MAX_SPEED_DONKEKONG_CAR);
        setRandomSpeed();
        setIconLocation(DONKEYKONG_CAR_IMAGE_FILE);
        setIcon2Location(DONKEYKONG_CAR_IMAGE_FILE2);
    }
}
