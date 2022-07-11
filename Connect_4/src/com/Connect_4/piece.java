package com.Connect_4;

import java.awt.Color;

public class piece extends Color{
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public piece  (int r, int g, int b)
    {
        super(r, g, b);
    }
}
