package Datamodel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Projectile extends Hitbox{
	private double damage;
	private Enemy target;
	private int speed = 10;
	private boolean arrived = false;

	private Rectangle shape;


	public Projectile(double width, double height,  double d, Enemy target,Point center){
		super(width, height);
		this.damage = d;
		this.target = target;
		this.center =new Point(center.getX(),center.getY());
		this.shape = new Rectangle(6, 6, Color.BLUE);

	}

	/**
	 * This method manage the movement of an object projectile.
	 */
	public void move(){

        this.center.setX(this.center.getX()+((target.getCenter().getX()-this.center.getX())/100)*speed);
        this.center.setY(this.center.getY()+((target.getCenter().getY()-this.center.getY())/100)*speed);
		this.updatePosition();

	}

	public void action(Hitbox object){
		if(object instanceof Enemy){
			//if Hitbox object is an Enemy so there is an action
			Enemy tmp = (Enemy) object;
			tmp.hurt(this.damage);
			this.arrived=true;
		}
	}

	public Rectangle getShape(){
		return this.shape;
	}

	public Enemy getTarget(){
		return this.target;
	}

	public boolean getArrived(){
		return this.arrived;
	}
}
