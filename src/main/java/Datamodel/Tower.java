package Datamodel;

import Field.Level;

import java.util.ArrayList;
import java.util.List;
import Field.Level;
import Field.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Tower extends Hitbox{


	protected int cost;	//how much it costs to put this tower 
	protected double damage;	//the damage of the tower
	protected double range;	//its range of action
	protected double accuracy;	//the accuracy of the tower , 0<x<1
	protected double critical_rate;	//the critical rate of the tower, 0<x<1
	protected int level;		//level of the tower
	public final int LEVEL_MAX = 3;	//maximum level of the tower
	protected int speed;		//speed of the tower
	protected int cost_next_level;	//cost for the next level of the tower
	public Enemy target;	//the current target of the tower
	protected Level levelPlateau; //level of the game
	protected boolean placed=false; //indicate if the tower has been placed on the terrain
	protected int compteur = 0;
	public final int IDENTIFIANT;
	protected ImageView image;
	protected VBox tower_box;


	/*HEIGHT AND WIDTH PROPER TO A SPECIFIC TOWER CLASS*/

	private Tower(int width, int height, Level l, int i, int s, double a, double c, double d, int cout, double range){
		super(width, height);
		this.levelPlateau = l;
		this.accuracy = a;
		this.critical_rate = c;
		this.damage = d;
		this.speed = s;
		this.IDENTIFIANT = i;
		this.cost = cout;
		this.range = range;
	}

	public void upgrade(int i) {
		switch(i) {
		case 0 : 
			if(this.level != this.LEVEL_MAX) {
				this.damage += 10;
				this.speed --;
				this.cost_next_level += 250;
			}
			break;
		case 1 : 
			if(this.level != this.LEVEL_MAX) {
				this.damage += 10;
				this.speed --;
				this.cost_next_level += 250;
			}
			break;
		case 2 :
			if(this.level != this.LEVEL_MAX) {
				this.damage += 10;
				this.speed --;
				this.cost_next_level += 250;
			}
			break;
		default :  new Exception("upgrade tower");
		}
	}

	/**
	 * This method allows targeting and changes the current target
	 */
	public void targeting(){

		List<Enemy> level = this.levelPlateau.getEnemy();
		List<Enemy> tmp = new ArrayList<Enemy>() ;

		for( Enemy e : level) {

			if(this.isInRange(e)){
				//if the Enemy is in the range of the tower we add it to our ArrayList tmp
				tmp.add(e);
			}
		}
		//we’re going to look for the enemy who’s in range and who’s farthest on the way
		int max = 0; //stores the box number where the enemy is located farthest
		for( Enemy e : tmp) {

			if(e.getCurrentTile().getId() > max) {
				max = e.getCurrentTile().getId();

			}
		}

		//enemy who are farthest on the way
		List<Enemy> far = new ArrayList<Enemy>() ;  
		for( Enemy e : tmp) {

			if(e.getCurrentTile().getId() == max) {
				far.add(e);
			}
		}

		//we look at the enemy closest to the tower according to the Cartesian distance 
		double distance = this.range;
		for( Enemy e : far) {

			if(this.distance(e) < distance) {
				distance = this.distance(e);
			}
		}
		Enemy target=null;
		for( Enemy e : far) {
			if(this.distance(e) == distance) {
				target = e;
			}
		}

		this.target=target;
		return;
	}

	/**
	 * This method check if an object enemy within the range of the tower
	 * @param e an Enemy
	 * @return true if the enemy is within range of the tower
	 */
	public boolean isInRange(Enemy e) {

		double distance = this.distance(e);
		return (this.range >= distance);

	}

	/**
	 * This method allows the tower to send a projectile to the current target of the tower
	 */
	public Projectile tir() {

		if(target!=null && this.isInRange(target)) {
			double resDamage = this.shotDamage();
			Projectile p = new Projectile(3,3,resDamage,target,this.center);
			this.levelPlateau.addProjectile(p);
			return p;
			//the tower has a target in range, we send it a projectile
		}

		return null;

	}

	/**
	 * This metho calculate cartesians range between the tower and Enemy e
	 * @param e an Enemy
	 * @return the distance between the tower and an enemy
	 */
	public double distance(Enemy e) {
		double distanceX = (this.getCenter().getX() - e.getCenter().getX()) * (this.getCenter().getX() - e.getCenter().getX());
		double distanceY = (this.getCenter().getY() - e.getCenter().getY()) * (this.getCenter().getY() - e.getCenter().getY());
		return Math.sqrt(distanceX + distanceY);
	}

	/**
	 * This method calculate the accuracy of the tower
	 * @return true if the random numbers is < accuracy of the tower
	 */
	private boolean shot(){
		double random = Math.random();
		return (random < this.accuracy);
	}

	/**
	 * This method calculate the chance to make a critical hit
	 * @return true if the random numbers is < critical rate of the tower
	 */
	private boolean critical(){
		double random = Math.random();
		return (random < this.critical_rate);
	}

	/**
	 * This methode calculate the damage make by the tower
	 * @return the damage in order to create a new Projectile 
	 */
	private double shotDamage(){
		double d = 0;
		if(this.shot())
			d = this.critical() ? this.damage * 2 : this.damage;
		return d;
	}

	public boolean getPlaced(){

		return this.placed;

	}

	public void setPlaced(){

		this.placed=true;
	}

	public int getSpeed() {
		return this.speed;
	}

	//fabrique de tower : pattern Factory

	public static Tower fabriqueTower(int i, Level l) throws Exception {
		switch(i) {
		case 0 : return new Tower(60, 60, l, i, 100, 0.9, 0.5, 20, 100,150);
		case 1 : return new Tower(60, 60, l, i, 160, 0.5, 0.2, 80, 300,200);
		case 2 : return new Tower(60, 60, l, i, 140, 0.7, 0.3, 70, 600,300);
		default : throw new Exception("Impossible de creer une tour de type " + i );
		}
	}

	@Override
	public void action(Hitbox object) {
		// TODO Auto-generated method stub

	}

	public void setImage(ImageView i){ this.image=i; }
	public int getCompteur(){return this.compteur;}
	public Enemy getTarget() {return this.target;}
	public void setCompteur(int i){this.compteur=i;}
	public void resetTarget(){this.target=null;}
	public int getCost(){return this.cost;}

}