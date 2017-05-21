package org.itchurch.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.itchurch.game.SnatchyBird;

public class EndGameState extends State {

    Texture background;
    Texture nextBtn;

    public static Texture first;
    public static Texture second;
    public static int x = 0;
    public static String[] names = {
            "0.png",
            "1.png",
            "2.png",
            "3.png",
            "4.png",
            "5.png",
            "6.png",
            "7.png",
            "8.png",
            "9.png"
    };

    public EndGameState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, SnatchyBird.WIDTH / 2, SnatchyBird.HEIGHT / 2);
        background = new Texture("bg.png");
        nextBtn = new Texture("playbtn.png");
        MenuState.playBtn = new Texture("touch.png");
        first = new Texture(names[x / 10]);
        second = new Texture(names[x % 10]);
    }

    @Override
    protected void handle() {
        if (Gdx.input.justTouched())
            gsm.set(new PlayState(gsm));
            x = 0;
    }

    @Override
    public void update(float updt) {
        handle();
    }


    @Override
    public void render(SpriteBatch spb) {
        spb.setProjectionMatrix(camera.combined);
        spb.begin();
        spb.draw(background, 0, 0);
        spb.draw(first, (camera.position.x - first.getWidth() / 2) - 15, camera.position.y + 150);
        spb.draw(second, (camera.position.x - second.getWidth() / 2) + 15, camera.position.y + 150);
        spb.draw(MenuState.playBtn, camera.position.x - MenuState.playBtn.getWidth() / 2, camera.position.y + 30);
        spb.end();

    }


    @Override
    public void dispose() {
        background.dispose();
        nextBtn.dispose();
        first.dispose();
        second.dispose();
    }
}
