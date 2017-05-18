package org.itchurch.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.itchurch.game.SnatchyBird;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SnatchyBird.WIDTH / 2, SnatchyBird.HEIGHT / 2);
        background = new Texture("bg.jpg");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handle() {
        if(Gdx.input.justTouched())
            gsm.set(new PlayState(gsm));

    }

    @Override
    public void update(float dt) {
        handle();

    }

    @Override
    public void render(SpriteBatch spb) {
        spb.setProjectionMatrix(camera.combined);
        spb.begin();
        spb.draw(background, 0, 0);
        spb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        spb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("MenuState Disposed");

    }
}
