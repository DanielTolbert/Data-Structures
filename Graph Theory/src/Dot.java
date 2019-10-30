import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Dot implements Comparable{

    private PImage image;
    private int x;
    private int y;
    private ArrayList<Edge> connectedEdges = new ArrayList<>(  );

    public Dot ( PImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isConnected() {
        return connectedEdges.size() > 0;
    }

    public ArrayList<Edge> getConnected() {
        return connectedEdges;
    }

    public void setImage( PImage image) {
        this.image = image;
    }

    public void addConnected(Edge edge) {
        connectedEdges.add( edge );
    }

    public PImage getImage() {
        return image;
    }

    @Override
    public int compareTo( Object o ) {
        if ( getConnected().size() > ((Dot)o).getConnected().size()) {
            return 1;
        } else if ( getConnected().size() < ((Dot)o).getConnected().size()) {
            return -1;
        }
        return 0;
    }
}
