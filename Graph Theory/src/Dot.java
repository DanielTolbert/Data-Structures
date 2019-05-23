import processing.core.PImage;

import java.util.ArrayList;

public class Dot {

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

    public void addConnected(Edge edge) {
        connectedEdges.add( edge );
    }

    public PImage getImage() {
        return image;
    }
}
