package Field;

import Datamodel.Point;
import javafx.scene.paint.Color;
import Datamodel.*;
import javafx.scene.shape.Rectangle;

public class Tile {

    private Color c;
    private static int total = 1;
    private int id;
    private boolean[] next = {false, false, false, false}; //NORTH, SOUTH, WEST, EAST
    public Point center;
    private boolean occupied=false;
    private Rectangle shape;

    public static final int side = 30;

    /*Constructor of tiles.*/
    public Tile(Point center, boolean path){
    	if(path){
            this.c = Color.LIGHTGRAY;
    		this.id = total;
    		total++;
    	}else{
            this.c = Color.GREEN; 
    		this.id = 0;
    	}
        this.center = center;
    }

    /**
     * Give the next tile
     * @return an array describing the next tiles
     */
    public boolean[] getNext(){
    	return next;
    }

    /**
     * Give the id of a tile
     * @return an int
     */
    public int getId(){
        return this.id;
    }

    public void setId(int i) {
    	this.id = i;
    }

    public boolean getOccupied(){return this.occupied;}

    public void setOccupied(){this.occupied=true;}

    public void setNext(int i, boolean b){
        this.next[i] = b;
    }
    
    public static void resetTotal() {
    	Tile.total = 1;
    }

    public void setShape(Rectangle shape){this.shape=shape;}

    public Color getColor(){return this.c;}



}
