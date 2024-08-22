package com.personal.sample.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class character {
    private Vector3 position;
    private Vector3 velocity;
    private Texture character;

    public character(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        character = new Texture("character.png");


    }


    public void update(float dt){

    }
}
