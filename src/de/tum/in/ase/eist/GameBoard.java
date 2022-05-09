package de.tum.in.ase.eist;

import de.tum.in.ase.eist.audio.AudioPlayerInterface;
import de.tum.in.ase.eist.car.BowserCar;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.car.DonkeyKongCar;
import de.tum.in.ase.eist.car.FastCar;
import de.tum.in.ase.eist.collision.Collision;
import de.tum.in.ase.eist.collision.ModeCollision;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates all car objects, detects collisions, updates car positions, notifies
 * player about victory or defeat.
 */
public class GameBoard {

	public static int getNumberOfBowserCars() {
		return NUMBER_OF_BOWSER_CARS;
	}

	public static int getNumberOfDonkeykongCars() {
		return NUMBER_OF_DONKEYKONG_CARS;
	}

	//	private static final int NUMBER_OF_SLOW_CARS = 0;
	//private static final int NUMBER_OF_TESLA_CARS = 0;
//	private static final int NUMBER_OF_MARIO_CARS = 1;
//	private static final int NUMBER_OF_BOWSER_CARS = 1;
	private static int NUMBER_OF_BOWSER_CARS = 1;
//	private static final int NUMBER_OF_DONKEYKONG_CARS =1;
	private static int NUMBER_OF_DONKEYKONG_CARS =1;

	public boolean isMULTIPLAYER_ON() {
		return MULTIPLAYER_ON;
	}

	public void setMULTIPLAYER_ON(boolean MULTIPLAYER_ON) {
		this.MULTIPLAYER_ON = MULTIPLAYER_ON;
	}

	//TODO CHANGE MANUALLY TO LOCAL MULTIPLAYER
	private boolean MULTIPLAYER_ON = false;

	public boolean getMULTIPLAYER_ON() {
		return this.isMULTIPLAYER_ON();
	}

	/**
	 * List of all active cars, does not contain player car.
	 */
	private final List<Car> cars = new ArrayList<>();

	/**
	 * The player object with player's car.
	 */
	private final Player player;
	private final Player2 player2;

	/**
	 * AudioPlayer responsible for handling music and game sounds.
	 */
	private AudioPlayerInterface audioPlayer;

	/**
	 * Dimension of the GameBoard.
	 */
	private final Dimension2D size;

	/**
	 * true if game is running, false if game is stopped.
	 */
	private boolean running;

	/**
	 * List of all loser cars (needed for testing, DO NOT DELETE THIS)
	 */
	private final List<Car> loserCars = new ArrayList<>();

	/**
	 * The outcome of this game from the players perspective. The game's outcome is open at the beginning.
	 */
	private GameOutcome gameOutcome = GameOutcome.OPEN;

	/**
	 * Creates the game board based on the given size.
	 *
	 * @param size of the game board
	 */
	public GameBoard(Dimension2D size) {
		this.size = size;
		FastCar playerCar = new FastCar(size);
		this.player = new Player(playerCar);
		this.player.setup();
//		System.out.println("TEST");

		if(this.isMULTIPLAYER_ON()) {
//			FastCar player2Car = new FastCar(size);
			BowserCar player2Car = new BowserCar(size);
			this.player2 = new Player2(player2Car);
			this.player2.setup();
		} else {
			player2 = null;
		}

		createCars();
	}

