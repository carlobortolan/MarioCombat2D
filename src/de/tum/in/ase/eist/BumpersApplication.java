package de.tum.in.ase.eist;

import de.tum.in.ase.eist.gameview.GameBoardUI;
import de.tum.in.ase.eist.gameview.GameToolBar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Starts the Bumpers Application, loads the GameToolBar and GameBoardUI. This
 * class is the root of the JavaFX Application.
 *
 * @see Application
 */
public class BumpersApplication extends Application {

	private static final int GRID_LAYOUT_PADDING = 5;
	private static final int GRID_LAYOUT_PREF_HEIGHT = 350;
	private static final int GRID_LAYOUT_PREF_WIDTH = 505;

	/**
	 * Starts the Bumpers Window by setting up a new tool bar, a new user interface
	 * and adding them to the stage.
	 *
	 * @param primaryStage the primary stage for this application, onto which the
	 *                     application scene can be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		// the tool bar object with start and stop buttons
		GameToolBar toolBar = new GameToolBar();
		GameBoardUI gameBoardUI = new GameBoardUI(toolBar, false, 1, 1);
		toolBar.initializeActions(gameBoardUI);
		toolBar.getEasyMode().fire();


		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Have you read the instructions on the provided readme File?", ButtonType.YES,
				ButtonType.NO);
		alert.setTitle("CONFIRM INSTUCTIONS");
		// By default the header additionally shows the Alert Type (Confirmation)
		// but we want to disable this to only show the question
		alert.setHeaderText("");

		Optional<ButtonType> result = alert.showAndWait();
		// reference equality check is OK here because the result will return the same
		// instance of the ButtonType
		if (result.isPresent() && result.get() == ButtonType.YES) {

		} else if(result.get() == ButtonType.NO){
			Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Please read the instructions on the provided readme File", ButtonType.YES,
					ButtonType.NO);
			alert2.setTitle("CONFIRM INSTUCTIONS");
			alert2.setHeaderText("");

			alert2.show();
		}

//		toolBar.getMultiPlayerON().setOnAction(event -> {
//
//			gameBoardUI.getGameBoard().setMULTIPLAYER_ON(true);
//			gameBoardUI.stopGame();
//			gameBoardUI.setup();
//			toolBar.getMultiPlayerON().setDisable(true);
//			toolBar.getMultiPlayerOFF().setDisable(false);
//			toolBar.initializeActions(gameBoardUI);
//		});
//
//		toolBar.getMultiPlayerOFF().setOnAction(event -> {
//
//			gameBoardUI.getGameBoard().setMULTIPLAYER_ON(false);
//			gameBoardUI.getGameBoard().startGame();
//			gameBoardUI.stopGame();
//			gameBoardUI.setup();
//			toolBar.getMultiPlayerON().setDisable(false);
//			toolBar.getMultiPlayerOFF().setDisable(true);
//			this.start(primaryStage);
//		});

		Pane gridLayout = createLayout(gameBoardUI, toolBar);

		// scene and stages
		Scene scene = new Scene(gridLayout);
//		primaryStage.setTitle("Bumpers");
		primaryStage.setTitle("Super Mario Combat");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(closeEvent -> gameBoardUI.stopGame());
		primaryStage.show();
	}

	/**
	 * Creates a new {@link Pane} that arranges the game's UI elements.
	 */
	private static Pane createLayout(GameBoardUI gameBoardUI, GameToolBar toolBar) {
		// GridPanes are divided into columns and rows, like a table
		GridPane gridLayout = new GridPane();
		gridLayout.setPrefSize(GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
		gridLayout.setVgap(GRID_LAYOUT_PADDING);
		gridLayout.setPadding(new Insets(GRID_LAYOUT_PADDING));

		// add all components to the gridLayout
		// second parameter is column index, second parameter is row index of grid
		gridLayout.add(gameBoardUI, 0, 1);
		gridLayout.add(toolBar, 0, 0);
		return gridLayout;
	}

	/**
	 * The whole game will be executed through the launch() method.
	 * <p>
	 * Use {@link Bumpers#main(String[])} to run the Java application.
	 */
	public static void startApp(String[] args) {
		launch(args);
	}
}
