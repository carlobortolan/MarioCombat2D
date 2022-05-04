package de.tum.in.ase.eist.usercontrol;

import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.KeyCode;

/**
 * This class is responsible for the handling the MOUSE_PRESSED Event, i.e. the
 * steering of the user's car.
 */
public class MouseSteering {

	private static final int ANGLE_90_DEGREES = 90;
	private static final int ANGLE_270_DEGREES = 270;

	private final Car userCar;

	/**
	 * Creates a MouseSteering instance for a specific GameBoardUI and a car that
	 * the user needs to steer with their mouse.
	 *
	 * @param userCar     the car that should be steered by the user
	 */
	public MouseSteering(Car userCar) {
		this.userCar = userCar;
	}

	public void scrollPressed(ScrollEvent scrollEvent) {
		if (scrollEvent.getDeltaY()/10 > 0 && this.userCar.getSpeed() < this.userCar.getMaxSpeed()) {
			this.userCar.setSpeed(userCar.getSpeed()+1);
		} else if (this.userCar.getSpeed() > this.userCar.getMinSpeed()){
			this.userCar.setSpeed(userCar.getSpeed()-1);
		}
	}

//	public void keyPressed(KeyEvent e) {
//		System.out.println("e = " + e);
//		if (e.getCode() == KeyCode.ALL_CANDIDATES) {
//			System.out.println("Right key pressed");
//		}
//		if (e.getCode() == KeyCode.ALL_CANDIDATES) {
//			System.out.println("Left key pressed");
//		}
//	}

	public void mousePressed(MouseEvent clickEvent) {
		Point2D carPosition = userCar.getPosition();
		Point2D clickPosition = new Point2D(clickEvent.getX(), clickEvent.getY());
		double deltaX = clickPosition.getX() - carPosition.getX();
		deltaX = Math.abs(deltaX);
		double deltaY = clickPosition.getY() - carPosition.getY();
		int degree = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

		if (clickPosition.getX() > carPosition.getX()) {
			degree = ANGLE_90_DEGREES - degree;
		} else {
			degree = ANGLE_270_DEGREES + degree;
		}
		userCar.setDirection(degree);
	}
}