	public GameBoard(Dimension2D size, boolean multiPlayer) {
		this.size = size;
		FastCar playerCar = new FastCar(size);
		this.player = new Player(playerCar);
		this.player.setup();
//		System.out.println("TEST");
		this.setMULTIPLAYER_ON(multiPlayer);

		if(this.isMULTIPLAYER_ON()) {
			BowserCar player2Car = new BowserCar(size);
			this.player2 = new Player2(player2Car);
			this.player2.setup();
		} else {
			player2 = null;
		}

		createCars();
	}	public GameBoard(Dimension2D size, boolean multiPlayer, int bowser, int dk) {
//		System.out.println("size = " + size + ", multiPlayer = " + multiPlayer + ", bowser = " + bowser + ", dk = " + dk);
		this.size = size;
		NUMBER_OF_BOWSER_CARS = bowser;
		NUMBER_OF_DONKEYKONG_CARS = dk;
		FastCar playerCar = new FastCar(size);
		this.player = new Player(playerCar);
		this.player.setup();
//		System.out.println("TEST");
		this.setMULTIPLAYER_ON(multiPlayer);

		if(this.isMULTIPLAYER_ON()) {
			BowserCar player2Car = new BowserCar(size);
			this.player2 = new Player2(player2Car);
			this.player2.setup();
		} else {
			player2 = null;
		}

		createCars();
	}

	/**
	 * Creates as many cars as specified by {NUMBER_OF_SLOW_CARS} and adds
	 * them to the cars list.
	 */
	private void createCars() {
		//for (int i = 0; i < NUMBER_OF_SLOW_CARS; i++) {
		//	this.cars.add(new SlowCar(this.size));
		//}
//		for (int i = 0; i < NUMBER_OF_TESLA_CARS; i++) {
//			this.cars.add(new FastCar(this.size));
//		}
		if(!getMULTIPLAYER_ON()) {

		for (int i = 0; i < NUMBER_OF_DONKEYKONG_CARS; i++) {
			this.cars.add(new DonkeyKongCar(this.size));
		}	for (int i = 0; i < NUMBER_OF_BOWSER_CARS; i++) {
			this.cars.add(new BowserCar(this.size));
		}
		}
	}

	public Dimension2D getSize() {
		return size;
	}

	/**
	 * Returns if game is currently running.
	 *
	 * @return true if the game is currently running, false otherwise
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * Sets whether the game should be currently running.
	 * <p>
	 * Also used for testing on Artemis.
	 *
	 * @param running true if the game should be running
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameOutcome getGameOutcome() {
		return gameOutcome;
	}

	/**
	 * Returns all cars on the game board except the player's car as a list.
	 *
	 * @return the list of all non-player cars
	 */
	public List<Car> getCars() {
		return this.cars;
	}

	public Car getPlayerCar() {
		return this.player.getCar();
	}
	public Car getPlayer2Car() {
		return this.player2.getCar();
	}

	public AudioPlayerInterface getAudioPlayer() {
		return this.audioPlayer;
	}

	public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	/**
	 * Updates the position of each car.
	 */
	public void update() {
		moveCars();
	}

	/**
	 * Starts the game. Cars start to move and background music starts to play.
	 */
	public void startGame() {
		playMusic();
		this.running = true;
	}

	/**
	 * Stops the game. Cars stop moving and background music stops playing.
	 */
	public void stopGame() {
		stopMusic();
		this.running = false;
	}

	/**
	 * Starts the background music.
	 */
	public void playMusic() {
		this.audioPlayer.playBackgroundMusic();
	}

	/**
	 * Stops the background music.
	 */
	public void stopMusic() {
		this.audioPlayer.stopBackgroundMusic();
	}

	/**
	 * @return list of loser cars
	 */
	public List<Car> getLoserCars() {
		return this.loserCars;
	}

