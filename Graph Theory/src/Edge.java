public class Edge {

    private Dot a;
    private Dot b;
    private int fill;
    private String name;

    public Edge(Dot a, Dot b) {
        this(a, b, 102);
    }

    public Edge(Dot a, Dot b, int fill) {
        this.a = a;
        this.b = b;
        this.name = hashCode() + "";
        this.fill = fill;

        a.addConnected( this );
        b.addConnected( this );
    }

    public Dot getConnectingDotA() {
        return a;
    }

    public Dot getConnectingDotB() {
        return b;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Edge otherEdge) {
        return (a == otherEdge.getConnectingDotA() && b == otherEdge.getConnectingDotB() ) || (a == otherEdge.getConnectingDotB() && b == otherEdge.getConnectingDotA());
    }


}
