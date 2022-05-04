package de.tum.in.ase.eist.gameview;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

import java.util.Optional;

/**
 * This class visualizes the tool bar with start and stop buttons above the game board.
 */
public class GameToolBar extends ToolBar {
	public Button getMultiPlayerON() {
		return multiPlayerON;
	}

	public Button getMultiPlayerOFF() {
		return multiPlayerOFF;
	}

	private final Button start;
	private final Button stop;
	private final Text text;
	private final Button multiPlayerON;
	private final Button multiPlayerOFF;

	public GameToolBar() {
		this.start = new Button("Start");
		this.stop = new Button("Stop");
		this.text = new Text("local MultiPlayer Mode");
		this.multiPlayerON = new Button("ON");
		this.multiPlayerOFF = new Button("OFF");
		this.multiPlayerOFF.setDisable(true);
		this.multiPlayerON.setDisable(false);
		// the game is stopped initially
		updateToolBarStatus(false);
		getItems().addAll(this.start, new Separator(), this.stop, new Separator(), this.text, this.multiPlayerON, this.multiPlayerOFF);
	}

	/**
	 * Initializes the actions of the toolbar buttons.
	 */
	public void initializeActions(GameBoardUI gameBoardUI) {
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
				gameBoardUI.setup(false);
				this.multiPlayerON.setDisable(true);
				this.multiPlayerOFF.setDisable(false);
			} else {
				// continue running
				gameBoardUI.startGame();
			}
		});
		this.multiPlayerON.setOnAction(event -> {

				gameBoardUI.getGameBoard().setMULTIPLAYER_ON(true);
				gameBoardUI.stopGame();
				gameBoardUI.setup(true);
				multiPlayerON.setDisable(true);
				multiPlayerOFF.setDisable(false);
//				gameBoardUI.getGameBoard().stopGame();
//				gameBoardUI.getGameBoard().startGame();
//				gameBoardUI.startGame();
		});

		this.multiPlayerOFF.setOnAction(event -> {

			gameBoardUI.getGameBoard().setMULTIPLAYER_ON(false);
			gameBoardUI.getGameBoard().stopGame();
			gameBoardUI.setup(false);
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
