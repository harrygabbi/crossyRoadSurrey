package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class character {
    private Vector3 position;
    private Vector3 velocity;
    private Texture character;
    private Rectangle bounds;

    public character(float x, float y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        character = new Texture("character.png");
        bounds = new Rectangle(x,y,60,70);

    }




    public void update(float dt){

    }

//    public void setPosition(int x, int y){
//        position.set(x,y,0);
//    }

    public Texture getTexture(){
        return character;
    }
    public Vector3 getPosition(){
        return position;
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        bounds.setPosition(x,y);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
