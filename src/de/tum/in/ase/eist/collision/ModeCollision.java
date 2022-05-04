package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;

public class ModeCollision extends Collision {
    public ModeCollision(Car car1, Car car2) {
        super(car1, car2);
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

        if (loserCar.getLifes() > 1) {
            loserCar.decreaseLife();
            loserCar.setPosition(500, 0);
            loserCar.setDirection(180);
            return false;
        } else if (loserCar.getLifes() == 1) {
            loserCar.decreaseLife();
            return true;
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
