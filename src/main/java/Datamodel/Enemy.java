package Datamodel;

import Field.Level;
import Field.Tile;
import javafx.scene.image.ImageView;

public class Enemy extends Hitbox{

	private double hp; 			//Life point
	private double speed;			//Enemy's speed
	private Tile current;		//Current tile
	private int defence;		//Enemy's defence
	private ImageView image; //enemy's sprite
	public final Level LEVEL;
	public final int BUTIN; //piece for the player after killing this enemy
	public final int damage;//damage to the player when reaching the castle

	/*ENEMY'S CONSTRUCTOR*/
	private Enemy(double heal, double spd, Point ctr, int def, double w, double h, Level l, int dam){
		
		super(ctr, w, h);
		this.hp = heal;
		this.speed = spd;
		this.defence = def;
		this.LEVEL = l;
		this.BUTIN = (int)(this.hp/2);
		System.out.println(this.BUTIN);
		this.damage = dam;
	}

	/**
	 * Factory enemy
	 */
	public static Enemy fabriqueEnemy(int i, Level l) throws Exception {
		switch(i) {
		case 0 : 
			return new Enemy(25, 1.5, new Point((l.getIndiceX()*Tile.side)+Tile.side/2,(l.getIndiceY()*Tile.side)+Tile.side/2), 8, 40, 40,l, 1);	//mob1
			
		case 1 : 
			return new Enemy(50, 1.5, new Point((l.getIndiceX()*Tile.side)+Tile.side/2,(l.getIndiceY()*Tile.side)+Tile.side/2), 10, 40, 40, l, 1);	//shooter
		
		case 2 : 
			return new Enemy(150, 1.2, new Point((l.getIndiceX()*Tile.side)+Tile.side/2,(l.getIndiceY()*Tile.side)+Tile.side/2), 15, 40, 40, l, 2);	//boss
		
		case 3 : 
			return new Enemy(1500, 0.9, new Point((l.getIndiceX()*Tile.side)+Tile.side/2,(l.getIndiceY()*Tile.side)+Tile.side/2), 50, 40, 40, l, 4);	//big-boss
		
		case 4 : 
			return new Enemy(10000, 0.7, new Point((l.getIndiceX()*Tile.side)+Tile.side/2,(l.getIndiceY()*Tile.side)+Tile.side/2), 50, 40, 40, l, 5);	//big-boss final
		
		default : throw new Exception("Impossible de creer un ennemie de type " + i );
		}
	}
	
	public void setImage(ImageView image){
		this.image = image;
	}

	/**
	* This method manage the movement of an enemy.
	*/
	public void move() {
		if(this.isAlive()){
			double dX = 0;
			double dY = 0;
			this.current = this.LEVEL.getTile(this.center);
			//X variation
			if(current.getNext()[2]){//West
				dX = -speed;
			}if(current.getNext()[3]){//East
				dX = speed;
			}
			//Y variation
			if(current.getNext()[0]){//North
				dY = -speed;
			}if(current.getNext()[1]){//South
				dY = speed;
			}

			this.center.setX(this.center.getX()+dX);
			this.center.setY(this.center.getY()+dY);
			this.updatePosition();
		}
	}


	/**
	* This method check if an object enemy still have HP.
	* @return true if the enemy is still alive else false.
	*/
	public boolean isAlive(){
		return this.hp > 0;
	}

	public Tile getCurrentTile(){
		 return this.current;
	}

	public void setCurrentTile(Tile newCurrent){
		this.current = newCurrent;
	}

	public ImageView getImage(){return  this.image;}

	/**
	* This method calculate the damage receive by an enemy.
	*/
	public void hurt(double damage) {
		if (damage != 0)
			this.hp -= damage - this.defence;
	}

	@Override
	public void action(Hitbox object) {
		// TODO Auto-generated method stub
	}
}