	/**
	 * Moves all cars on this game board one step further.
	 */
	public void moveCars() {
		// update the positions of the player car and the autonomous cars
		for (Car car : this.cars) {
			car.drive(size);
		}
		this.player.getCar().drive(size);
		if(getMULTIPLAYER_ON()) {
			this.player2.getCar().drive(size);
		}
		if(this.getMULTIPLAYER_ON() && !player2.getCar().isCrunched()) {
			this.player2.getCar().drive(size);
//			Collision collision12 = new DefaultCollision(player.getCar(), player2.getCar());
			Collision collision12 = new ModeCollision(player.getCar(), player2.getCar());
			if(collision12.isCrash()) {
				Car	winner = collision12.evaluate();
				Car loser = collision12.evaluateLoser();
				printWinner(winner);
				loserCars.add(loser);
				this.audioPlayer.playCrashSound();
				if (player.getCar().equals(winner) && this.getLoserCars().size() == this.getCars().size() && this.isWinner()) {
					System.out.println("Player 1 has won the game!");
					this.gameOutcome = GameOutcome.WON;
				} else if (player.getCar().equals(loser)) {
					System.out.println("Player 1 has lost the game!");
					this.gameOutcome = GameOutcome.LOST;
				}
				if (player2.getCar().equals(winner) && this.getLoserCars().size() == this.getCars().size() && this.isWinner()) {
					System.out.println("Player 2 has won the game!");
					this.gameOutcome = GameOutcome.LOST;
				} else if (player2.getCar().equals(loser)) {
					System.out.println("Player 2 has lost the game!");
					this.gameOutcome = GameOutcome.WON;
				}

			}
		}
		// iterate through all cars (except player car) and check if it is crunched

		for (Car car : cars) {
			if (car.isCrunched()) {
				// because there is no need to check for a collision
				continue;
			}

			/*
			 * Hint: Make sure to create a subclass of the class Collision and store it in
			 * the new Collision package. Create a new collision object and check if the
			 * collision between player car and autonomous car evaluates as expected
			 */

//			Collision collision = new DefaultCollision(player.getCar(), car);
			ModeCollision collision = new ModeCollision(player.getCar(), car);
			ModeCollision collision2 = null;

			if(this.getMULTIPLAYER_ON()) {
//				collision2 = new DefaultCollision(player2.getCar(), car);
				collision2 = new ModeCollision(player2.getCar(), car);
			}

			if (collision.isCrash() || collision2 != null && collision2.isCrash()) {

				Car winner = null;
				Car loser = null;
				if(collision.isCrash()) {
					 winner = collision.evaluate();
					loser = collision.evaluateLoser();
					printWinner(winner);
					loserCars.add(loser);
				}
				else if(collision2.isCrash()) {
					 winner = collision2.evaluate();
					 loser = collision2.evaluateLoser();
					printWinner(winner);
					loserCars.add(loser);
				}

				this.audioPlayer.playCrashSound();

				loser.crunch();
				if (player.getCar().equals(winner) && this.getLoserCars().size() == this.getCars().size() && this.isWinner()) {
					System.out.println("Player 1 has won the game!");
					this.gameOutcome = GameOutcome.WON;
				} else if (player.getCar().equals(loser)) {
					System.out.println("Player 1 has lost the game!");
					this.gameOutcome = GameOutcome.LOST;
				}
				if (this.getMULTIPLAYER_ON() && player2.getCar().equals(winner) && this.getLoserCars().size() == this.getCars().size() && this.isWinner()) {
					System.out.println("Player 2 has won the game!");
					this.gameOutcome = GameOutcome.WON;
				} else if (this.getMULTIPLAYER_ON() && player2.getCar().equals(loser)) {
					System.out.println("Player 2 has lost the game!");
					this.gameOutcome = GameOutcome.LOST;
				}
				/*
				 * Hint: you should set the attribute gameOutcome accordingly. Use 'isWinner()'
				 * below for your implementation
				 */

			}
		}
	}

	/**
	 * If all other cars are crunched, the player wins.
	 *
	 * @return true if the game is over and the player won, false otherwise
	 */
	private boolean isWinner() {
		for (Car car : getCars()) {
			if (!car.isCrunched()) {
				return false;
			}
		}
		return true;
	}

	private void printWinner(Car winner) {
		if (winner == this.player.getCar()) {
			System.out.println("The player's car won the collision!");
		} else if (winner != null) {
			System.out.println(winner.getClass().getSimpleName() + " won the collision!");
		} else {
			System.err.println("Winner car was null!");
		}
	}
}
