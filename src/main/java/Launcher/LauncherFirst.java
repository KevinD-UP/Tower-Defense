package Launcher;

import Sauvegarde.Save;
import TowerDefenseVisual.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LauncherFirst extends Application {

	public static void main(String[] args) {

		//creation de la sauvegarde
		Save sauvegarde = new Save(11);

		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {

		s.setFullScreen(true);
		s.setTitle("TowerDefense");
		s.setResizable(true);

		//Texte

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(25));	    
		grid.setVgap(25);

		Label labelTitle = new Label("Tower Defense");
		labelTitle.setTextFill(Color.ANTIQUEWHITE);
		
		// Put on cell (0,0), span 2 column, 1 row.
		grid.add(labelTitle, 0, 0);
		GridPane.setHalignment(labelTitle, HPos.CENTER);
		GridPane.setValignment(labelTitle, VPos.CENTER);
		GridPane.setHgrow(labelTitle, Priority.ALWAYS);
		GridPane.setVgrow(labelTitle, Priority.ALWAYS);
		labelTitle.setFont(Font.font(25));

		Label text1 = new Label("Bienvenue dans notre jeu");
		text1.setTextFill(Color.ANTIQUEWHITE);
		grid.add(text1, 0, 1);
		GridPane.setHalignment(text1, HPos.CENTER);
		GridPane.setValignment(text1, VPos.CENTER);
		GridPane.setHgrow(text1, Priority.ALWAYS);
		GridPane.setVgrow(text1, Priority.ALWAYS);
		text1.setFont(Font.font(17));

		Label text2 = new Label("Ce jeu a été réalisé par Sarobidy, Julia, Kevin et Maxime pour notre projet du S4");
		text2.setTextFill(Color.ANTIQUEWHITE);
		grid.add(text2, 0, 2);
		GridPane.setHalignment(text2, HPos.CENTER);
		GridPane.setValignment(text2, VPos.CENTER); 
		GridPane.setHgrow(text2, Priority.ALWAYS);
		GridPane.setVgrow(text2, Priority.ALWAYS);
		text2.setFont(Font.font(17));

		Label text3 = new Label("J'espère que le jeu vous plaira, nous avons adoré travailler dessus");
		text3.setTextFill(Color.ANTIQUEWHITE);
		grid.add(text3, 0, 3);
		GridPane.setHalignment(text3, HPos.CENTER);
		GridPane.setValignment(text3, VPos.CENTER);
		GridPane.setHgrow(text3, Priority.ALWAYS);
		GridPane.setVgrow(text3, Priority.ALWAYS);
		text3.setFont(Font.font(17));

		Label text4 = new Label("Amusez-vous bien");
		text4.setTextFill(Color.ANTIQUEWHITE);
		grid.add(text4, 0, 4);
		GridPane.setHalignment(text4, HPos.CENTER);
		GridPane.setValignment(text4, VPos.CENTER);
		GridPane.setHgrow(text4, Priority.ALWAYS);
		GridPane.setVgrow(text4, Priority.ALWAYS);
		text4.setFont(Font.font(17));

		//Bouton jouer
		Button b = new Button("JOUER");
		grid.add(b, 0, 5);
		GridPane.setHalignment(b, HPos.CENTER);
		GridPane.setValignment(b, VPos.BOTTOM);
		GridPane.setHgrow(b, Priority.ALWAYS);
		GridPane.setVgrow(b, Priority.ALWAYS);
		b.setFont(Font.font(17));
		b.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2));
		b.setBorder(new Border(borderStroke));
		
		//AnchorPane et Scene
		grid.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(grid,s.getHeight(),s.getWidth(),Color.rgb(40, 40, 40));		
		s.setScene(scene);

		s.show();
		s.centerOnScreen();

		//Action du button b
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// New window (Stage)
				Stage newWindow = new Stage();
				showNext(newWindow);
				s.close();
			}
		});
	}

	private void showNext(Stage s) {
		ApplicationLevel a = new ApplicationLevel();
		try {
			a.start(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

