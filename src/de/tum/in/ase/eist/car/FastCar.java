package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class FastCar extends Car {

	private static final String FAST_CAR_IMAGE_FILE = "KirbyCar.png";
	private static final String FAST_CAR_IMAGE_FILE2 = "KirbyCar2.png";

	private static final int MIN_SPEED_FAST_CAR = 2;
	private static final int MAX_SPEED_FAST_CAR = 10;

	public FastCar(Dimension2D gameBoardSize) {
		super(gameBoardSize);
		setMinSpeed(MIN_SPEED_FAST_CAR);
		setMaxSpeed(MAX_SPEED_FAST_CAR);
		setRandomSpeed();
		setIconLocation(FAST_CAR_IMAGE_FILE);
		setIcon2Location(FAST_CAR_IMAGE_FILE2);
	}
}
