import processing.core.PImage;

public class Dot {

    private PImage image;
    private int x;
    private int y;

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

    public PImage getImage() {
        return image;
    }
}
