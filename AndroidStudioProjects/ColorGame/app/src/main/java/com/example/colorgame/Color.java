package com.example.colorgame;

import java.util.ArrayList;
import java.util.Collections;

public class Color implements Comparable {

    private int r, g, b;
    public static ArrayList<Color> standings = new ArrayList<>();
    private double distance;
    private static double average = 0;

    public static double getAverage() {
        return average;
    }

    public static void addToAverage(double distance) {
        average = ( average + distance ) / 2;
    }

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

    public static void sortStandings() {
        Collections.sort(standings);
    }

    public double calcDistance(int guessR, int guessG, int guessB) {
        distance =  Math.pow(Math.pow((r)
                - (guessR), 2) +
                Math.pow((g) - (guessG), 2) +
                Math.pow((b) - (guessB), 2),(1d/2d));
        return distance;
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
