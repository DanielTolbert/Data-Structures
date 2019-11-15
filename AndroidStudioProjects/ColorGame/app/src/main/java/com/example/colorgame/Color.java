package com.example.colorgame;

import java.util.ArrayList;

public class Color {

    private int r, g, b;
    private static ArrayList<Color> standings;

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        standings.add(new Color(r,g,b));
    }

    public int[] getRGB() {
        int[] rgb = {r, g, b};
        return rgb;
    }

    public double getDistance() {
        Math.pow(Math.pow(Double.valueOf(answers[0])
                - Double.valueOf(guesses[0]), 3) +
                Math.pow(Double.valueOf(answers[1]) - Double.valueOf(guesses[1]), 3) +
                Math.pow(Double.valueOf(answers[2]) - Double.valueOf(guesses[2]), 3),(1d/2d))
    }


}
