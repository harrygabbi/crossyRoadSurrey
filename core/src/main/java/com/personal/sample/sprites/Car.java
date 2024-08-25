package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Car {
    public Texture getCar() {
        return car;
    }

    private Texture car;
    private Rectangle bounds;
    private float xPos;
    private float yPos;
    private int carspeed;

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public Car(float x, float y, int speed){
        car = new Texture("car.png");
        bounds = new Rectangle(x +7,y,140 - 7,50);
        xPos = x;
        yPos = y+10;
        carspeed = speed;
    }

    public int getCarspeed() {
        return carspeed;
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
        car.dispose();
    }

}
