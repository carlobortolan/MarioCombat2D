package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Abstract class for cars. Objects for this class cannot be instantiated.
 */
public abstract class Car {

	protected static final int MAX_ANGLE = 360;
	protected static final int HALF_ANGLE = MAX_ANGLE / 2;

	//TODO Change @artemisMode to false to play the game with correct proportions
	// [by changing artemisMode to false the only thing that will change in the implementation are the defaultCarWidth/Height values]

	private static final boolean artemisMode = true;
	protected static final int DEFAULT_CAR_WIDTH = 50;
	protected static final int DEFAULT_CAR_HEIGHT = 25;


	private int minSpeed;
	private int maxSpeed;
	private int speed;
	private boolean crunched;

	private int lives = 3;

	private Point2D position;
	/**
	 * The direction as degree within a circle, a value between 0 (inclusive) and
	 * 360 (exclusive).
	 */
	private int direction;

	private String iconL0;
	private String iconL1;
	private String iconL2;
	private String iconL3;
	private String iconR0;
	private String iconR1;
	private String iconR2;
	private String iconR3;


	private Dimension2D size = new Dimension2D(DEFAULT_CAR_WIDTH, DEFAULT_CAR_HEIGHT);

	public void setSize(int a, int b) {
		this.size = new Dimension2D(a, b);
	}

	/**
	 * Constructor, taking the maximum coordinates of the game board. Each car gets
	 * a random X and Y coordinate, a random direction and a random speed.
	 * <p>
	 * The position of the car cannot be larger then the dimensions of the game
	 * board.
	 *
	 * @param gameBoardSize dimensions of the game board
	 */
	protected Car(Dimension2D gameBoardSize) {
		setRandomPosition(gameBoardSize);
		setRandomDirection();
	}

	/**
	 * Sets the cars position to a random value in between the boundaries of the
	 * game board.
	 *
	 * @param gameBoardSize dimensions of the game board
	 */
	protected void setRandomPosition(Dimension2D gameBoardSize) {
		double carX = calculateRandomDouble(0, gameBoardSize.getWidth() - size.getWidth());
		double carY = calculateRandomDouble(0, gameBoardSize.getHeight() - size.getHeight());
		this.position = new Point2D(carX, carY);
	}

	protected void setRandomDirection() {
		this.direction = calculateRandomInt(0, MAX_ANGLE);
	}

	/**
	 * Sets the speed of the car to a random value based on its minimum and maximum
	 * speed.
	 */
	protected void setRandomSpeed() {
		// We pass this.maxSpeed + 1 to include the value of maxSpeed
		this.speed = calculateRandomInt(this.minSpeed, this.maxSpeed + 1);
	}

	/**
	 * Drives the car and updates its position and possibly its direction.
	 * <p>
	 * The X and Y coordinates of the new position are based on the current
	 * position, direction and speed.
	 *
	 * @param gameBoardSize dimensions of the game board that are the bounds inside
	 *                      which the car is allowed to move.
	 */
	public void drive(Dimension2D gameBoardSize) {
		if (this.crunched) {
			return;
		}
		double maxX = gameBoardSize.getWidth();
		double maxY = gameBoardSize.getHeight();
		// calculate delta between old coordinates and new ones based on speed and
		// direction
		double deltaX = this.speed * Math.sin(Math.toRadians(this.direction));
		double deltaY = this.speed * Math.cos(Math.toRadians(this.direction));
		double newX = this.position.getX() + deltaX;
		double newY = this.position.getY() + deltaY;

		// calculate position in case the boarder of the game board has been reached
		if (newX < 0) {
			newX = -newX;
			this.direction = MAX_ANGLE - this.direction;
		} else if (newX + this.size.getWidth() > maxX) {
			newX = 2 * maxX - newX - 2 * this.size.getWidth();
			this.direction = MAX_ANGLE - this.direction;
		}

		if (newY < 0) {
			newY = -newY;
			this.direction = HALF_ANGLE - this.direction;
			if (this.direction < 0) {
				this.direction = MAX_ANGLE + this.direction;
			}
		} else if (newY + this.size.getHeight() > maxY) {
			newY = 2 * maxY - newY - 2 * this.size.getHeight();
			this.direction = HALF_ANGLE - this.direction;
			if (this.direction < 0) {
				this.direction = MAX_ANGLE + this.direction;
			}
		}
		// set coordinates
		this.position = new Point2D(newX, newY);
	}

