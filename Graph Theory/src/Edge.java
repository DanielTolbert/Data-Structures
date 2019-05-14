public class Edge {

    private Dot a;
    private Dot b;

    public Edge(Dot a, Dot b) {
        this.a = a;
        this.b = b;
    }

    public Dot getConnectingDotA() {
        return a;
    }

    public Dot getConnectingDotB() {
        return b;
    }

    public boolean equals(Edge otherEdge) {
        return a == otherEdge.getConnectingDotA() && b == otherEdge.getConnectingDotB();
    }


}
