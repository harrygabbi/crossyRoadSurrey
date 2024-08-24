package com.personal.sample.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;

public class GameOverState extends State {

    private Texture backgroundImage;
    private Texture gameover;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        backgroundImage = new Texture("whitebackdrop.jpg");
        gameover = new Texture("gameOVER.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundImage, 0, 0, main.WIDTH, main.HEIGHT);
        sb.draw(gameover, ((float) (main.WIDTH / 2)) - ((float) gameover.getWidth() /4), ((float) main.HEIGHT / 2)- (gameover.getHeight()/4), 250, 250);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        gameover.dispose();
    }
}
