package Datamodel;

import TowerDefenseVisual.ControlSubThread;

public abstract class Hitbox{

    protected Point a;//on top left
    protected Point b;//on top right
    protected Point c;//on the bottom right
    protected Point d;//on the bottom left
    protected Point center;//used when the object is moving
    protected double width;
    protected double height;
    protected ControlSubThread thread=null;
    protected boolean deleted=false;
    //create hitbox according to the center
    public Hitbox(Point center, double width, double height){

        this.width=width;
        this.height=height;
        this.center = center;
        this.a = new Point(center.getX()-width/2,center.getY()-height/2);
        this.b = new Point(a.getX()+width,a.getY());
        this.c = new Point(a.getX()+width,a.getY()+height);
        this.d = new Point(a.getX(), a.getY()+height);
    }
    //create hitbox in coordinate 0,0
    public Hitbox(double width, double height){
        this(new Point(0,0), width, height);
    }
    //set all the other points according to the center
    public void updatePosition(){


        this.a.setX(center.getX()-(width/2));
        this.a.setY(center.getY()-(height/2));
        this.b.setX(a.getX()+width);
        this.b.setY(a.getY());
        this.c.setX(a.getX()+width);
        this.c.setY(a.getY()+height);
        this.d.setX(a.getX());
        this.d.setY(a.getY()+height);


    }
    //test if the position is betwen min, max
    boolean belong_Interval(double min, double max, double position){
        if(min<=position && position<=max){
            return true;
        }
        return false;
    }
    //test if other hitbox share area with the current hitbox
    public boolean touching(Hitbox object){
        double minX = (a.getX() < b.getX()) ? a.getX() : b.getX() ;
        double maxX = (a.getX() >= b.getX()) ? a.getX() : b.getX() ;
        //if the x coordinate share the same zone on left or right side
        boolean collisionX = belong_Interval(minX, maxX, object.a.getX()) || belong_Interval(minX, maxX, object.b.getX());
        
        double minY = a.getY();
        double maxY = c.getY();
        //if the y coordinate share the same zone on top or bottom side
        boolean collisionY = belong_Interval(minY, maxY, object.a.getY()) || belong_Interval(minY, maxY, object.d.getY());
        //if they share a zone on x and y
        return collisionX && collisionY;
    }

    public abstract void action(Hitbox object);

    public void setCenter(Point p){
        this.center=p;
    }

    public Point getCenter(){
        return this.center;
    }

    public Point getUpperLeft(){
        return this.a;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    public ControlSubThread getThread(){ return this.thread; }

    public void setThread(ControlSubThread t){this.thread=t;}

    public boolean getDeleted(){return this.deleted;}

    public void setDeleted(){this.deleted=true;}



}