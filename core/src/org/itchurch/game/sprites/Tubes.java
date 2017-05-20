package org.itchurch.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tubes {


    public static final int otklon = 130;
    public static final int gap = 100;
    public static final int botborder = 120;
    public static final int twidth = 52;

    private Texture Top, Bot, groundp;
    private Vector2 posTop, posBot;
    private Random random;
    private Rectangle bTop, bBot, groundRect;


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
        groundp = new Texture("ground.png");
        random = new Random();

        posTop = new Vector2(x, random.nextInt(otklon) + gap + botborder);
        posBot = new Vector2(x, posTop.y - gap - Bot.getHeight());

        bTop = new Rectangle(posTop.x, posTop.y, Top.getWidth(), Top.getHeight());
        bBot = new Rectangle(posBot.x, posBot.y, Bot.getWidth(), Bot.getHeight());
        groundRect = new Rectangle(0, -30, groundp.getWidth(), groundp.getHeight());

    }

    public void reposition(float x) {
        posTop.set(x, random.nextInt(otklon) + gap + botborder);
        posBot.set(x, posTop.y - gap - Bot.getHeight());
        bTop.setPosition(posTop.x, posTop.y);
        bBot.setPosition(posBot.x, posBot.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(bTop) || player.overlaps(bBot) || player.overlaps(groundRect);
    }

    public void dispose() {
        Top.dispose();
        Bot.dispose();
        groundp.dispose();
    }
}
