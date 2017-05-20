package org.itchurch.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import org.itchurch.game.SnatchyBird;
import org.itchurch.game.sprites.Bird;
import org.itchurch.game.sprites.Tubes;


public class PlayState extends State {

    private static final int space = 125;
    private static final int count = 4;
    private static final int gg = -30; // граница земли не забыть

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundp1, groundp2;
    private Music musicPlay;

    private Array<Tubes> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, SnatchyBird.WIDTH / 2, SnatchyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        musicPlay = Gdx.audio.newMusic(Gdx.files.internal("8bitgame.mp3"));
        musicPlay.setVolume(0.005f);
        musicPlay.play();
        groundp1 = new Vector2(camera.position.x - camera.viewportWidth / 2, gg);
        groundp2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), gg);

        tubes = new Array<Tubes>();

        for (int i = 0; i < count; i++) {
            tubes.add(new Tubes(i * (space + Tubes.twidth)));
        }
    }

    @Override
    protected void handle() {
        if (Gdx.input.justTouched())
            bird.jump();

    }

    @Override
    public void update(float dt) {
        handle();
        bird.update(dt);
        groundGrade();
        camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {

            Tubes tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTop().x + tube.getTop().getWidth()) {
                tube.reposition(tube.getPosTop().x + ((Tubes.twidth + space) * count));
            }
            if (tube.collides(bird.getbBird()))
                gsm.set(new PlayState(gsm));
        }
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tubes tube : tubes) {
            sb.draw(tube.getTop(), tube.getPosBot().x, tube.getPosTop().y);
            sb.draw(tube.getBot(), tube.getPosBot().x, tube.getPosBot().y);
        }
        sb.draw(ground, groundp1.x, groundp1.y);
        sb.draw(ground, groundp2.x, groundp2.y);
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        musicPlay.dispose();
        for (Tubes tube : tubes)
            tube.dispose();
        System.out.println("PS Disposed");
    }
    private void groundGrade(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundp1.x + ground.getWidth())
            groundp1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundp2.x + ground.getWidth())
            groundp2.add(ground.getWidth() * 2, 0);
    }
}