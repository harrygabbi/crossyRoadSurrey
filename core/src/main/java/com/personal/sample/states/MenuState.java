package com.personal.sample.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.personal.sample.main;

public class MenuState extends State{
    private Texture backgroundImage;
    private Texture playbutton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        backgroundImage = new Texture("background.jpg");
        playbutton = new Texture("playbtn.png");

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
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
        sb.draw(playbutton,((float) (main.WIDTH/2))-(float)(50),((float) main.HEIGHT/2),100,80);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        playbutton.dispose();
    }
}
