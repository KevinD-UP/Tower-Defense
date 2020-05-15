package Datamodel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoadImage{


    /**
     * in order to get different instance of ImageView for each tower
     *name for the image name, the location not included. And the tower is used to set ImageView
     * @param name
     * @param t
     * @return
     */
   public static ImageView getView(String name, Hitbox t){
        //load image
        ImageView im = getImage("/Image/"+name);
        //set imageView according to tower size
        setImageView(im, t);
        return im;
    }

    //load the image file using the path
    private static ImageView getImage(String path){
        Image image = null;
        image = new Image(LoadImage.class.getResourceAsStream(path));
        //set image view
        ImageView imageView = new ImageView(image);
        return imageView;
    }

    public static Image getImageStat(String path){

        Image image = null;
        image = new Image(LoadImage.class.getResourceAsStream("/Image/"+path));
        return image;

    }

    //setUp the size of imageView
    private static void setImageView(ImageView im, Hitbox t){
        im.setLayoutX(t.getUpperLeft().getX());
        im.setLayoutY(t.getUpperLeft().getY());
        im.setFitWidth(t.getWidth());
        im.setFitHeight(t.getHeight());
        im.setPreserveRatio(true);
    }
}