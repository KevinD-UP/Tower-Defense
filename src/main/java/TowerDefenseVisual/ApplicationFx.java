package TowerDefenseVisual;

import Field.*;
import Datamodel.*;
import Sauvegarde.Save;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ApplicationFx extends Application {

	/*FIELDS TO MANAGE TIME SIMULATION*/

	private static int FPS=60;
	private static int UPS=60;
	private static boolean RENDER_TIME=true;

	/*FIELDS TO MANAGE IMPORTANT NOD OUTSIE OF THE START FUNCTION*/

	private Timeline time;
	private Level l;
	private TilePane level_layout;
	private HBox shopPane;
	private Pane level_container;
	private VBox tower_box1;
	private VBox tower_box2;
	private VBox tower_box3;
	private Group root;
	private static long gameTime;
	private Wave[] enemyWave;
	private int nbWaves = 0; 	//numbers of waves
	private final int idLevel;
	private boolean defaite = false;
	private int countdown=0;
	private Text argent;
	private boolean finWaves = false;
	private boolean victoire = false;
	private Rectangle[] lives;
	private HBox vies;
	private int nblives;

	public ApplicationFx(Level le, int i) {
		this.l = le;
		this.idLevel = i;
		this.nblives = this.l.getLives();
		this.lives = new Rectangle[this.l.getLives()];
		for(int j=0; j<this.lives.length; j++)
		{
			Rectangle r = new Rectangle(40, 40, Color.CRIMSON);
			this.lives[j] = r;
		}
	}

	/**
	 * This Runnable destined to be runned on the javaFX Plateform Thread updated every graphical
	 * element position.
	 */
	public Runnable Updater=new Runnable() {
		@Override
		public void run() {
			/*UPDATING ENEMIES POSITION*/

			for(Enemy e: l.getEnemy()){

				e.getImage().setLayoutX(e.getCenter().getX()-(e.getWidth()/2));
				e.getImage().setLayoutY(e.getCenter().getY()-(e.getHeight()/2));
			}

			/*UPDATING PROJECTILES*/

			ArrayList<Projectile> tmp=new ArrayList<>(l.getProjectiles());

			Iterator itp=tmp.iterator();

			while(itp.hasNext()){

				Projectile p=(Projectile) itp.next();

				if(!p.getDeleted() && !p.getArrived()){



					/*REFRESHING PROJECTILES ON SCREEN*/

					if(!level_container.getChildren().contains(p.getShape())){

						level_container.getChildren().add(p.getShape());

						p.getShape().setLayoutX(p.getCenter().getX()+(p.getWidth()/2));
						p.getShape().setLayoutY(p.getCenter().getY()-(p.getHeight()/2));

					}

					/*UPDATING PROJECTILES POSITION*/

					p.getShape().setLayoutX(p.getCenter().getX()+(p.getWidth()/2));
					p.getShape().setLayoutY(p.getCenter().getY()-(p.getHeight()/2));

				}
			}

			argent.setText("pieces d'ors : "+l.getPiece());
			
			miseAjourLives();
			
			

		}
	};

	/**
	 * This method updates the datas of the enemies, tower and projectiles within the level
	 */
	public void UpdateModel(){


		/*FOR EACH ENEMY STORED IN THE LEVEL*/

		ArrayList<Enemy> tmpe=new ArrayList<>(l.getEnemy());

		Iterator ite=tmpe.iterator();

		while(ite.hasNext()){

			Enemy e=(Enemy)ite.next();

			if(!e.getDeleted()){

				/*STARTING THE THREAD*/
				e.getThread().start();
			}
			else
			{
				l.removeEnemy(e);
				Runnable runnableA=new Runnable() {
					@Override
					public void run() {
						level_container.getChildren().remove(e.getImage());

					}
				};

				/*PUSHIN THE GRAPHIC PART OF THE TASK ON THE JAVAFX APPLICATION THREAD*/

				Platform.runLater(runnableA);
			}
		}

		/*FOR EACH TOWER STORED IN THE LEVEL*/
		
		ArrayList<Tower> tow = new ArrayList<>(l.getTowers());

		Iterator itTo= tow.iterator();
		int compteur = 0;
		while(itTo.hasNext() && compteur != tow.size() ){

			Tower t = (Tower)itTo.next();
			t.getThread().start();
		}

		/*FOR EACH PROJECTILE STORED */

		ArrayList<Projectile> tmpp=new ArrayList<>(l.getProjectiles());

		Iterator itp=tmpp.iterator();

		while(itp.hasNext()){

			Projectile p=(Projectile)itp.next();

			if(!p.getDeleted()){

				/*STARTING THE THREAD*/

				p.getThread().start();

			}
		}
	}


	/**
	 * managing game over and victory
	 * @param st Stage
	 */

	private void gameOver(Stage st) {

			VBox root = new VBox();


			Label secondLabel = new Label("Vous avez perdu");
			secondLabel.setTextFill(Color.ANTIQUEWHITE);

			StackPane secondaryLayout = new StackPane();
			secondaryLayout.getChildren().add(secondLabel);
			root.getChildren().add(secondaryLayout);

			HBox root2 = new HBox();

			root2.setSpacing(30);
			root2.setPadding(new Insets(10,10, 25,25));

			Button again = new Button("recommencer");
			again.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2));
			again.setBorder(new Border(borderStroke));
			root2.getChildren().add(again);

			Button map = new Button("retour aux niveaux");
			map.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
			map.setBorder(new Border(borderStroke));
			root2.getChildren().add(map);

			root.getChildren().add(root2);
			root.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
			Scene secondScene = new Scene(root, 300, 100);

			Stage newWindow = new Stage();

			newWindow.setTitle("Defaite");
			newWindow.setScene(secondScene);

			// Specifies the modality for new window.
			newWindow.initModality(Modality.WINDOW_MODAL);

			// Specifies the owner Window (parent) for new window
			newWindow.initOwner(st);

			// Set position of second window, related to primary window.
			newWindow.setX(st.getX() + 200);
			newWindow.setY(st.getY() + 100);

			newWindow.show();

			//action button again 
			again.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Stage stage = new Stage();
					ApplicationLevel l = new ApplicationLevel(); 
					ApplicationFx a = new ApplicationFx(l.levels[idLevel], idLevel);
					try {
						stop();
						a.start(stage);
						newWindow.close();
						st.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			//action button map

			map.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Stage stage = new Stage();
					ApplicationLevel a = new ApplicationLevel();
					try {
						a.start(stage);
						newWindow.close();
						st.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
		return;
	}

	private boolean gagner() {
		return (this.finWaves && this.l.getEnemy().size()== 0);
	}

	private void victoire(Stage s){
		
		//on met a jour  les autorisations de applicationLevel
		if(idLevel < ApplicationLevel.autorisations.length -1) {
			ApplicationLevel.autorisations[idLevel + 1] = true;
		}
		if(idLevel == 5) {
			ApplicationLevel.autorisations[0] = true;
		}

		//on met a jour le fichier json
		Save.miseAjour(ApplicationLevel.autorisations);
		
		VBox root = new VBox();

		root.setSpacing(10);

		Label secondLabel = new Label("Bravo vous avez gagne");
		secondLabel.setTextFill(Color.ANTIQUEWHITE);

		StackPane secondaryLayout = new StackPane();
		secondaryLayout.getChildren().add(secondLabel);
		root.getChildren().add(secondaryLayout);

		Button map = new Button("retour aux niveaux");
		map.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,2,2));
		map.setBorder(new Border(borderStroke));

		StackPane pane = new StackPane();
		pane.getChildren().add(map);
		root.getChildren().add(pane);

		root.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene secondScene = new Scene(root, 300, 100);


		Stage newWindow = new Stage();

		newWindow.setTitle("Victoire");
		newWindow.setScene(secondScene);

		// Specifies the modality for new window.
		newWindow.initModality(Modality.WINDOW_MODAL);

		// Specifies the owner Window (parent) for new window
		newWindow.initOwner(s);

		// Set position of second window, related to primary window.
		newWindow.setX(s.getX() + 200);
		newWindow.setY(s.getY() + 100);

		newWindow.show();

		//action button map

		map.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Stage stage = new Stage();
				ApplicationLevel a = new ApplicationLevel();
				try {
					a.start(stage);
					newWindow.close();
					s.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		return;
	}


	/**
	 * Runnable Interface for the main time managing Thread
	 */
	ControlSubThread task=new ControlSubThread(1) { /*CREATING A NEW THREAD TO MANAGE THIS TOWER*/

		@Override
		public void run(){

			long initialTime = System.nanoTime();

			/*UPS MANAGE THE NUMBER OF TICKS, FPS THE NUMBER OF FRAMES*/

			final double timeU = 1000000000 / UPS;
			final double timeF = 1000000000 / FPS;
			double deltaU = 0, deltaF = 0;
			int frames = 0, ticks = 0;
			long timer = System.currentTimeMillis();
			boolean condition = true;
			
			while (condition) {
				long currentTime = System.nanoTime();
				deltaU += (currentTime - initialTime) / timeU;
				deltaF += (currentTime - initialTime) / timeF;
				initialTime = currentTime;

				/*UPDATING THE GAME STATUS AT EVERY TICK*/

				if (deltaU >= 1) {

					if(countdown>1){

						UpdateModel();
					}

					ticks++;
					deltaU--;
				}

				/*RENDERING AT EVERY FRAME*/

				if (deltaF >= 1) {

					Platform.runLater(Updater);
					frames++;
					deltaF--;
				}

				/*UPDATING FRAMES TICKS AND TIMER IF A SECOND HAS PASSED*/

				if (System.currentTimeMillis() - timer > 1000) {
					countdown++;
					condition = (l.gameOver() == false && gagner()==false) ;
					/*PRINTING THE FPS AND UPS*/

					if (RENDER_TIME) {
						System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
						System.out.println(countdown);
						l.setPiece(l.getPiece()+5);
						if(finWaves == false) {
							enemyWave[nbWaves].setTotalSecond(countdown);
							if(enemyWave[nbWaves].isShouldCreate()){
								try {
									createEnemy(enemyWave[nbWaves].enemy());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							if(enemyWave[nbWaves].getEnd()) {
								nbWaves ++;
							}
							if(nbWaves == enemyWave.length)
							{
								finWaves = true;
							}
						}

					}

					frames = 0;
					ticks = 0;
					timer += 1000;
				}
			}
			if(gagner()) {
				victoire = true;
			}
			else {
				miseAjourLives();
				defaite = true;
			}

			this.interrupt();
		}
	};


	/* CREATE A LEVEL WITH WITH ROWS AND HEIGHT COLUMNS OF TILES*/

	private void createLevel(int width, int height){

		level_layout=new TilePane();
		level_layout.setOrientation(Orientation.VERTICAL);
		level_layout.setPrefRows(height);
		enemyWave = this.l.getWaves();
	}

	/* FILLING THE LEVEL_LAYTOUT TILEPANE WITH THE REQUIRED
	 * NUMBER OF TILES*/

	private void loadLevelLayout(){

		/*RECTANGLE USE TO REPRESENT A TILE*/

		Rectangle rectangle;

		/*FILLING THE LAYOUT ACCORDING TO THE LEVEL LAYOUT*/

		for(int i=0;i<l.getWidth();i++){

			for(int j=0;j<l.getHeight();j++){

				rectangle=new Rectangle();
				rectangle.setWidth(Tile.side-1);
				rectangle.setHeight(Tile.side-1);
				rectangle.setFill(l.getLayout()[i][j].getColor());
				l.getLayout()[i][j].setShape(rectangle);

				/*ADDING BORDERS*/

				rectangle.setStroke(Color.DARKGREEN);
				rectangle.setStrokeWidth(1);

				/*COLOR FOR CASTLE*/
				int x = this.l.getIndiceCastleX();
				int y = this.l.getIndiceCastleY();
				if(i == x && j == y) {
					rectangle.setFill(Color.RED);
				}

				/*ADDING TILE TO THE LEVEL LAYOUT*/

				level_layout.getChildren().add(rectangle);
			}
		}
	}

	/** CREATE A TOWER OBJECT AND ADD IT TO THE LEVEL, CREATE THE ASSOCIATED VISUAL NOD
	 * AND ITS EVENT LISTENER*/

	private ImageView createTower(int i, VBox tower_box) throws Exception{

		/*CREATING THE TOWER OBJECT*/

		Tower t= Tower.fabriqueTower(i, l);

		/*CREATIGN THE ASSOCIATED JAVAFX NODE*/
		//Load Image and set the size according to tower t

		String arg = Integer.toString(i);
		ImageView tour = LoadImage.getView("tower"+arg+".png", t);
		t.setImage(tour);
		tour.setCursor(Cursor.HAND);

		/*EVENT LISTENER ON MOUSE RELEASED*/

		tour.setOnMouseReleased((e) -> {

			/*MAKING SURE THAT THE TOWER WASNT ALREADY PLACED*/

			if(!t.getPlaced()){

				/*REMOVING NODE FROM ROOT BECAUSE IT S NOT BEING MOVED ANYMORE*/

				root.getChildren().remove(tour);

				/*IF PLACING TOWER ON THE LEVEL FAILS, SETTING IT BACK INTO THE SHOP PANE*/


				if(!l.placeTower(t,new Point(tour.getLayoutX()+(t.getWidth()/2), tour.getLayoutY()+(t.getHeight()/2)))){
					tour.setLayoutX(t.getUpperLeft().getX());
					tour.setLayoutY(t.getUpperLeft().getY());
					tower_box.getChildren().add(tour);
				}

				/*ELSE DEFINITIVELY PLACING IT ON THE LEVEL*/

				else{

					level_container.getChildren().add(tour);
					t.setPlaced();
					try {
						Random rand=new Random();
						int randomNum = rand.nextInt((2 - 0) + 1) + 0;
						ImageView tourn = createTower(randomNum,tower_box);
						Runnable r=new Runnable() {
							@Override
							public void run() {
								tower_box.getChildren().add(tourn);
							}
						};
						Platform.runLater(r);
					} catch (Exception ex) {
						ex.printStackTrace();
					}




				}
			}

		});

		/*EVEN LISTENER ON MOUSE PRESSED, IS NECESSARY TO MAKE SURE THE NODE CAN BE DRAGGED ON
		 * THE FIRST MOUSE PRESS*/

		tour.setOnMousePressed((e) -> {

			/*MAKING SURE THAT THE TOWER WASN T ALREADY PLACED*/

			if(!t.getPlaced()){

				/*PREPARING THE NODE SO IT CAN BE DRAGGED PROPERLY
				 * (CHANGING PARENT NODE AND UPDATING COORDINATES)*/

				if(tower_box.getChildren().contains(tour)){
					tower_box.getChildren().remove(tour);
					root.getChildren().add(tour);
					tour.setLayoutY((l.getHeight()*Tile.side));
					tour.setLayoutX(tour.getX());
				}

			}

		});

		/*EVENT LISTENER ON MOUSE DRAGGED*/

		tour.setOnMouseDragged((e) -> {

			/*MAKING SURE THE TOWER WASN T ALREADY PLACED*/

			if(!t.getPlaced()){


				/*MOVING THE X OF THE NODE ONLY IF THE MOUSE HAS MOVED*/

				if(Math.abs(e.getSceneX()-tour.getLayoutX()-(t.getWidth()/2))>Tile.side) {


					/*MAKING SURE THE X OF THE NODE ONLY CHANGE TILE BY TILE*/

					if ((e.getSceneX() - tour.getLayoutX() -(t.getWidth()/2)) > 0 && tour.getLayoutX() < (l.getWidth() - 2) * Tile.side) {

						tour.setLayoutX(tour.getLayoutX() + Tile.side);
					}

					if ((e.getSceneX() - tour.getLayoutX() -(t.getWidth()/2))< 0 && tour.getLayoutX() >0) {
						tour.setLayoutX(tour.getLayoutX() - Tile.side);

					}
				}

				/*MOVING THE Y OF THE NODE ONLY IF THE MOUSE HAS MOVED*/

				if(Math.abs(e.getSceneY()-tour.getLayoutY()-(t.getHeight()/2))>Tile.side) {

					/*MAKING SURE THE Y OF THE NOD EONLY CHANGE TILE BY TILE*/

					if ((e.getSceneY() - tour.getLayoutY() -(t.getHeight()/2)) > 0 && tour.getLayoutY() < (l.getHeight() - 2) * Tile.side) {
						tour.setLayoutY(tour.getLayoutY() + Tile.side);
					}

					if ((e.getSceneY() - tour.getLayoutY() -(t.getHeight()/2))< 0 && tour.getLayoutY() >0) {
						tour.setLayoutY(tour.getLayoutY() - Tile.side);

					}

				}




			}


		});

		ControlSubThread tower=new ControlSubThread(1) { /*CREATING A NEW THREAD TO MANAGE THIS TOWER*/

			@Override
			public void run(){

				/*SAFE THREAD MANAGEMENT*/

				running.set(true);
				while (running.get()) {


					/*MAKING SURE THE TOWER HAS A TARGET*/

					if(t.getTarget()==null || t.isInRange(t.getTarget()) == false){

						t.targeting();

					}

					else{

						/*RESTING THE TARGET IF THE PREVIOUS TARGET DIED*/

						if(t.getTarget().getDeleted()){

							t.resetTarget();

						}
					}


					/*MANAGING THE SHOOTING SPEED*/

					if (t.getCompteur() == 0) {

						t.setCompteur(t.getSpeed());

						if(t.getTarget()!=null){

							Projectile p = t.tir();

							/*CREATING THE PROJECTILE GRAPHIC COUNTERPART IN A TASK DESTINED TO BE RUN
							 * ON THE JAVAFX APPLICATION THREAD*/


							ControlSubThread projectile=new ControlSubThread(1){ /*CREATING A NEW THREAD TO MANAGE THIS PROJECTILE*/

								public void run(){

									/*SAFE THREAD MANAGEMENT*/

									running.set(true);
									while (running.get()) {

										/*IF THE PROJECTILE HAD REACHED DESTINATION, IT IS REMOVED FROM THE LEVEL*/

										/*IF THE PROJECTILE HAS REACHED IT'S TARGET*/

										if(p.getTarget().touching(p)){

											p.action(p.getTarget());
											p.setDeleted();

											/*REMOVING THE PROJECTILE FROM THE JAVAFX SCENE USING A RUNNABLE DESTINED TO BE RUN ON THE JAVAFX
											 * APPLICATION THREAD*/

											Runnable runnableC=new Runnable() {
												@Override
												public void run() {

													level_container.getChildren().remove(p.getShape());

												}
											};



											Platform.runLater(runnableC);
											if(this.isRunning()){
												this.interrupt();
											}


										}

										/*MOVING THE PROJECTILE*/

										else{
											p.move();
										}

										if(this.isRunning()){
											this.interrupt();
										}


									}
								}
							};

							if(p!=null){
								p.setThread(projectile);
								t.setCompteur(t.getSpeed());
							}





						}


					}

					else {

						t.setCompteur(t.getCompteur() - 1);
					}


					/*INTERRUPTING THE THREAD WHEN WE ARE DONE*/

					if(this.isRunning()){
						this.interrupt();
					}
				}


			}
		};

		t.setThread(tower);

		return tour;

	}
	private void createEnemy(int TypeEnemy) throws Exception {

		int i = TypeEnemy;
		Enemy e = Enemy.fabriqueEnemy(TypeEnemy, this.l);
		String s = Integer.toString(i);
		ImageView viewEnemy = LoadImage.getView("enemi"+s+".png", e);
		e.setImage(viewEnemy);
		l.addEnemy(e);

		// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
		Platform.runLater(
				() -> {
					level_container.getChildren().add(viewEnemy);
				}
				);


		ControlSubThread enemy=new ControlSubThread(1){ /*CREATING A NEW THREAD TO MANAGE THIS ENEMY */

			@Override
			public void run(){

				/*SAFE THREAD MANAGEMENT*/

				running.set(true);
				while (running.get()) {

					/*IF THE ENEMY DIED, IT IS REMOVED BOTH FROM THE LEVEL AND THE GRAPHICS ( IT NEEDS
					 * TO BE DONE HERE AS WE WOULD NOT BE ABLE TO ACCESS THE ENEMY IN THE UPDATER RUNNABLE IF IT WAS ALREADY REMOVED HERE*/

					if(!e.isAlive()){



						/*REMOVING THE ENEMY FROM THE LEVEL*/

						e.setDeleted();
						l.setPiece(l.getPiece()+50+e.BUTIN);
						if(this.isRunning()){

							this.interrupt();
						}

					}

					/*IF THE ENEMY IS STILL ALIVE, IT MOVES*/

					else{
						e.move();
					}

					/*INTERRUPTING THE THREAD ONCE WE ARE FINISHED*/

					if(this.isRunning()){

						this.interrupt();
					}

				}
			}
		};
		e.setThread(enemy);
	}

	/**
	 * Updates lives display
	 */
	public void miseAjourLives() {
		int lives = this.l.getLives();
		if(lives != this.nblives) {
			for(int i=0; i<this.nblives;i++) {
				if(lives <= i) {
					this.lives[i].setFill(Color.WHITE);
				}
			}
			this.nblives = lives;
		}
		return;
	}

	/*BUILDING THE JAVAFX APP*/

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*SETTING UP THE PRIMARY STAGE AND SCENE*/

		primaryStage.setTitle("Tower Defense");
		primaryStage.setWidth( (Tile.side*l.getWidth())+15);
		primaryStage.setHeight((l.getHeight()*Tile.side)+179);
		primaryStage.setOnCloseRequest(new EventHandler <WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
		primaryStage.setFullScreen(true);
		root = new Group();
		Scene scene = new Scene(root, (Tile.side*l.getWidth()), (l.getHeight()*Tile.side)+140, Color.WHITE);
		primaryStage.setScene(scene);

		/*V BOX STRUCTURATE THE INTERFACE*/

		VBox contentBox=new VBox();
		root.getChildren().add(contentBox);

		/*CREATING AND LOADING A LEVEL*/

		createLevel(30,20);
		loadLevelLayout();

		/*LEVEL CONTAINER STRUCTURATE THE LEVEL PART OF THE INTERFACE*/

		level_container=new Pane();
		level_container.getChildren().add(level_layout);
		contentBox.getChildren().add(level_container);

		/*SHOP PANE STRUCTURATE THE SHOP PART OF THE LEVEL*/

		BackgroundImage backgroundimageA = new BackgroundImage(LoadImage.getImageStat("shop_box.png"),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);


		shopPane=new HBox();
		shopPane.setBackground(new Background(backgroundimageA));
		shopPane.setPrefSize(level_container.getWidth(),140);
		shopPane.setSpacing(30);
		shopPane.setPadding(new Insets(20,0,0,10));
		contentBox.getChildren().add(shopPane);

		BackgroundImage backgroundimageB = new BackgroundImage(LoadImage.getImageStat("tower_box.png"),
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);

		tower_box1=new VBox();
		tower_box1.setBackground(new Background(backgroundimageB));
		tower_box1.setPrefSize(70,100);
		tower_box1.setPadding(new Insets(20,0,0,5));

		tower_box2=new VBox();
		tower_box2.setBackground(new Background(backgroundimageB));
		tower_box2.setPrefSize(70,100);
		tower_box2.setPadding(new Insets(20,0,0,5));

		tower_box3=new VBox();
		tower_box3.setBackground(new Background(backgroundimageB));
		tower_box3.setPrefSize(70,100);
		tower_box3.setPadding(new Insets(20,0,0,5));

		shopPane.getChildren().add(tower_box1);
		shopPane.getChildren().add(tower_box2);
		shopPane.getChildren().add(tower_box3);

		/* GOLD OF PLAYER */


		argent = new Text("pieces d'ors : "+l.getPiece());
		argent.setFill(Color.ANTIQUEWHITE);
		argent.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		shopPane.getChildren().add(argent);

		//contentBox.getChildren().add(shopPane);

		/*LIVES PLAYER*/

		Pane coeur = new Pane();

		VBox root2 = new VBox();
		root2.setSpacing(10);

		vies = new HBox();
		vies.setSpacing(10);

		Text lives = new Text("vies restantes : ");
		lives.setFill(Color.ANTIQUEWHITE);
		lives.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		root2.getChildren().add(lives);

		for(int i=0; i<this.lives.length; i++) {
			vies.getChildren().add(this.lives[i]);
		}

		root2.getChildren().add(vies);

		coeur.getChildren().add(root2);

		shopPane.getChildren().add(coeur);

		/*ADDING A TOWER*/

		ImageView tour0 = createTower(0,tower_box1);
		ImageView tour1 = createTower(1,tower_box2);
		ImageView tour2 = createTower(2,tower_box3);
		tower_box1.getChildren().add(tour0);
		tower_box2.getChildren().add(tour1);
		tower_box3.getChildren().add(tour2);

		task.start();
		primaryStage.show();
		close(primaryStage);
	}

	/**
	 *
	 * @param s Stage
	 */
	public void close(Stage s) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
			if(defaite) {
				System.out.println("termine en mode defaite");
				gameOver(s);
				this.time.stop();
			}
			if(gagner()) {
				System.out.println("termine en mode victoire");
				victoire(s);
				this.time.stop();
			}

		}));
		this.time = timeline;
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
}
