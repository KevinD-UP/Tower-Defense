package Field;

import Datamodel.*;
import java.util.ArrayList;

public class Level {

    private int height;
    private int width;
    private Tile [][] layout;
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemy;
    private ArrayList<Projectile> projectiles;
    private int indiceX;	//index of the first path's Tile in layout [X][]
    private int indiceY;	//index of the first path's Tile in layout [][Y]
    private int pieces;
    private int lives;	//numbers of lives of players
    private int indiceCastleX;	//index of the last path's Tile in layout
    private int indiceCastleY;	//index of the last path's Tile in layout
    private Wave[] waves;

    public Level(int width, int height, int w){

        this.height = height;
        this.width = width;
        this.towers = new ArrayList<Tower>();
        this.enemy=new ArrayList<Enemy>();
        this.projectiles=new ArrayList<Projectile>();
        this.layout = new Tile[width][height];
        this.lives = 5;
        try {
			this.waves = Wave.createWaves(w);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    /*FUNCTION ADDING THE TOWER T TO THE TOWERS ARRAYLIST OF THE LEVEL
    * AND SETTING ITS POSITION TO THE DESIRED PLACE*/

    public boolean placeTower(Tower t, Point p){

        if(t.getCost()>this.pieces){

            return false;
        }

        /*MAKING SURE THAT THE TOWER IS NOT PLACED ON THE BOUNDARIES OF THE LEVEL*/
        if(p.getX()<=0 || p.getX()>=width*Tile.side || p.getY()<=0 || p.getY()>=height*Tile.side){
            return false;
        }
        /*MAKING SURE THAT THE CENTER OF THE TOWER IS NOT IN THE MIDDLE OF A TILE*/

        if(p.getY()%15 != 0 || p.getX()%15 != 0){
            return false;
        }
        /*MAKING SURE THAT THE TOWER IS NOT IN THE PATH*/
        
        Point a = new Point(p.getX() - (t.getWidth()/4), p.getY() + (t.getHeight()/4) ); 
        Point b = new Point(p.getX() + (t.getWidth()/4), p.getY() + (t.getHeight()/4) ); 
        Point c = new Point(p.getX() - (t.getWidth()/4), p.getY() - (t.getHeight()/4) ); 
        Point d = new Point(p.getX() + (t.getWidth()/4), p.getY() - (t.getHeight()/4) );

        Tile tmpA = getTile(a);
        Tile tmpB = getTile(b);
        Tile tmpC = getTile(c);
        Tile tmpD =getTile(d);

       if(tmpA==null || tmpB==null || tmpC==null ||tmpD==null){
            return false;
        }

        if( tmpA.getId() != 0 || tmpB.getId() != 0 || tmpC.getId() != 0 || tmpD.getId() != 0){

            return false;
        }

        /*MAKING SURE THAT THERE ISNT ALREADY A TOWER THERE*/

        if(tmpA.getOccupied() ||tmpB.getOccupied() ||tmpC.getOccupied() ||tmpD.getOccupied()){

            return false;
        }

        /*UPDATING T POSITION AND ADDING IT TO THE TOWERS ARRAYLIST */
        t.setCenter(p);
        t.updatePosition();
        if(!towers.contains(t)){
            towers.add(t);
        }
        tmpA.setOccupied();
        tmpB.setOccupied();
        tmpC.setOccupied();
        tmpD.setOccupied();
        this.pieces=this.pieces-t.getCost();
        return true;


    }

    public void addEnemy(Enemy e){
        this.enemy.add(e);
        e.setCurrentTile(layout[indiceX][indiceY]);
    }


    public void addProjectile(Projectile p){this.projectiles.add(p);}


    public Tile getTile(Point p){
        for(int i=0;i<this.layout.length;i++){
            for(int j=0;j<this.layout[i].length;j++){
                if(p.appartientTile(this.layout[i][j])){
                    return this.layout[i][j];
                }

            }
        }
        return null;
    }

    /* INITIALIZATION OF THE TILES OH PATH (mise a jour des next de chaque Tiles chemin)*/

    private void path(){
    	int n = numberTilePath();
    	boolean[] verif = new boolean[n+1];
    	int compteur = 1;
    	int i = this.indiceX;
    	int j = this.indiceY;
    	verif[this.layout[i][j].getId()] = true;
    	while(compteur <= n && i != this.width && j != this.height){
    		//North
    		if(j-1 >= 0 && this.layout[i][j-1].getId() > 0 && verif[this.layout[i][j-1].getId()] == false ){
    			compteur ++;
    			verif[this.layout[i][j-1].getId()] = true;
    			this.layout[i][j].setNext(0,true);
    			
    			j = j - 1;
    		}
    		//West
    		else if(i-1 >= 0 && this.layout[i-1][j].getId() > 0 && verif[this.layout[i-1][j].getId()] == false  ){
    			compteur ++;
    			verif[this.layout[i-1][j].getId()] = true;
    			this.layout[i][j].setNext(2,true);
    			i = i - 1;
    		}
    		//South
    		else if(j+1 < this.height && this.layout[i][j+1].getId() > 0 && verif[this.layout[i][j+1].getId()] == false){
    			compteur ++;
    			verif[this.layout[i][j+1].getId()] = true;
    			this.layout[i][j].setNext(1,true);
    			j = j + 1;
    		}
    		//East
    		else if(i+1 < this.width && this.layout[i+1][j].getId() > 0 && verif[this.layout[i+1][j].getId()] == false  ){
    			compteur ++;
    			verif[this.layout[i+1][j].getId()] = true;
    			this.layout[i][j].setNext(3,true);
    			i = i + 1;
    		}
    		else {
    			compteur++;
    		}
    	}
    	
    	// INITIALISATION DES INDICES CASTLES
    	
    	for(int k=0;k<this.width;k++){
        	for(int l=0;l<this.height;l++){
        		if(this.layout[k][l].getId() == n){
        			this.indiceCastleX = k;	
        			this.indiceCastleY = l;
        		}
        	}
        }

    	compteur = 1;
    	i = this.indiceX;
    	j = this.indiceY;
    	while(compteur != n && i<30  && j<20) {
    		this.layout[i][j].setId(compteur);
    		if(this.layout[i][j].getNext()[0]) {
    			j = j - 1;
    			compteur++;
    		}
    		else if(this.layout[i][j].getNext()[1]) {
    			j = j + 1;
    			compteur++;
    		}
    		else if(this.layout[i][j].getNext()[2]) {
    			i = i - 1;
    			compteur++;
    		}
    		else {
    			i = i + 1;
    			compteur++;
    		}
    	}

    }

    private int numberTilePath(){
    	int res = 0;
    	for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
            	if(this.layout[i][j].getId() > res){
            		res = this.layout[i][j].getId();
            	}
            }
        }
    	
        return res;
    }

