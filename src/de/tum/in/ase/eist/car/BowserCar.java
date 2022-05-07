package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class BowserCar extends Car {


    private static final String BOWSER_CAR_IMAGE_FILER0 = "BowserCarR0.png";
    private static final String BOWSER_CAR_IMAGE_FILER1 = "BowserCarR1.png";
    private static final String BOWSER_CAR_IMAGE_FILER2 = "BowserCarR2.png";
    private static final String BOWSER_CAR_IMAGE_FILER3 = "BowserCarR3.png";
    private static final String BOWSER_CAR_IMAGE_FILEL0 = "BowserCarL0.png";
    private static final String BOWSER_CAR_IMAGE_FILEL1 = "BowserCarL1.png";
    private static final String BOWSER_CAR_IMAGE_FILEL2 = "BowserCarL2.png";
    private static final String BOWSER_CAR_IMAGE_FILEL3 = "BowserCarL3.png";

    private static final int MIN_SPEED_BOWSER_CAR = 2;
    private static final int MAX_SPEED_BOWSER_CAR = 3;

    @Override
    public String getIconLocation() {
        if(this.getDirection() > 180) {
            return "BowserCarL" + this.getLives() + ".png";
        }
        else {
            return "BowserCarR" + this.getLives() + ".png";
        }
    }

    public  BowserCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        this.setSize(150, 150);
        setMinSpeed(MIN_SPEED_BOWSER_CAR);
        setMaxSpeed(MAX_SPEED_BOWSER_CAR);
        setRandomSpeed();
        setIconL0(BOWSER_CAR_IMAGE_FILEL0);
        setIconL1(BOWSER_CAR_IMAGE_FILEL1);
        setIconL2(BOWSER_CAR_IMAGE_FILEL2);
        setIconL3(BOWSER_CAR_IMAGE_FILEL3);
        setIconR0(BOWSER_CAR_IMAGE_FILER0);
        setIconR1(BOWSER_CAR_IMAGE_FILER1);
        setIconR2(BOWSER_CAR_IMAGE_FILER2);
        setIconR3(BOWSER_CAR_IMAGE_FILER3);
    }
}
