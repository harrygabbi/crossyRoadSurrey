package com.personal.sample.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;


public class PlayState extends State{

    private Texture character;
    private Texture backgroundImage;
    private float charX = 50;
    private float charY = 50;
    private final float charWidth = 60;
    private final float charHeight = 70;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        character = new Texture("character.png");
        backgroundImage = new Texture("background.png");
        cam.setToOrtho(false,main.WIDTH/2,main.HEIGHT/2);

    }

    @Override
    protected void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            charX -= 20; // Move left
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            charX += 20; // Move right
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            charY += 20; // Move up
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            charY -= 20; // Move down
        }

        // Ensure character stays within the screen bounds
        charX = Math.max(0, Math.min(charX, Gdx.graphics.getWidth() - charWidth));
        charY = Math.max(0, Math.min(charY, Gdx.graphics.getHeight() - charHeight));

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
    sb.begin();
    sb.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    sb.draw(character,charX, charY, charWidth, charHeight);
    //handleInput();
    sb.end();
    }

    @Override
    public void dispose() {

    }
}
