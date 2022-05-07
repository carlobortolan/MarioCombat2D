package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.BowserCar;
import de.tum.in.ase.eist.car.Car;

public class ModeCollision extends Collision {
    private static int i = 1;

//    private AudioPlayer audioPlayer;   ;
    public ModeCollision(Car car1, Car car2) {
        super(car1, car2);
//        if(audioPlayer == null) {
//            this.audioPlayer = new AudioPlayer();
//        } else {
//        this.audioPlayer = audioPlayer;
//    }
}

    public boolean detectCollision() {
        Point2D p1 = getCar1().getPosition();
        Dimension2D d1 = getCar1().getSize();

        Point2D p2 = getCar2().getPosition();
        Dimension2D d2 = getCar2().getSize();

        boolean above = p1.getY() + d1.getHeight() < p2.getY();
        boolean below = p1.getY() > p2.getY() + d2.getHeight();
        boolean right = p1.getX() + d1.getWidth() < p2.getX();
        boolean left = p1.getX() > p2.getX() + d2.getWidth();


    if(!above && !below && !right && !left) {

        Car winnerCar = null;
        Car loserCar = null;
        if (p1.getX() > p2.getX()) {
            winnerCar = this.getCar1();
            loserCar = this.getCar2();
        } else {
            winnerCar = this.getCar2();
            loserCar = this.getCar1();
        }

        if (loserCar.getLives() > 1) {

            if(winnerCar instanceof BowserCar) {


                if(loserCar.getLives() == 2) {
                    loserCar.decreaseLife();
                    loserCar.decreaseLife();
                    return true;
                }

                loserCar.decreaseLife();

            }

            loserCar.decreaseLife();
            loserCar.setPosition((i%3)*500+200, 0);
            ++i;
            loserCar.setDirection(200);
//            AudioPlayer a = new AudioPlayer();
//            a.playCrashSound();

            return false;
        } else if (loserCar.getLives() == 1) {
            loserCar.decreaseLife();

//            AudioPlayer a = new AudioPlayer();
//            a.playKOSound();return  true;
        } else {
            return false;
        }
    }    return !above && !below && !right && !left;
    }

    public Car evaluate() {

        // TODO Backlog Item 11: Collisions follow the "right before left" rule, and thus right-most
        // cars on the screen win the collisions

        Point2D p1 = getCar1().getPosition();
        Point2D p2 = getCar2().getPosition();

        Car winnerCar = null;
        if (p1.getX() > p2.getX()) {
            winnerCar = this.getCar1();
        } else {
            winnerCar = this.getCar2();
        }
        return winnerCar;
    }
}
