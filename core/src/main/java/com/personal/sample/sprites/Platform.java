package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Platform {

    private Texture wood;
    private float width;
    private float height;
    private Rectangle bounds;
    private float xpos;
    private float ypos;


    public Platform(float x , float y,float width){
        wood = new Texture("wood.jpeg");
        this.width = width;
        bounds = new Rectangle(x + 25,y,width-25,70);
        xpos = x;
        ypos = y;
    }

    public void move(float x){
        xpos += x;
        bounds.setPosition(xpos,ypos);
    }

    public void setWidth(float x){
        width = x;
        bounds.setWidth(width-25);
    }

    public Texture getWood() {
        return wood;
    }

    public float getXpos() {
        return xpos;
    }

    public float getYpos() {
        return ypos;
    }

    public boolean collides(Rectangle bird){
        return bird.overlaps(bounds);
    }

    public float getWidth() {
        return width;
    }

    public void setXPosition(float x) {
        xpos = x;
        bounds.setPosition(xpos+25,ypos);
    }
    public void dispose() {
        wood.dispose();
    }

}

