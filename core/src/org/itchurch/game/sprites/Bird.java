package org.itchurch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class Bird {
    private static final int move = 100;
    private static final int gravity = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bBird;
    private Texture texture;
    private Animation animation;
    private Texture Bird;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        Bird = new Texture("bird.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.2f);
        bBird = new Rectangle(x, y, Bird.getWidth(), Bird.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return animation.getFrame();
    }

    public void update(float dt) {
        animation.update(dt);
        if (position.y > 80 || position.y < 376)
            velocity.add(0, gravity, 0);
        velocity.scl(dt);
        position.add(move * dt, velocity.y, 0);
        if (position.y < 80) {
            position.y = 80;
        }
        if (position.y > 376) {
            position.y = 376;
        }
        velocity.scl(1 / dt);
        bBird.setPosition(position.x, position.y);


    }

    public void jump() {
        velocity.y = 315;
    }

    public Rectangle getbBird() {
        return bBird;
    }

    public void dispose() {
        texture.dispose();
    }
}


