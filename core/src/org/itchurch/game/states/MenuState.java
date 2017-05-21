package org.itchurch.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.itchurch.game.SnatchyBird;

public class MenuState extends State {

    private Texture background;
    public static Texture playBtn;
    private Music musicMenu;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SnatchyBird.WIDTH / 2, SnatchyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        musicMenu = Gdx.audio.newMusic(Gdx.files.internal("8bitmk.mp3"));
        musicMenu.setLooping(true);
        musicMenu.setVolume(0.15f);
        musicMenu.play();
        playBtn = new Texture("touch.png");
    }

    @Override
    public void handle() {
        if (Gdx.input.justTouched()) {
            EndGameState.x = 0;
            gsm.set(new PlayState(gsm));
        }
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
        spb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y + 30);
        spb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        musicMenu.dispose();
    }
}
