package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class train {
    public Texture getTrain() {
        return train;
    }

    private Texture train;
    private Rectangle bounds;
    private float xPos;
    private float yPos;

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public train(float x, float y){
        train = new Texture("skytrain.jpeg");
        bounds = new Rectangle(x,y,350,70);
        xPos = x;
        yPos = y;
    }

    public boolean collide(Rectangle bird){
        return bird.overlaps(bounds);
    }

    public void move(float x){
        xPos += x;
        bounds.setPosition(xPos, yPos);
    }

}
