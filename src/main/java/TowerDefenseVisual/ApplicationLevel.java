package TowerDefenseVisual;


import java.io.IOException;

import org.json.JSONException;

import Sauvegarde.*;
import Datamodel.LoadImage;
import Field.FactoryLevel;
import Field.Level;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ApplicationLevel extends Application{

	public final Level[] levels = new Level[11];
	public static boolean[] autorisations = new boolean[11];

	public ApplicationLevel() {
		// TODO Auto-generated constructor stub
		for(int i=0;i<levels.length;i++) {
			try {
				this.levels[i] = FactoryLevel.factory(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			autorisations = Save.sauvegarde();
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * pour mettre a jour les autorisations on va placer un id sur chaque level
		 * et en cas de victoire a ce level, on appelera une methode qui mettre a jour le fichier json ainsi 
		 * que les autorisation de level
		 */
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub


		primaryStage.setFullScreen(true);

		// les dimensions de l'ecran
		int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();

		Image image = null;
		image = new Image(LoadImage.class.getResourceAsStream("/Image/Mordor.jpg" ));

		ImageView imageView = new ImageView(image);
		imageView.setLayoutX(0);
		imageView.setLayoutY(0);
		imageView.setFitWidth(screenWidth);
		imageView.setFitHeight(screenHeight);
		imageView.setPreserveRatio(false);

		Pane root = new Pane(); 
		root.getChildren().setAll(imageView); 


		//button 1 
		Button b1 = new Button("1");
		b1.setLayoutX(95);
		b1.setLayoutY(115);
		root.getChildren().add(b1);
		b1.setFont(Font.font(17));
		b1.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2));
		b1.setBorder(new Border(borderStroke));
		b1.setVisible(autorisations[1]);

		//button 2 
		Button b2 = new Button("2");
		b2.setLayoutX(170);
		b2.setLayoutY(190);
		root.getChildren().add(b2);
		b2.setFont(Font.font(17));
		b2.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b2.setBorder(new Border(borderStroke));
		b2.setVisible(autorisations[2]);

		//button 3
		Button b3 = new Button("3");
		b3.setLayoutX(380);
		b3.setLayoutY(140);
		root.getChildren().add(b3);
		b3.setFont(Font.font(17));
		b3.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b3.setBorder(new Border(borderStroke));
		b3.setVisible(autorisations[3]);

		//button 4
		Button b4 = new Button("4");
		b4.setLayoutX(560);
		b4.setLayoutY(220);
		root.getChildren().add(b4);
		b4.setFont(Font.font(17));
		b4.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b4.setBorder(new Border(borderStroke));
		b4.setVisible(autorisations[4]);

		//button 5
		Button b5 = new Button("5");
		b5.setLayoutX(700);
		b5.setLayoutY(230);
		root.getChildren().add(b5);
		b5.setFont(Font.font(17));
		b5.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b5.setBorder(new Border(borderStroke));
		b5.setVisible(autorisations[5]);

		//button 6
		Button b6 = new Button("6");
		b6.setLayoutX(750);
		b6.setLayoutY(360);
		root.getChildren().add(b6);
		b6.setFont(Font.font(17));
		b6.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b6.setBorder(new Border(borderStroke));
		b6.setVisible(autorisations[6]);

		//button 7
		Button b7 = new Button("7");
		b7.setLayoutX(434);
		b7.setLayoutY(423);
		root.getChildren().add(b7);
		b7.setFont(Font.font(17));
		b7.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b7.setBorder(new Border(borderStroke));
		b7.setVisible(autorisations[7]);

		//button 8
		Button b8 = new Button("8");
		b8.setLayoutX(630);
		b8.setLayoutY(560);
		root.getChildren().add(b8);
		b8.setFont(Font.font(17));
		b8.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b8.setBorder(new Border(borderStroke));
		b8.setVisible(autorisations[8]);

		//button 9
		Button b9 = new Button("9");
		b9.setLayoutX(820);
		b9.setLayoutY(500);
		root.getChildren().add(b9);
		b9.setFont(Font.font(17));
		b9.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b9.setBorder(new Border(borderStroke));
		b9.setVisible(autorisations[9]);

		//button 10
		Button b10 = new Button("10");
		b10.setLayoutX(930);
		b10.setLayoutY(565);
		root.getChildren().add(b10);
		b10.setFont(Font.font(17));
		b10.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		b10.setBorder(new Border(borderStroke));
		b10.setVisible(autorisations[10]);

		//button survival
		Button survival = new Button("Survival");
		survival.setLayoutX(1050);
		survival.setLayoutY(150);
		root.getChildren().add(survival);
		survival.setFont(Font.font(17));
		survival.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
		survival.setBorder(new Border(borderStroke));
		survival.setVisible(autorisations[0]);

		//button new Game
		Button newGame = new Button("New Game");
		newGame.setLayoutX(0);
		newGame.setLayoutY(680);
		newGame.setFont(Font.font(17));
		newGame.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		newGame.setBorder(new Border(borderStroke));
		root.getChildren().add(newGame);

		Scene scene = new Scene(root, primaryStage.getHeight(),primaryStage.getWidth());
		primaryStage.setTitle("Map towerdefense"); 
		primaryStage.setScene(scene); 
		primaryStage.show();

		//action button 1

		b1.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 1);
			}
		});

		//action button 2

		b2.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 2);
			}
		});

		//action button 3

		b3.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 3);
			}
		});

		//action button 4

		b4.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 4);
			}
		});

		//action button 5

		b5.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 5);
			}
		});

		//action button 6

		b6.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 6);
			}
		});

		//action button 7

		b7.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 7);
			}
		});

		//action button 8

		b8.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 8);
			}
		});

		//action button 9

		b9.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 9);
			}
		});

		//action button 10

		b10.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 10);
			}
		});

		//action button survival

		survival.setOnMouseClicked((MouseEvent event) -> {
			if (event.getButton().equals(MouseButton.PRIMARY)) {

				root.getChildren().clear();
				showNext(primaryStage, scene, 0);
			}
		});

		//action button new game

		newGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				VBox root = new VBox();



				Label secondLabel = new Label("Voulez vous vraiment recommencer ?");
				secondLabel.setTextFill(Color.ANTIQUEWHITE);

				StackPane secondaryLayout = new StackPane();
				secondaryLayout.getChildren().add(secondLabel);
				root.getChildren().add(secondaryLayout);

				HBox root2 = new HBox();

				root2.setSpacing(50);
				root2.setPadding(new Insets(25,25, 70,70));

				Button oui = new Button("oui");
				oui.setAlignment(Pos.CENTER_LEFT);
				oui.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2));
				oui.setBorder(new Border(borderStroke));
				root2.getChildren().add(oui);

				Button non = new Button("non");
				oui.setAlignment(Pos.CENTER_RIGHT);
				non.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				non.setBorder(new Border(borderStroke));
				root2.getChildren().add(non);

				root.getChildren().add(root2);
				root.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
				Scene secondScene = new Scene(root, 300, 100);



				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("New Game");
				newWindow.setScene(secondScene);

				// Specifies the modality for new window.
				newWindow.initModality(Modality.WINDOW_MODAL);

				// Specifies the owner Window (parent) for new window
				newWindow.initOwner(primaryStage);

				// Set position of second window, related to primary window.
				newWindow.setX(primaryStage.getX() + 200);
				newWindow.setY(primaryStage.getY() + 100);

				newWindow.show();

				//action button oui 
				oui.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						Save.newGame();
						try {
							autorisations = Save.sauvegarde();
						} catch (IOException | JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						newWindow.close();
						try {
							Stage second = new Stage();
							start(second);
							primaryStage.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

				//action button non 

				non.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						newWindow.close();
					}
				});
			}


		});
	}

	private void showNext(Stage s, Scene sc, int i) {
		ApplicationFx a = new ApplicationFx(this.levels[i], i);
		try {
			a.start(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

