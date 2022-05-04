package de.tum.in.ase.eist.gameview;

import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.GameOutcome;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.audio.AudioPlayer;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.usercontrol.MouseSteering;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.*;


/**
 * This class implements the user interface for steering the player car. The
 * user interface is implemented as a Thread that is started by clicking the
 * start button on the tool bar and stops by the stop button.
 */
public class GameBoardUI extends Canvas {

	//private static final Color BACKGROUND_COLOR = Color.WHITE;
	/**
	 * The update period of the game in ms, this gives us 25 fps.
	 */
	private static final int UPDATE_PERIOD = 1000 / 25;
	private static final int DEFAULT_WIDTH = 1000;
//private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 600;
	//	private static final int DEFAULT_HEIGHT = 300;
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	public static Dimension2D getPreferredSize() {
		return DEFAULT_SIZE;
	}

	/**
	 * Timer responsible for updating the game every frame that runs in a separate
	 * thread.
	 */
	private Timer gameTimer;

	private GameBoard gameBoard;

	private final GameToolBar gameToolBar;

	private MouseSteering mouseSteering;
	private MouseSteering mouse2Steering;
//	private WASDSteering wasdSteering;

	private HashMap<String, Image> imageCache;

	public GameBoardUI(GameToolBar gameToolBar) {
		this.gameToolBar = gameToolBar;
		setup();
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public MouseSteering getMouseSteering() {
		return mouseSteering;
	}
		//	}public WASDSteering getWASDSteering() {
//		return wasdSteering;
//	}

	/**
	 * Removes all existing cars from the game board and re-adds them. Player car is
	 * reset to default starting position. Renders graphics.
	 */
	public void setup() {
		setupGameBoard();
		setupImageCache();
		this.gameToolBar.updateToolBarStatus(false);
		paint();
	}

	private void setupGameBoard() {
		Dimension2D size = getPreferredSize();
		this.gameBoard = new GameBoard(size);
		this.gameBoard.setAudioPlayer(new AudioPlayer());

		widthProperty().set(size.getWidth());
		heightProperty().set(size.getHeight());

		this.mouseSteering = new MouseSteering(this.gameBoard.getPlayerCar());

		this.setOnKeyPressed((KeyEvent e) -> {this.mouseSteering.keyPressed(e);});


		if(gameBoard.getMULTIPLAYER_ON()) {
			this.mouse2Steering = new MouseSteering(this.gameBoard.getPlayer2Car());

		this.setOnMouseClicked((MouseEvent e) -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				this.mouseSteering.mousePressed(e);
			} else if (e.getButton() == MouseButton.SECONDARY) {
				this.mouse2Steering.mousePressed(e);
			}
			});

//		this.setOnKeyPressed((KeyEvent e) -> {
//				if (e.getCode() != KeyCode.ALPHANUMERIC) {
//					this.mouseSteering.keyPressed(e);
//				} else if (e.getCode() == KeyCode.UP) {
//					this.mouse2Steering.keyPressed(e);
//				}
//		});

		} else {

//		this.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
//				this.mouseSteering.keyPressed(e);
//			});
//		}

		this.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent clickEvent) -> {
			this.mouseSteering.mousePressed(clickEvent);
		});

		this.addEventHandler(ScrollEvent.SCROLL, (ScrollEvent scrollEvent) -> {
			this.mouseSteering.scrollPressed(scrollEvent);
		});
	}}

	private void setupImageCache() {
		this.imageCache = new HashMap<>();
		for (Car car : this.gameBoard.getCars()) {
			// Loads the image into the cache
			getImage(car.getIconLocation());
		}
		String playerImageLocation = this.gameBoard.getPlayerCar().getIconLocation();
		if(this.gameBoard.getMULTIPLAYER_ON()) {
			String player2ImageLocation = this.gameBoard.getPlayer2Car().getIconLocation();
		}
		getImage(playerImageLocation);
	}

	/**
	 * Returns the car's image. If no image is present in the cache, a new image is created.
	 *
	 * @param carImageFilePath an image file path that needs to be available in the
	 *                         resources folder of the project
	 */
	private Image getImage(String carImageFilePath) {
		return this.imageCache.computeIfAbsent(carImageFilePath, this::createImage);
	}

	/**
	 * Loads the car's image.
	 *
	 * @param carImageFilePath an image file path that needs to be available in the
	 *                         resources folder of the project
	 */
	private Image createImage(String carImageFilePath) {
		URL carImageUrl = getClass().getClassLoader().getResource(carImageFilePath);
		if (carImageUrl == null) {
			throw new IllegalArgumentException(
					"Please ensure that your resources folder contains the appropriate files for this exercise.");
		}
		return new Image(carImageUrl.toExternalForm());
	}

	/**
	 * Starts the GameBoardUI Thread, if it wasn't running. Starts the game board,
	 * which causes the cars to change their positions (i.e. move). Renders graphics
	 * and updates tool bar status.
	 */
	public void startGame() {
		if (!this.gameBoard.isRunning()) {
			this.gameBoard.startGame();
			this.gameToolBar.updateToolBarStatus(true);
			startTimer();
			paint();
		}
	}

	private void startTimer() {
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				updateGame();
			}
		};
		if (this.gameTimer != null) {
			this.gameTimer.cancel();
		}
		this.gameTimer = new Timer();
		this.gameTimer.scheduleAtFixedRate(timerTask, UPDATE_PERIOD, UPDATE_PERIOD);
	}

	private void updateGame() {
		if (gameBoard.isRunning()) {
			// updates car positions and re-renders graphics
			this.gameBoard.update();
			// when this.gameBoard.getOutcome() is OPEN, do nothing
			if (this.gameBoard.getGameOutcome() == GameOutcome.LOST) {
				showAsyncAlert("Oh.. you lost.");
				this.stopGame();
			} else if (this.gameBoard.getGameOutcome() == GameOutcome.WON) {
				showAsyncAlert("Congratulations! You won!!");
				this.stopGame();
			}
			paint();
		}
	}

	/**
	 * Stops the game board and set the tool bar to default values.
	 */
	public void stopGame() {
		if (this.gameBoard.isRunning()) {
			this.gameBoard.stopGame();
			this.gameToolBar.updateToolBarStatus(false);
			this.gameTimer.cancel();
		}
	}

	/**
	 * Render the graphics of the whole game by iterating through the cars of the
	 * game board at render each of them individually.
	 */
	private void paint() {
		//TODO testtest
		//getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
		getGraphicsContext2D().drawImage(getImage("background.png"), 0, 0, getWidth(), getHeight());
		//getGraphicsContext2D().setFill(BACKGROUND_COLOR);

		for (Car car : this.gameBoard.getCars()) {
			paintCar(car);
		}
		// render player car
		paintCar(this.gameBoard.getPlayerCar());
		if(gameBoard.getMULTIPLAYER_ON()) {
		paintCar(this.gameBoard.getPlayer2Car());
		}
	}


	/**
	 * Show image of a car at the current position of the car.
	 *
	 * @param car to be drawn
	 */
	private void paintCar(Car car) {
		Point2D carPosition = car.getPosition();

		if(car.getDirection() > 180) {
			getGraphicsContext2D().drawImage(getImage(car.getIconLocation()), carPosition.getX(),
					carPosition.getY(), car.getSize().getWidth(), car.getSize().getHeight());
		} else {
		getGraphicsContext2D().drawImage(getImage(car.getIconLocation()), carPosition.getX(),
				carPosition.getY(), car.getSize().getWidth(), car.getSize().getHeight());
	}
	}

	/**
	 * Method used to display alerts in moveCars().
	 *
	 * @param message you want to display as a String
	 */
	private void showAsyncAlert(String message) {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(message);
			alert.showAndWait();
			this.setup();
		});
	}
}
