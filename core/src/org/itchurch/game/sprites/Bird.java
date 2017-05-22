package org.itchurch.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.itchurch.game.states.EndGameState;


public class Bird {
    private static final int move = 100;
    private static final int gravity = -15;
    private Vector2 position;
    private Vector2 velocity;
    public static Rectangle bBird;
    private Texture texture;
    private Animation animation;
    private Texture Bird;
    public static Music scoreadd;

    public Bird(int x, int y) {
        position = new Vector2(x + 2, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("birdanimation.png");
        Bird = new Texture("bird.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bBird = new Rectangle(x, y, Bird.getWidth(), Bird.getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return animation.getFrame();
    }

    public void update(float dt) {
        animation.update(dt);
        if (position.y > 80 || position.y < 376)
            velocity.add(0, gravity);
        velocity.scl(dt);
        position.add(move * dt, velocity.y);
        if (position.y < 80) {
            position.y = 80;
        }
        if (EndGameState.x * 173 <= (position.x - 138)) {
            EndGameState.x++;
            if (EndGameState.x == 100){
                EndGameState.x = 0;
            }
            scoreadd = Gdx.audio.newMusic(Gdx.files.internal("point.wav"));
            scoreadd.setVolume(0.15f);
            scoreadd.play();
        }
        if (position.y > 376) {
            position.y = 376;
        }
        velocity.scl(1 / dt);
        bBird.setPosition(position.x, position.y);


    }

    public void jump() {
        velocity.y = 269;
    }

    public Rectangle getbBird() {
        return bBird;
    }

    public void dispose() {
        texture.dispose();
    }
}


