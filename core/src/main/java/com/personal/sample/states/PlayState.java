package com.personal.sample.states;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;
import com.personal.sample.sprites.Background;
import com.personal.sample.sprites.Bus;
import com.personal.sample.sprites.Platform;
import com.personal.sample.sprites.character;

import java.util.Random;
import java.util.Vector;

public class PlayState extends State{

    private Texture character;
    private Background background;
    private character bird;
    private float charX = 350;
    private float charY = 0;
    private final float charWidth = 60;
    private final float charHeight = 70;
    private Vector<Bus> bus;
    private Vector<Platform> woods;
    private int[] map;

    public PlayState(GameStateManager gsm) {
        super(gsm);
//        character = new Texture("character.png");
        bird = new character(charX, charY);
        background = new Background();
        cam.setToOrtho(false,main.WIDTH,main.HEIGHT);
        map = new int[]{0, 2, 1, 2, 1, 0};
        bus = new Vector<Bus>();
        woods = new Vector<Platform>();
        for(int i = 0 ; i<map.length; i++){
            if(map[i] == 1){
                int randomNumber = -500 + random.nextInt(401);
                Bus newBus = new Bus(randomNumber,i*71);
                bus.add(newBus);
            }
            if(map[i] == 2){
                float randomwidth = 60 + random.nextInt(140);
                float randomxpos = -randomwidth + random.nextInt(200);
                Platform newplat = new Platform(randomxpos,i*70,randomwidth);
                woods.add(newplat);
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

        for(Bus b:bus){
            b.move(dt*30);
            if (b.collide(bird.getBounds())){
                gsm.set(new MenuState(gsm));
            }
        }

        for(Platform w:woods) {
            w.move(dt * 30);
            if ((w.getYpos()==bird.getPosition().y)) {

                if(bird.getPosition().x <= (w.getXpos() - 30) || (bird.getPosition().x + 30) >=( w.getXpos() + w.getWidth())) {
                    gsm.set(new MenuState(gsm));
                }
                charX += (dt*30);

            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {


        sb.setProjectionMatrix(cam.combined);
        sb.begin();



        // First draw all the layers
        for (int x = 0; x < map.length; x++) {
            switch (map[x]) {
                case 0:
                    sb.draw(background.getGrassTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 +1, Gdx.graphics.getWidth(), 1);

                    break;
                case 1:
                    sb.draw(background.getRoadTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 +1, Gdx.graphics.getWidth(), 1);


                    break;
                case 2:
                    sb.draw(background.getWaterTexture(), 0, x * 70, Gdx.graphics.getWidth(), 69);
                    sb.draw(background.getBlackbackground(), 0, x * 69 +1, Gdx.graphics.getWidth(), 1);


                    break;
            }
        }



//        sb.draw(bus.getBus(), bus.getxPos(), bus.getyPos(), 180, 60);

        for(Bus b:bus){
            sb.draw(b.getBus(), b.getxPos(), b.getyPos(), 180, 60);
        }
        for(Platform w : woods){
            sb.draw(w.getWood(),w.getXpos(),w.getYpos(),w.getWidth(),65);
        }

        sb.draw(bird.getTexture() ,bird.getPosition().x, bird.getPosition().y, charWidth, charHeight);

        sb.end();

    }

    @Override
    public void dispose() {

    }
}