	/**
	 * Sets the car's direction.
	 *
	 * @param direction the new direction in degrees (must be between 0 and 360)
	 * @throws IllegalArgumentException if the new direction is lower than 0 or
	 *                                  higher than 360.
	 */
	public void setDirection(int direction) {
		if (direction < 0 || direction >= MAX_ANGLE) {
			throw new IllegalArgumentException("Direction must be between 0 (inclusive) and 360 (exclusive)");
		}
		if(direction > 180) {

		}
		this.direction = direction;
	}

	public int getDirection() {
		return this.direction;
	}

	public int getSpeed() {
		return this.speed;
	}

	/**
	 * Increments the car's speed, won't exceed the maximum speed.
	 */
	public void incrementSpeed() {
		if (this.speed < this.maxSpeed) {
			this.speed++;
		}
	}

	/**
	 * Decrements the car's speed, won't fall below the minimum speed.
	 */
	public void decrementSpeed() {
		if (this.speed > this.minSpeed) {
			this.speed--;
		}
	}

	public String getIconLocation() {
//	if(this.getDirection() > 180) {
//
//		if(this instanceof BowserCar) {
//			return "BowserCarL" + this.lives + ".png";
//
//		} else if(this instanceof FastCar) {
//			return "MarioCarL" + this.lives + ".png";
//
//		} else if (this instanceof DonkeyKongCar) {
//			return "DonkeyKongCarL" + this.lives +".png" + "";
//		}
//	}
//	else {
//		if(this instanceof BowserCar) {
//			return "BowserCarR" + this.lives + ".png";
//
//		} else if(this instanceof FastCar) {
//			return "MarioCarR" + this.lives + ".png";
//
//		} else if (this instanceof DonkeyKongCar) {
//			return "DonkeyKongCarR" + this.lives +".a" + "" + "png" + "\r\n";
//		}
//
//	}
		return "null";}

//	/**
//	 * Sets the image path of the car.
//	 *
//	 * @param iconLocation the path to the image file
//	 * @throws NullPointerException if iconLocation is null
//	 */
//	protected void setIconLocation(String iconLocation) {
//		if (iconLocation == null) {
//			throw new NullPointerException("The chassis image of a car cannot be null.");
//		}
//		this.iconLocation = iconLocation;
//	}
//	protected void setIcon2Location(String icon2Location) {
//		if (icon2Location == null) {
//			throw new NullPointerException("The chassis image of a car cannot be null.");
//		}
//		this.icon2Location = icon2Location;
//	}

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(double x, double y) {
		this.position = new Point2D(x, y);
	}

	public Dimension2D getSize() {
		if(!artemisMode)
		return new Dimension2D(150, 150);
		return this.size;
	}

	public void setSize(Dimension2D size) {
		this.size = size;
	}

	public void crunch() {
		this.crunched = true;
		this.speed = 0;
	}

	public boolean isCrunched() {
		return this.crunched;
	}

	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	public int getMinSpeed() {
		return this.minSpeed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setMinSpeed(int minSpeed) {
		this.minSpeed = minSpeed;
	}

	/**
	 * Calculates a new random int value between minValue (inclusive) and the
	 * provided maxValue (exclusive).
	 *
	 * @param minValue the inclusive lower bound
	 * @param maxValue the exclusive upper bound
	 * @return a random int value
	 */
	protected static int calculateRandomInt(int minValue, int maxValue) {
		return ThreadLocalRandom.current().nextInt(minValue, maxValue);
	}

	/**
	 * Calculates a new random double value between minValue (inclusive) and the
	 * provided maxValue (exclusive).
	 *
	 * @param minValue the inclusive lower bound
	 * @param maxValue the exclusive upper bound
	 * @return a random double value
	 */
	protected static double calculateRandomDouble(double minValue, double maxValue) {
		return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getLives() {
		return this.lives;
	}

	public void decreaseLife() {
		if(this.getLives() != 0) {
			setLives(getLives()-1);
		}
	}

	public void setIconL0(String iconL0) {
		this.iconL0 = iconL0;
	}

	public void setIconL1(String iconL1) {
		this.iconL1 = iconL1;
	}

	public void setIconL2(String iconL2) {
		this.iconL2 = iconL2;
	}

	public void setIconL3(String iconL3) {
		this.iconL3 = iconL3;
	}

	public void setIconR0(String iconR0) {
		this.iconR0 = iconR0;
	}

	public void setIconR1(String iconR1) {
		this.iconR1 = iconR1;
	}

	public void setIconR2(String iconR2) {
		this.iconR2 = iconR2;
	}

	public void setIconR3(String iconR3) {
		this.iconR3 = iconR3;
	}
}
