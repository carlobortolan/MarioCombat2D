package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class FastCar extends Car {

	private static final String MARIO_CAR_IMAGE_FILER0 = "MarioCarR0.png";
	private static final String MARIO_CAR_IMAGE_FILER1 = "MarioCarR1.png";
	private static final String MARIO_CAR_IMAGE_FILER2 = "MarioCarR2.png";
	private static final String MARIO_CAR_IMAGE_FILER3 = "MarioCarR3.png";
	private static final String MARIO_CAR_IMAGE_FILEL0 = "MarioCarL0.png";
	private static final String MARIO_CAR_IMAGE_FILEL1 = "MarioCarL1.png";
	private static final String MARIO_CAR_IMAGE_FILEL2 = "MarioCarL2.png";
	private static final String MARIO_CAR_IMAGE_FILEL3 = "MarioCarL3.png";

	private static final int MIN_SPEED_Mario_CAR = 2;
	private static final int MAX_SPEED_Mario_CAR = 6;

	@Override
	public String getIconLocation() {
		if(this.getDirection() > 180) {
			return "MarioCarL" + this.getLifes() + ".png";
		}
		else {
			return "MarioCarR" + this.getLifes() + ".png";
		}
	}

	public  FastCar(Dimension2D gameBoardSize) {
		super(gameBoardSize);
		setMinSpeed(MIN_SPEED_Mario_CAR);
		setMaxSpeed(MAX_SPEED_Mario_CAR);
		setRandomSpeed();
		setIconL0(MARIO_CAR_IMAGE_FILEL0);
		setIconL1(MARIO_CAR_IMAGE_FILEL1);
		setIconL2(MARIO_CAR_IMAGE_FILEL2);
		setIconL3(MARIO_CAR_IMAGE_FILEL3);
		setIconR0(MARIO_CAR_IMAGE_FILER0);
		setIconR1(MARIO_CAR_IMAGE_FILER1);
		setIconR2(MARIO_CAR_IMAGE_FILER2);
		setIconR3(MARIO_CAR_IMAGE_FILER3);
	}
}
