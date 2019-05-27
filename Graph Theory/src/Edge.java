public class Edge implements Comparable{

    private Dot a;
    private Dot b;
    private int fill;
    private String name;
    private int[] color;

    public Edge(Dot a, Dot b) {
        this(a, b, 0, 0, 0);
    }

    public Edge(Dot a, Dot b, int... color) {
        this.a = a;
        this.b = b;
        this.name = hashCode() + "";
        this.color = color;

        a.addConnected( this );
        b.addConnected( this );
    }

    public Dot getConnectingDotA() {
        return a;
    }

    public Dot getConnectingDotB() {
        return b;
    }

    public int getRed() {
        return color[0];
    }

    public int getGreen() {
        return color[1];
    }

    public int getBlue() {
        return color[2];
    }
    public int getFill() {
        return fill;
    }

    public void setColor(int... color) {
        this.color = color;
    }

    public void setStroke( int fill) {
        this.fill = fill;
    }

    public int getLength() {
        return (int)Math.sqrt( (b.getX() - a.getX())^2 + (b.getY() - a.getY())^2 );
    }

    public String getName() {
        return name;
    }

    public Edge get() {
        return this;
    }

    public boolean equals(Edge otherEdge) {
        return (a == otherEdge.getConnectingDotA() && b == otherEdge.getConnectingDotB() ) || (a == otherEdge.getConnectingDotB() && b == otherEdge.getConnectingDotA());
    }

    public int compareTo(Object o) {
        if(getLength() < ((Edge)o).getLength()) {
            return -1;
        } else if ( getLength() > ((Edge)o).getLength() ) {
            return 1;
        }
        return 0;
    }
}
