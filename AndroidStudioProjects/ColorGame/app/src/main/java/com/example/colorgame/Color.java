package com.example.colorgame;

import java.util.ArrayList;

public class Color implements Comparable{

    private int r, g, b;
    public static ArrayList<Color> standings = new ArrayList<>();
    private double distance;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
//        standings.add(new Color(r,g,b));
        calcDistance(r,g,b);
    }

    public int[] getRGB() {
        int[] rgb = {r, g, b};
        return rgb;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public void calcDistance(int guessR, int guessG, int guessB) {
        distance =  Math.pow(Math.pow((r)
                - (guessR), 3) +
                Math.pow((g) - (guessG), 3) +
                Math.pow((b) - (guessB), 3),(1d/2d));
    }

    public  double getDistance() {
        return distance;
    }

    public static ArrayList<Color> getStandings() {
        return standings;
    }


    @Override
    public int compareTo(Object o) {
        if (((Color)o).getDistance() > this.getDistance()) {
            return -1;
        } else if (((Color)o).getDistance() == this.getDistance()) {
            return 0;
        } else {
            return 1;
        }
    }
}
