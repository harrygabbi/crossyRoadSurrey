package com.personal.sample.states;

import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;
import com.personal.sample.sprites.Background;
import com.personal.sample.sprites.Bus;
import com.personal.sample.sprites.Platform;
import com.personal.sample.sprites.character;
import com.personal.sample.sprites.train;

import java.util.Vector;

public class PlayState extends State {

    private Background background;
    private character bird;
    private float charX;
    private float charY;
    private final float charWidth = 60;
    private final float charHeight = 70;
    private Vector<Bus> bus;
    private Vector<Platform> woods;
    private Vector<train> trains;
    private int[] map;
    private float busvelocity = 50;
    private float woodvelocity = 30;
    private float trainvelocity = 100;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        // Set initial bird position to bottom middle of the screen
        charX = (float) main.WIDTH / 2 - charWidth / 2;
        charY = 0;
        bird = new character(charX, charY);
        background = new Background();
        cam.setToOrtho(false, main.WIDTH, main.HEIGHT);

        // Set camera to follow the bird initially
        cam.position.set(charX + charWidth / 2, charY + charHeight / 2, 0);

        map = new int[]{0, 2, 3, 2, 1, 0};
        bus = new Vector<>();
        woods = new Vector<>();
        trains = new Vector<>();
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {
                int randomNumber = -500 + random.nextInt(401);
                Bus newBus = new Bus(randomNumber, i * 71);
                bus.add(newBus);
            }
            if (map[i] == 2) {
                float randomwidth = 60 + random.nextInt(140);
                float randomxpos = -randomwidth + random.nextInt(200);
                Platform newplat = new Platform(randomxpos, i * 70, randomwidth);
                woods.add(newplat);
            }
            if (map[i] == 3) {
                int randomNumber = -500 + random.nextInt(401);
                train newtrain = new train(randomNumber, i * 71);
                trains.add(newtrain);
            }

        }
    }

    @Override
    protected void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            charX -= 35; // Move left
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            charX += 35; // Move right
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            charY += 70; // Move up
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            charY -= 70; // Move down
        }

        // Ensure character stays within the screen bounds
        charX = Math.max(0, Math.min(charX, Gdx.graphics.getWidth() - charWidth));
        charY = Math.max(0, Math.min(charY, Gdx.graphics.getHeight() - charHeight));
        bird.setPosition(charX, charY);
    }

    @Override
    public void update(float dt) {
        handleInput();

        for (Bus b : bus) {
            b.move(dt * busvelocity);
            if (b.collide(bird.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        for (train t : trains) {
            t.move(dt * trainvelocity);
            if (t.collide(bird.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        for (Platform w : woods) {
            w.move(dt * woodvelocity);
            if ((w.getYpos() == bird.getPosition().y)) {
                if (bird.getPosition().x <= (w.getXpos() - 30) || (bird.getPosition().x + 30) >= (w.getXpos() + w.getWidth())) {
                    gsm.set(new GameOverState(gsm));
                }
                charX += (dt * woodvelocity);
            }
        }

        // Update camera position to follow the bird
        cam.position.x = main.WIDTH / 2;  // Keep camera x position in the middle
        cam.position.y = bird.getPosition().y + charHeight / 2;

        // Clamp camera within game world bounds
        cam.position.y = Math.max(cam.viewportHeight / 2, Math.min(cam.position.y, main.HEIGHT - cam.viewportHeight / 2));

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        cam.update();
        sb.setProjectionMatrix(cam.combined);

        // Draw all the layers
        for (int x = 0; x < map.length; x++) {
            switch (map[x]) {
                case 0:
                    sb.draw(background.getGrassTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 + 1, Gdx.graphics.getWidth(), 1);
                    break;
                case 1:
                    sb.draw(background.getRoadTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 + 1, Gdx.graphics.getWidth(), 1);
                    break;
                case 2:
                    sb.draw(background.getWaterTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 + 1, Gdx.graphics.getWidth(), 1);
                    break;
                case 3:
                    sb.draw(background.getTrainTrackTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 + 1, Gdx.graphics.getWidth(), 1);
            }
        }

        for (Bus b : bus) {
            sb.draw(b.getBus(), b.getxPos(), b.getyPos(), 180, 60);
        }
        for (Platform w : woods) {
            sb.draw(w.getWood(), w.getXpos(), w.getYpos(), w.getWidth(), 65);
        }
        for (train t : trains) {
            sb.draw(t.getTrain(), t.getxPos(), t.getyPos(), 350, 60);
        }
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, charWidth, charHeight);
        sb.end();
    }

    @Override
    public void dispose() {
//        background.dispose();
//        bird.dispose();
//        for (Bus b : bus) {
//            b.dispose();
//        }
//        for (Platform w : woods) {
//            w.dispose();
//        }
    }
}
