package jp.ocn;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class TransitionTestMainOnly extends Application {
	int abs = 1;

	@Override
	public void start(Stage primaryStage) throws Exception {
				
		Button start_button = new Button("Transition Start");
		Label transition_label = new Label("Transition Label");

		start_button.setLayoutX(250);
		start_button.setLayoutY(50);

		AnchorPane root = new AnchorPane();
		root.getChildren().add(start_button);
		root.getChildren().add(transition_label);

		start_button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TranslateTransition transition = new TranslateTransition(
						new Duration(500), transition_label);
				transition.setToX(500 * abs);
				transition.setToY(375 * abs);
				PauseTransition pauseTransition=new PauseTransition(new Duration(500));
				SequentialTransition seqentialTransition=new SequentialTransition(transition,pauseTransition);

				Stage stage = new Stage();
				Label label = new Label("Running");
				AnchorPane root = new AnchorPane();
				root.getChildren().add(label);
				Scene scene = new Scene(root, 200, 60);
				stage.setScene(scene);
				stage.setMaxWidth(200);
				stage.setMaxHeight(60);
				stage.setTitle("メッセージ");
				stage.setX(-500);
				stage.setY(-300);
//				stage.centerOnScreen();
				stage.initModality(Modality.WINDOW_MODAL);
				final Window window = stage;
				seqentialTransition.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						window.hide();
					}

				});
				
				seqentialTransition.play();
				stage.showAndWait();

				abs *= -1;
			}
		});

		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}