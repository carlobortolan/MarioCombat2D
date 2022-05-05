package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class DonkeyKongCar extends Car {

    private static final String DONKEYKONG_CAR_IMAGE_FILER0 = "DonkeyKongCarR0.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILER1 = "DonkeyKongCarR1.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILER2 = "DonkeyKongCarR2.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILER3 = "DonkeyKongCarR3.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILEL0 = "DonkeyKongCarL0.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILEL1 = "DonkeyKongCarL1.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILEL2 = "DonkeyKongCarL2.png";
    private static final String DONKEYKONG_CAR_IMAGE_FILEL3 = "DonkeyKongCarL3.png";

    private static final int MIN_SPEED_DONKEYKONG_CAR = 4;
    private static final int MAX_SPEED_DONKEYKONG_CAR = 8;

    @Override
    public String getIconLocation() {
        if(this.getDirection() > 180) {
            return "DonkeyKongCarL" + this.getLifes() + ".png";
        }
        else {
            return "DonkeyKongCarR" + this.getLifes() + ".png";
        }
    }

    public  DonkeyKongCar(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setMinSpeed(MIN_SPEED_DONKEYKONG_CAR);
        setMaxSpeed(MAX_SPEED_DONKEYKONG_CAR);
        setRandomSpeed();
        setIconL0(DONKEYKONG_CAR_IMAGE_FILEL0);
        setIconL1(DONKEYKONG_CAR_IMAGE_FILEL1);
        setIconL2(DONKEYKONG_CAR_IMAGE_FILEL2);
        setIconL3(DONKEYKONG_CAR_IMAGE_FILEL3);
        setIconR0(DONKEYKONG_CAR_IMAGE_FILER0);
        setIconR1(DONKEYKONG_CAR_IMAGE_FILER1);
        setIconR2(DONKEYKONG_CAR_IMAGE_FILER2);
        setIconR3(DONKEYKONG_CAR_IMAGE_FILER3);
    }
}
