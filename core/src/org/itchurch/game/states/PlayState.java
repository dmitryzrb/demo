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

    private static final int space = 120;
    private static final int count = 4;
    private static final int gg = -30;

    private Bird bird;
    private Texture bg;
    private Vector2 groundp1, groundp2;
    public static Music musicPlay, wing;
    public static Texture ground;

    private Array<Tubes> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, SnatchyBird.WIDTH / 2, SnatchyBird.HEIGHT / 2);
        bg = new Texture("bg.png");
        MenuState.musicMenu.dispose();
        Bird.scoreadd = Gdx.audio.newMusic(Gdx.files.internal("point.wav"));
        Tubes.die = Gdx.audio.newMusic(Gdx.files.internal("die.wav"));
        musicPlay = Gdx.audio.newMusic(Gdx.files.internal("8bitgame.mp3"));
        musicPlay.setVolume(0.15f);
        musicPlay.setLooping(true);
        musicPlay.play();
        EndGameState.first = new Texture("0.png");
        EndGameState.second = new Texture("0.png");
        ground = new Texture("ground.png");
        groundp1 = new Vector2(camera.position.x - camera.viewportWidth / 2, gg);
        groundp2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), gg);

        tubes = new Array<Tubes>();

        for (int i = 0; i < count; i++) {
            tubes.add(new Tubes(i * (space + Tubes.twidth)));
        }
    }

    @Override
    protected void handle() {
        if (Gdx.input.justTouched()) {
            wing = Gdx.audio.newMusic(Gdx.files.internal("wing.wav"));
            wing.setVolume(0.15f);
            wing.play();
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handle();
        bird.update(dt);
        groundGrade();
        camera.position.x = bird.getPosition().x + 80;
        EndGameState.first = new Texture(EndGameState.names[EndGameState.x / 10]);
        EndGameState.second = new Texture(EndGameState.names[EndGameState.x % 10]);
        for (int i = 0; i < tubes.size; i++) {
            Tubes tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTop().x + tube.getTop().getWidth()) {
                tube.reposition(tube.getPosTop().x + ((Tubes.twidth + space) * count));
            }
            if (tube.collides(bird.getbBird())) {
                musicPlay.dispose();
                PlayState.wing.dispose();
                Bird.scoreadd.dispose();
                gsm.set(new EndGameState(gsm));
            }

        }
        camera.update();
    }
s
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tubes tube : tubes) {
            sb.draw(tube.getTop(), tube.getPosTop().x, tube.getPosTop().y);
            sb.draw(tube.getBot(), tube.getPosBot().x, tube.getPosBot().y);
        }
        sb.draw(ground, groundp1.x, groundp1.y);
        sb.draw(ground, groundp2.x, groundp2.y);
        sb.draw(EndGameState.first, (camera.position.x - EndGameState.first.getWidth() / 2) - 15, camera.position.y + 150);
        sb.draw(EndGameState.second, (camera.position.x - EndGameState.second.getWidth() / 2) + 15, camera.position.y + 150);
        sb.end();

    }

    @Override
    public void dispose() {
        if (EndGameState.egsMusic.isPlaying()) {
            EndGameState.egsMusic.dispose();
        }
        if (Tubes.die.isPlaying()) {
            Tubes.die.dispose();
        }
        bg.dispose();
        bird.dispose();
        ground.dispose();
        Bird.scoreadd.dispose();
        Tubes.die.dispose();
        musicPlay.dispose();
        wing.dispose();
        for (Tubes tube : tubes)
            tube.dispose();
    }

    private void groundGrade() {
        if (camera.position.x - (camera.viewportWidth / 2) > groundp1.x + ground.getWidth())
            groundp1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundp2.x + ground.getWidth())
            groundp2.add(ground.getWidth() * 2, 0);
    }
}