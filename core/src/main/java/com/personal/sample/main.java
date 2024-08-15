package com.personal.sample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture backgroundImage;
    private Texture character;
    private float charX = 480;
    private float charY = 400;
    private float charWidth = 60;
    private float charHeight = 70;

    @Override
    public void create() {
        batch = new SpriteBatch();
        // Load your background image. Make sure the image file is in the assets folder.
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        character = new Texture(Gdx.files.internal("character.png"));
    }

    @Override
    public void render() {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        // Draw the background image
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(character, charX, charY, charWidth, charHeight);

        batch.end();
    }

    @Override
    public void dispose() {
        // Dispose of resources when no longer needed
        batch.dispose();
        backgroundImage.dispose();
    }

    public void handleInput() {
        float deltaTime = Gdx.graphics.getDeltaTime();

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
}
