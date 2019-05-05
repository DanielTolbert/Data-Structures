import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import java.util.ArrayList;

public class Grapher extends PApplet {

    int x = 0;
    int screenWidth = 955;
    int screenHeight = 610;
    PImage image;
    PImage dotImage;
    boolean madeLocations = false;
    boolean notWhite = false;
    ArrayList<Dot> dots = new ArrayList<>(  );

    public void settings() {
        size( screenWidth, screenHeight);
    }

    public void setup() {
        setSize( screenWidth, screenHeight);
        surface.setResizable( true );
        image = loadImage( "WorldMap.png" );
        dotImage = loadImage("Dot.png");

        background( 255 );
        noStroke();
        fill( 102 );
    }

    public void keyTyped() {
        String locations = JOptionPane.showInputDialog( "How many locations?" );
        int dotsWanted = 0;
        if ( key == 'q') {
            try {
                dotsWanted = Integer.parseInt( locations );
            } catch (Exception e) {
                e.printStackTrace();
            }

            createDots(dotsWanted);

        }

    }

    public void draw() {
        image(image, 0, 0);//, width, height);
        for ( Dot d : dots ) {
            image(d.getImage(), d.getX(), d.getY());
//            System.out.println(d.getX());
        }
    }

    public void createDots(int dotsWanted) {
        for ( int i = 0; i < dotsWanted; i ++ ) {
            int x = 0;
            int y = 0;
            notWhite = false;
            while(!notWhite) {
                x = (int)(Math.random() * width);
                y = (int)(Math.random() * height);
                notWhite = ( image.get( x, y ) >> 1 ) != -1;
//                notWhite = false;
            }
//            image( dotImage, x, y );
            dots.add( new Dot( dotImage, x, y ) );
            System.out.println("============" +  ( image.get( x, y ) ) );
//            System.out.println(image.get( x, y ) >> 1 );
        }
    }

    public static void main(String[] args) {
        PApplet.main( "Grapher" );
    }
}
