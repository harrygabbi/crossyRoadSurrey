package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bus {
    public Texture getBus() {
        return bus;
    }

    private Texture bus;
    private Rectangle bounds;
    private float xPos;
    private float yPos;
    private int busspeed;

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public Bus(float x, float y, int speed){
        bus = new Texture("Bus.png");
        bounds = new Rectangle(x+10,y,180 - 10,60);
        xPos = x;
        yPos = y;
        busspeed = speed;
    }

    public int getBusspeed() {
        return busspeed;
    }

    public boolean collide(Rectangle bird){
        return bird.overlaps(bounds);
    }

    public void move(float x){
        xPos += x;
        bounds.setPosition(xPos, yPos);
    }

    public void setXPosition(float x) {
        xPos = x;
        bounds.setPosition(xPos,yPos);
    }
    public void dispose() {
        bus.dispose();
    }
}
