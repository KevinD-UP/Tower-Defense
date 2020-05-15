package Datamodel;


import Field.Tile;

public class Point{

    private double x;
    private double y;
    
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    /**
    * This method check if a point belongs to the a tile
    * @param t a tile
    * @return true if Point t belongs to the Tile t
    */
    public boolean appartientTile(Tile t){
        double tmp = (Tile.side) / 2;
        Point a = new Point(Math.abs(t.center.getX()-tmp), Math.abs(t.center.getY()+tmp)); // point NORTH-WEST
        Point b = new Point(Math.abs(t.center.getX()+tmp), Math.abs(t.center.getY()+tmp)); // point North-east
        Point c = new Point(Math.abs(t.center.getX()-tmp), Math.abs(t.center.getY()-tmp)); // point South-west

        return ((this.x <= b.x && this.x >= a.x ) && (this.y <= a.y && this.y >= c.y));
    }

    public String toString() {
    	return (this.x +":"+ this.y);
    }

    
}