import com.sun.source.tree.Tree;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Grapher extends PApplet {

    int x = 0;
    int screenWidth = 955;
    int screenHeight = 610;
    PImage image;
    PImage dotImage;
    PImage visitedDotImage;
    int randomNumber = 0;
    boolean madeLocations = false;
    boolean notWhite = false;
    boolean allPossibleEdges = false;
    boolean randomActive = false;
    boolean cont = false;

    ArrayList<Dot> dots = new ArrayList<>(  );
    ArrayList<Edge> edges = new ArrayList<>(  );
    HashSet<String> cycledEdges = new HashSet<>(  );
    HashSet<Edge> cycledDots = new HashSet<>(  );
    Scanner scanner = new Scanner( System.in );

    public void settings() {
        size( screenWidth, screenHeight);
    }

    public void setup() {
        setSize( screenWidth, screenHeight);
        surface.setResizable( true );
        image = loadImage( "WorldMap.png" );
        dotImage = loadImage("Dot.png");
        visitedDotImage = loadImage("Dotvisited.png");

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

        if ( key == 's' ) {
            System.out.println("ALL EDGES: " + edges.size());
        }

        if ( key == 'a' ) {
//            allPossibleEdges = true;
            allPossibleEdges( true );
        }

        if ( key == 'k' ) {
//            System.out.println( edges.get(0).getConnectingDotA() == edges.get( 1 ).getConnectingDotB() || edges.get(0).getConnectingDotB() == edges.get( 1 ).getConnectingDotA() );
        }

        if ( key == 'r' ) {
//            randomActive = true;
            String num = JOptionPane.showInputDialog( "How many random connections?" );
            randomNumber = Integer.parseInt( num );
            randomEdges( true );
        }

//        if ( key == 'f' ) {
//            Edge e = new Edge( dots.get( 0 ), dots.get( 2 ) );
//            e.setStroke( 50 );
//            edges.add( e );
//
//            System.out.println("OVERALL EDGES: " + edges.size());
//            String msg = formsCycle( edges, e ) ? "True" : "False";
//
//            System.out.println(msg);
//        }

        if ( key == 'd' ) {
            wipeDots();
        }

        if ( key == 'e' ) {
            wipeEdges();
        }

        if ( key == 'p' ) {
            prims( dots );
        }

    }

    public void draw() {
        image(image, 0, 0);//, width, height);
        for ( Dot d : dots ) {
            image(d.getImage(), d.getX(), d.getY());
        }

        for ( Edge e : edges ) {

             stroke( e.getRed(), e.getGreen(), e.getBlue() );
             line( e.getConnectingDotA().getX(), e.getConnectingDotA().getY(), e.getConnectingDotB().getX(), e.getConnectingDotB().getY() );
//             clear();
        }


//        allPossibleEdges( allPossibleEdges );
//        randomEdges( randomActive );
    }

    public void createDots(int dotsWanted) {
        for ( int i = 0; i < dotsWanted; i ++ ) {
            int x = 0;
            int y = 0;
            notWhite = false;
            while(!notWhite) {
                x = (int)(Math.random() * width);
                y = (int)(Math.random() * height);
                notWhite = ( image.get( x, y ) >> 1 ) != -1 && ( image.get( x, y ) >> 1 ) != 0;
//                notWhite = false;
            }
//            image( dotImage, x, y );
            dots.add( new Dot( dotImage, x, y ) );
            System.out.println("============" +  ( image.get( x, y ) ) );
//            System.out.println(image.get( x, y ) >> 1 );
        }
    }

    private void randomEdges(boolean active) {
//        stroke( 102 );
        if ( active ) {
            if ( dots.size() == 3 && randomNumber == 2 ) {
                Edge a = new Edge( dots.get( 0 ), dots.get( 1 ) );
                Edge b = new Edge( dots.get( 1 ), dots.get( 2 ) );

                edges.add( a );
                edges.add( b );
                return;
            }
//            for ( int i = 0; i < randomNumber; i++ ) {
//                Edge e = new Edge((dots.get( i )), dots.get( (dots.size() - 1)/ (i + 1) ));
//                if ( !edges.contains( e ) ) {
//                    edges.add( e );


//                }
//            }
            ArrayList<String> made = new ArrayList<>(  );
            made.clear();
            Random randy = new Random(  );

            while (edges.size() < randomNumber) {

                int randomIndexA = 0;
                int randomIndexB = 0;
                randomIndexA = randy.nextInt( dots.size() );
                randomIndexB = randy.nextInt( dots.size() );

                String indicies = "" + (randomIndexA) + "" + randomIndexB;
                StringBuilder stringBuilder = new StringBuilder( indicies );
                while((randomIndexA == randomIndexB) || (made.contains( indicies ) || made.contains( stringBuilder.reverse().toString() ))) {
                    System.out.println("CALLED");
                    randomIndexA = randy.nextInt( dots.size() );
                    randomIndexB = randy.nextInt( dots.size() );
                    indicies = "" + (randomIndexA) + "" + randomIndexB;
                }
                made.add( indicies );
                System.out.println("A: " + randomIndexA + "\nB: " + randomIndexB);
                Edge e = new Edge( dots.get( randomIndexA ), dots.get( randomIndexB ) );
                edges.add( e );

                System.out.println(edges.size() + " ====== " + randomNumber);
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

    private boolean formsCycle(ArrayList<Edge> currentEdges, Edge newEdge) {
        Dot startDot = newEdge.getConnectingDotA();
        ArrayList<Dot> visitedDots = new ArrayList<>(  );
        visitedDots.add( startDot );
        for ( Edge e : startDot.getConnected() ) {
            if ( cycleHelper( currentEdges, visitedDots, visitedDots.contains(e.getConnectingDotA())? e.getConnectingDotB() : e.getConnectingDotA() ) ) {
                return true;
            }
        }
        return false;
    }
//
    private boolean cycleHelper(ArrayList<Edge> currentEdges, ArrayList<Dot> visitedNodes, Dot currentDot) {
        if ( currentDot.getConnected().size() < 2 ) {
            return false;
        }

        if ( visitedNodes.contains(currentDot) ) {
            return true;
        }

        for ( Edge e : currentDot.getConnected() ) {
            if (cycleHelper( currentEdges, visitedNodes, e.getConnectingDotA() )) {
                return true;
            }

            if ( cycleHelper( currentEdges, visitedNodes, e.getConnectingDotB() ) ) {
                return true;
            }
        }
        return false;
    }
//


    private void wipeDots() {
        dots.clear();
        allPossibleEdges = false;
        randomActive = false;
    }


    private boolean isUniqueDot(ArrayList<Dot> dots, Dot dot) {
        for ( Dot a : dots ) {
            if ( a.equals( dot ) ) {
                return false;
            }
        }
        return true;
    }

    private void wipeEdges() {
        edges.clear();
        allPossibleEdges = false;
        randomActive = false;
    }

    private boolean isUniqueEdge(Edge edge){
        for ( Edge e : edges ) {
            if ( e.equals( edge ) ) {
                return false;
            }
        }
        return true;
    }

    private boolean isUniqueEdge(int indexA, int indexB) {
        for ( Edge e : edges ) {
            if ( ( e.getConnectingDotA().equals( dots.get( indexA ) ) && e.getConnectingDotB().equals( dots.get( indexB ) ) ) ||
                    ( e.getConnectingDotA().equals( dots.get( indexB ) ) && e.getConnectingDotB().equals( dots.get( indexA ) ) ) ||
                    ( e.getConnectingDotB().equals( dots.get( indexA ) ) && e.getConnectingDotB().equals( dots.get( indexB ) ) ) ||
                    ( e.getConnectingDotB().equals( dots.get( indexB ) ) && e.getConnectingDotA().equals( dots.get( indexA ) ) )) {
                return false;
            }
        }
        return true;
    }

    public void prims(ArrayList<Dot> dots) {
        ArrayList<Dot> visitedNodes = new ArrayList<>(  );
        edges.clear();
        visitedNodes.add( dots.get( 0 ) );
        while(visitedNodes.size() < dots.size()) {
            List<Edge> availableConnections = findAvailable( visitedNodes );

            Edge smallest = findNthSmallestEdge( availableConnections, 1 );
            if ( availableConnections.size() == 0 ) {
                smallest = dots.stream().filter( c -> !visitedNodes.contains( c ) ).collect( Collectors.toList() ).get( 0 ).getConnected().get( 0 );
            }


                smallest.setColor( 0, 255, 255 );
                edges.add( smallest );
                addIfNotDuplicate( visitedNodes, smallest.getConnectingDotB() );
                addIfNotDuplicate( visitedNodes, smallest.getConnectingDotA() );

                availableConnections.forEach( x -> System.out.print( x.toString() + ", " ) );
                System.out.println( "\nSmallest Edge: " + smallest.getLength() );
                System.out.printf( "Dots: %s, Visited: %s, Available Connections: %s\n", dots.size(), visitedNodes.size(), availableConnections.size() );


        }


    }

    private Edge findNthSmallestEdge(List<Edge> edgeCollection, int n) {
        Collections.sort( edgeCollection );
        Object[] edgecol = (edgeCollection.stream().filter( c -> c.getLength() != 0 )).toArray();
        return (Edge)edgecol[n - 1];
    }

    private List<Edge> findAvailable(ArrayList<Dot> visited) {
        ArrayList<Edge> available = new ArrayList<>(  );
        for ( Dot a : visited ) {
            for ( Edge e : a.getConnected() ) {
                if ( containsUnvisited( e, visited ) && e.getLength() != 0 ) {
                    available.add( e );
                }
            }
        }
        return available;
    }

    private boolean containsUnvisited(Edge e, ArrayList<Dot> visited) {
        return (!visited.contains( e.getConnectingDotA() )) || (!visited.contains( e.getConnectingDotB() ));
    }

    private void addIfNotDuplicate( ArrayList<Dot> list, Dot o ) {
        if ( !list.contains( o ) ) {
            list.add( o );
        }
    }


    public static void main(String[] args) {
        PApplet.main( "Grapher" );
    }
}
