package de.tum.in.ase.eist.gameview;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

import javax.swing.border.EmptyBorder;
import java.util.Optional;

/**
 * This class visualizes the tool bar with start and stop buttons above the game board.
 */
public class GameToolBar extends ToolBar {
	private boolean cheating = false;
	public Button getMultiPlayerON() {
		return multiPlayerON;
	}

	public Button getMultiPlayerOFF() {
		return multiPlayerOFF;
	}

	private final Button start;
	private final Button stop;
	private final Text text;

	private final Button cheatMode;
	private final Button multiPlayerON;

	public Button getEasyMode() {
		return easyMode;
	}

	private final Button easyMode;
	private final Button normalMode;
	private final Button hardMode;
	private final Button multiPlayerOFF;

	private int bowser = 1;
	private int dk = 1;

	public GameToolBar() {
		this.start = new Button("Start");
		this.stop = new Button("Stop");
		this.text = new Text("local MultiPlayer Mode");
		this.cheatMode = new Button("enable cheats ;)");

		this.easyMode = new Button("Easy");
		this.normalMode = new Button("Normal");
		this.hardMode = new Button("Hard");
		this.multiPlayerON = new Button("ON");
		this.multiPlayerOFF = new Button("OFF");
		this.multiPlayerOFF.setDisable(true);
		this.multiPlayerON.setDisable(false);
		this.easyMode.setDisable(true);
		// the game is stopped initially
		updateToolBarStatus(false);
		getItems().addAll(this.start, new Separator(), this.stop);
		getItems().addAll(new Separator(), this.easyMode, this.normalMode, this.hardMode, new Separator(), this.text, this.multiPlayerON, this.multiPlayerOFF);
		Text sep = new Text("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
sep.setVisible(false);
		getItems().addAll(sep, this.cheatMode);
	}

	/**
	 * Initializes the actions of the toolbar buttons.
	 */
	public void initializeActions(GameBoardUI gameBoardUI) {
		this.easyMode.setOnAction(event ->  {
			if(this.normalMode.isDisabled()) {
				this.normalMode.setDisable(false);
			} else if(this.hardMode.isDisabled()) {
				this.hardMode.setDisable(false);
			}
			this.easyMode.setDisable(true);
			this.bowser = 1;
			this.dk = 1;
			gameBoardUI.stopGame();
			gameBoardUI.getGameBoard().stopGame();
			gameBoardUI.setup(false, bowser, dk);

		});

		this.normalMode.setOnAction(event -> {
			if (this.easyMode.isDisabled()) {
				this.easyMode.setDisable(false);
			} else if (this.hardMode.isDisabled()) {
				this.hardMode.setDisable(false);
			}
			this.normalMode.setDisable(true);
			this.bowser = 2;
			this.dk = 2;
			gameBoardUI.stopGame();
			gameBoardUI.getGameBoard().stopGame();
			gameBoardUI.setup(false, bowser, dk);
		});

		this.hardMode.setOnAction(event -> {
			if (this.normalMode.isDisabled()) {
				this.normalMode.setDisable(false);
			} else if (this.easyMode.isDisabled()) {
				this.easyMode.setDisable(false);
			}
			this.hardMode.setDisable(true);
			this.bowser = 3;
			this.dk = 3;
			gameBoardUI.stopGame();
			gameBoardUI.getGameBoard().stopGame();
			gameBoardUI.setup(false, bowser, dk);
		});


		this.cheatMode.setOnAction(event ->  {
			if(!cheating) {
				cheating = true;
				gameBoardUI.getGameBoard().getAudioPlayer().playCheatSound();
				gameBoardUI.cheat();
				gameBoardUI.stopGame();
			} else {
				cheating = false;
				gameBoardUI.getGameBoard().getAudioPlayer().stopCheatMusic();
				gameBoardUI.stopGame();
			}

		});
		this.start.setOnAction(event -> gameBoardUI.startGame());
		this.stop.setOnAction(event -> {
			// stop the game while the alert is shown
			gameBoardUI.stopGame();

			Alert alert = new Alert(AlertType.CONFIRMATION, "Do you really want to stop the game?", ButtonType.YES,
					ButtonType.NO);
			alert.setTitle("Stop Game Confirmation");
			// By default the header additionally shows the Alert Type (Confirmation)
			// but we want to disable this to only show the question
			alert.setHeaderText("");

			Optional<ButtonType> result = alert.showAndWait();
			// reference equality check is OK here because the result will return the same
			// instance of the ButtonType
			if (result.isPresent() && result.get() == ButtonType.YES) {
				// reset the game board to prepare the new game

				gameBoardUI.setup(false, bowser, dk);
				this.multiPlayerON.setDisable(false);
				this.multiPlayerOFF.setDisable(true);
			} else {
				// continue running
				gameBoardUI.startGame();
			}
		});
		this.multiPlayerON.setOnAction(event -> {
				this.easyMode.setDisable(true);
				this.normalMode.setDisable(true);
				this.hardMode.setDisable(true);
				gameBoardUI.getGameBoard().setMULTIPLAYER_ON(true);
				gameBoardUI.stopGame();
				gameBoardUI.setup(true, bowser, dk);
				multiPlayerON.setDisable(true);
				multiPlayerOFF.setDisable(false);
//				gameBoardUI.getGameBoard().stopGame();
//				gameBoardUI.getGameBoard().startGame();
//				gameBoardUI.startGame();
		});

		this.multiPlayerOFF.setOnAction(event -> {
			this.easyMode.setDisable(true);
			this.normalMode.setDisable(false);
			this.hardMode.setDisable(false);
			gameBoardUI.getGameBoard().setMULTIPLAYER_ON(false);
			gameBoardUI.getGameBoard().stopGame();
			gameBoardUI.setup(false, 1, 1);
			multiPlayerON.setDisable(false);
			multiPlayerOFF.setDisable(true);
		});	}

	/**
	 * Updates the status of the toolbar. This will for example enable or disable
	 * buttons.
	 *
	 * @param running true if game is running, false otherwise
	 */
	public void updateToolBarStatus(boolean running) {
		this.start.setDisable(running);

		this.stop.setDisable(!running);
//		this.multiPlayerON.setDisable(running);
//		this.multiPlayerOFF.setDisable(running);
	}
}
