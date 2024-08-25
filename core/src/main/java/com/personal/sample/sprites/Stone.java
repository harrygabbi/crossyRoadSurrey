package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Stone {
    private Texture wood;
    private Rectangle bounds;
    private float xpos;
    private boolean isPicked;
    private boolean isPlaced;
    private String imagepath;
    private int stoneId;
    static int id = 1;



    public Stone(float x , float y, String imagePath){
        wood = new Texture(imagePath);
        bounds = new Rectangle(x + 25,y,10,10);
        xpos = x;
        isPicked = false;
        isPlaced = false;
        this.imagepath = imagePath;
        stoneId = id;
        id++;
    }

    public int getStoneId() {
        return stoneId;
    }


    public void setPicked(boolean picked) {
        isPicked = picked;
    }

    public boolean getPicked(){
        return isPicked;
    }

    public String getImagepath() {
        return imagepath;
    }



    public Texture getStone() {
        return wood;
    }

    public float getXpos() {
        return xpos;
    }

    public boolean collides(Rectangle bird){
        return bird.overlaps(bounds);
    }


    public void setPosition(float x) {
        xpos = x;
        bounds.setPosition(xpos,10);
    }

    public void dispose() {
        wood.dispose();
    }

    public boolean isPlaced() {
        return isPlaced;
    }
    public void setPlaced(boolean x){
        isPlaced = x;
    }
}