    /*REMOVERS*/

    public boolean removeTower(Tower t){
        if(this.towers.contains(t)){
            this.towers.remove(t);
            return true;
        }
        return false;
    }

    public boolean removeEnemy(Enemy e){
        if(this.enemy.contains(e)){
            this.enemy.remove(e);
            return true;
        }
        return false;
    }
    
    public void initLevel(int p) {
    	
    	this.pieces = p;
        for(int k=0;k<this.width;k++){
        	for(int l=0;l<this.height;l++){
        		if(this.layout[k][l].getId() == 1){
        			this.indiceX = k;
        			this.indiceY = l;
        		}
        	}
        }
        
        this.path();
        Tile.resetTotal();
    }
    

    private boolean dead() {
    	return (this.lives <= 0);
    }

    /**
     * check if there is an enemy on the castle and updated live points
     * @return boolean
     */

    public boolean gameOver() {
    	if(this.enemy.isEmpty()) {
    		return false;
    	}
    	int idCastle = this.layout[indiceCastleX][indiceCastleY].getId();
    	for (Enemy e : this.enemy) {

    		if(e.getCurrentTile().getId() == idCastle) {

    			this.lives -= e.damage;
    			e.setDeleted();
    		}
    	}
    	return this.dead();
    }

    /*GETTERS*/

    public ArrayList<Tower> getTowers() { return this.towers; }
    public ArrayList<Projectile> getProjectiles(){return this.projectiles; }
    public ArrayList<Enemy> getEnemy(){ return this.enemy; }
    public int getWidth(){ return this.width; }
    public int getHeight(){ return this.height; }
    public Tile[][] getLayout(){ return this.layout; }
    public int getPiece() { return this.pieces; }
    public void setPiece(int p) { this.pieces = p; }
    public void setLayout(Tile t, int x, int y) { this.layout[x][y] = t; }
    public int getLives() { return this.lives; }
    public void setLives(int i) {this.lives -= i; }
    public int getIndiceCastleX() {	return this.indiceCastleX;}
    public int getIndiceCastleY() {	return this.indiceCastleY;}
    public Wave[] getWaves() { return this.waves;}
    public int getIndiceX() { return this.indiceX;}
    public int getIndiceY() { return this.indiceY;}
    


}
