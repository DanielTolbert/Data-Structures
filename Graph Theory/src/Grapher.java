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
    int randomNumber = 0;
    boolean madeLocations = false;
    boolean notWhite = false;
    boolean allPossibleEdges = false;
    boolean randomActive = false;

    ArrayList<Dot> dots = new ArrayList<>(  );
    ArrayList<Edge> edges = new ArrayList<>(  );

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
        int dotsWanted = 0;
        if ( key == 'q') {
            String locations = JOptionPane.showInputDialog( "How many locations?" );
            try {
                dotsWanted = Integer.parseInt( locations );
            } catch (Exception e) {
                e.printStackTrace();
            }

            createDots(dotsWanted);

        }

        if ( key == 'a' ) {
            allPossibleEdges = true;
        }

        if ( key == 'r' ) {
            randomActive = true;
            String num = JOptionPane.showInputDialog( "How many random connections?" );
            randomNumber = Integer.parseInt( num );
        }

    }

    public void draw() {
        image(image, 0, 0);//, width, height);
        for ( Dot d : dots ) {
            image(d.getImage(), d.getX(), d.getY());
        }

        for ( Edge e : edges ) {
            stroke(102);
            line( e.getConnectingDotA().getX(), e.getConnectingDotA().getY(), e.getConnectingDotB().getX(), e.getConnectingDotB().getY() );
        }


        allPossibleEdges( allPossibleEdges );
        randomEdges( randomActive );
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

    private void randomEdges(boolean active) {
        stroke( 102 );
        if ( active ) {
            for ( int i = 0; i < randomNumber; i++ ) {
                Edge e = new Edge((dots.get( i )), dots.get( (dots.size() - 1)/ (i + 1) ));
                if ( !edges.contains( e ) ) {
                    edges.add( e );
                }
            }
        }
    }

    private void allPossibleEdges(boolean active) {
        if ( active ) {
            for ( Dot a : dots ) {
                for ( Dot b : dots) {
//                    stroke( 100 );
//                    line( a.getX(), a.getY(), b.getX(), b.getY() );
                    Edge e = new Edge( a, b );
                    if ( !edges.contains( e ) ) {
                        edges.add( e );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main( "Grapher" );
    }
}
