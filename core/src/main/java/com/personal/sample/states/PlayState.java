package com.personal.sample.states;

import static com.badlogic.gdx.math.MathUtils.random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;
import com.personal.sample.sprites.Background;
import com.personal.sample.sprites.Bus;
import com.personal.sample.sprites.Car;
import com.personal.sample.sprites.Platform;
import com.personal.sample.sprites.Stone;
import com.personal.sample.sprites.character;
import com.personal.sample.sprites.train;

import java.util.Objects;
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
    private Vector<Car> cars;
    private int[] map;
    private float woodvelocity = 30;
    private float trainvelocity = 200;
    private Vector<Stone> stones;
    private Vector<Stone> placedStones;
    private boolean hasBirdPicked;
    private String pickedStone;


    public PlayState(GameStateManager gsm) {
        super(gsm);

        // Set initial bird position to bottom middle of the screen
        charX = (float) main.WIDTH / 2 - charWidth / 2;
        charY = 0;
        bird = new character(charX, charY);
        background = new Background();
        hasBirdPicked = false;


        map = new int[]{0, 1, 2, 3, 1, 0};
        bus = new Vector<>();
        woods = new Vector<>();
        trains = new Vector<>();
        cars = new Vector<>();
        stones = new Vector<>();
        placedStones = new Vector<>();

        //adding Stones to the vector
        stones.add(new Stone(240, main.HEIGHT - 60,"yellow.png"));
        stones.add(new Stone(400, main.HEIGHT - 60,"red.png"));

        placedStones.add(new Stone(240, 10,"yellow.png"));
        placedStones.add(new Stone(400, 10,"red.png"));

        //CHOOSING BACKDROP
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 1) {

                    int randomNumber = -500 + random.nextInt(401);
                    int randomNumber2 = random.nextInt(10);
                    if (randomNumber % 2 == 0) {
                        Bus newBus = new Bus(randomNumber, i * 71,30 + random.nextInt(100));
                        bus.add(newBus);
                    } else {
                        Car newCar = new Car(randomNumber, i * 70,40 + random.nextInt(100));
                        cars.add(newCar);
                    }
                    if (randomNumber2 % 2 == 0) {
                        Bus newBus = new Bus(randomNumber - 500, i * 71,30 + random.nextInt(100));
                        bus.add(newBus);
                    } else {
                        Car newCar = new Car(randomNumber - 500, i * 70,40 + random.nextInt(100));
                        cars.add(newCar);
                    }

            }
            if (map[i] == 2) {
                float randomwidth = 60 + random.nextInt(140);
                float randomwidth2 = 60 + random.nextInt(140);

                float randomxpos = -randomwidth + random.nextInt(200);
                float randomxpos2 = -400 + random.nextInt(201) -randomxpos*3;

                Platform newplat = new Platform(randomxpos, i * 70, randomwidth);
                Platform newplat2 = new Platform(randomxpos2, i * 70, randomwidth2);

                woods.add(newplat);
                woods.add(newplat2);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            charX -= 35; // Move left
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            charX += 35; // Move right
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            charY += 70; // Move up
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) {
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

        boolean onPlatform = false;

        for (Platform w : woods) {
            if(w.getXpos() > main.WIDTH){
                float randomwidth = 60 + random.nextInt(140);
                float randomxpos = -randomwidth - 300 + random.nextInt(100);
                w.setXPosition(randomxpos);
                w.setWidth(randomwidth);
            }
            w.move(dt * woodvelocity);

            if(w.collides(bird.getBounds())){
                onPlatform = true;
            }
        }

        if(onPlatform){
            charX +=  dt * woodvelocity;
        }

        // If the bird is not on any platform and it's on the water, trigger game over
        if (!onPlatform) {
            for (Platform w : woods) {
                if (bird.getPosition().y == w.getYpos()) {
                    gsm.set(new GameOverState(gsm));
                    break;
                }
            }
        }

        for (Bus b : bus) {
            b.move(dt * b.getBusspeed());
            if (b.getxPos() > main.WIDTH) {
                int randomBusNumber = -500 + random.nextInt(301);
                b.setXPosition(randomBusNumber);
            }
            if (b.collide(bird.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        for(Car c: cars){
            c.move(dt * c.getCarspeed());
            if (c.getxPos() > main.WIDTH) {
                int randomCarNumber = -500 + random.nextInt(201);
                c.setXPosition(randomCarNumber);
            }
            if(c.collide(bird.getBounds())){
                gsm.set(new GameOverState(gsm));
            }
        }

        for (train t : trains) {
            t.move(dt * trainvelocity);
            if (t.getxPos() > main.WIDTH) {
                int randomTrainNumber = -1000 + random.nextInt(201);
                t.setXPosition(randomTrainNumber);
            }
            if (t.collide(bird.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        if(!hasBirdPicked) {
            for (Stone s : stones) {
                if (s.collides(bird.getBounds())) {
                    s.setPicked(true);
                    hasBirdPicked = true;
                    pickedStone = s.getImagepath();
                    break;
                }
            }
        }
        else{
            for (Stone s : placedStones) {
                if (s.collides(bird.getBounds())) {
                    s.setPicked(true);
                    hasBirdPicked = false;
                    break;
                }
            }
        }
    }


    @Override
    public void render(SpriteBatch sb) {

        sb.begin();

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
        for (Car c : cars) {
            sb.draw(c.getCar(), c.getxPos(), c.getyPos(), 140, 50);
        }
        for (Platform w : woods) {
            sb.draw(w.getWood(), w.getXpos(), w.getYpos(), w.getWidth(), 65);
        }
        for (train t : trains) {
            sb.draw(t.getTrain(), t.getxPos(), t.getyPos(), 350, 60);
        }

        for(Stone s: stones){
            if(!s.getPicked()){
                sb.draw(s.getStone(),s.getXpos(),420-60,35,45);
            }
        }

        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, charWidth, charHeight);
        for (Stone s : placedStones) {
            // Check if the stone collides with the bird and matches the picked stone
            if (s.collides(bird.getBounds()) && Objects.equals(s.getImagepath(), pickedStone)) {
                s.setPlaced(true);  // Mark this stone as placed
                pickedStone = null;  // Reset pickedStone after placing
                hasBirdPicked = false;  // Allow the bird to pick another stone
            }

            // Draw the stone with full opacity if placed, otherwise with half opacity
            if (s.isPlaced()) {
                sb.setColor(1f, 1f, 1f, 1f);  // Full opacity for placed stones
            } else {
                sb.setColor(1f, 1f, 1f, 0.5f);  // Half opacity for unplaced stones
            }

            sb.draw(s.getStone(), s.getXpos(), 10, 35, 45);
            sb.setColor(1f, 1f, 1f, 1f);  // Reset color to full opacity for other draws
        }
        sb.end();
    }

    @Override
    public void dispose() {
        for (Bus b : bus) {
           b.dispose();
        }

        for(Car c: cars){
           c.dispose();
        }

        for (train t : trains) {
            t.dispose();
        }

        for(Platform p: woods){
            p.dispose();
        }
    }
}
