package com.personal.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.states.GameStateManager;
import com.personal.sample.states.MenuState;

public class main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Texture character;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final String TITLE = "crossy road Surrey";
    private float charX = 480;
    private float charY = 400;
    private final float charWidth = 60;
    private final float charHeight = 70;
    private GameStateManager gsm;


    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();

        // Load your background image. Make sure the image file is in the assets folder.
//        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
//        character = new Texture(Gdx.files.internal("character.png"));
        Gdx.gl.glClearColor(0, 0, 0, 1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        // Clear the screen

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
//        handleInput();

//        // Draw the background image
//        batch.begin();
//        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.draw(character, charX, charY, charWidth, charHeight);

    }

//    @Override
//    public void dispose() {
//        // Dispose of resources when no longer needed
//        batch.dispose();
//        backgroundImage.dispose();
//    }

//    public void handleInput() {
//        float deltaTime = Gdx.graphics.getDeltaTime();
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
//            charX -= 20; // Move left
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
//            charX += 20; // Move right
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
//            charY += 20; // Move up
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
//            charY -= 20; // Move down
//        }
//
//        // Ensure character stays within the screen bounds
//        charX = Math.max(0, Math.min(charX, Gdx.graphics.getWidth() - charWidth));
//        charY = Math.max(0, Math.min(charY, Gdx.graphics.getHeight() - charHeight));
//
//    }
}
