package org.itchurch.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.itchurch.game.states.PlayState;

import java.util.Random;

public class Tubes {


    public static final int otklon = 130;
    public static final int gap = 100;
    public static final int botborder = 120;
    public static final int twidth = 52;

    private Texture Top, Bot;

    private Vector2 posTop, posBot;
    private Random random;
    private Rectangle bTop, bBot, groundRect;
    public static Music die;


    public Texture getTop() {
        return Top;
    }

    public Texture getBot() {
        return Bot;
    }

    public Vector2 getPosTop() {
        return posTop;
    }

    public Vector2 getPosBot() {
        return posBot;
    }

    public Tubes(float x) {
        Top = new Texture("toptube.png");
        Bot = new Texture("bottomtube.png");
        random = new Random();


        posTop = new Vector2(x, random.nextInt(otklon) + gap + botborder);
        posBot = new Vector2(x, posTop.y - gap - Bot.getHeight());

        bTop = new Rectangle(posTop.x, posTop.y, Top.getWidth(), Top.getHeight());
        bBot = new Rectangle(posBot.x, posBot.y, Bot.getWidth(), Bot.getHeight());
        groundRect = new Rectangle(0, -30, PlayState.ground.getWidth(), PlayState.ground.getHeight());
    }

    public void reposition(float x) {
        posTop.set(x, random.nextInt(otklon) + gap + botborder);
        posBot.set(x, posTop.y - gap - Bot.getHeight());
        bTop.setPosition(posTop.x, posTop.y);
        groundRect.setPosition(Bird.bBird.x, -30);
        bBot.setPosition(posBot.x, posBot.y);
    }

    public boolean collides(Rectangle player) {
        boolean w = player.overlaps(bTop) || player.overlaps(bBot) || player.overlaps(groundRect);
        if (w) {
            die = Gdx.audio.newMusic(Gdx.files.internal("die.wav"));
            die.setVolume(0.15f);
            die.play();
        }
        return w;
    }


    public void dispose() {
        Top.dispose();
        Bot.dispose();
    }
}
