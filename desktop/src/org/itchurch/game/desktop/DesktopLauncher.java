package org.itchurch.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.itchurch.game.SnatchyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SnatchyBird.WIDTH;
		config.height = SnatchyBird.HEIGHT;
		config.title = SnatchyBird.TITLE;
		new LwjglApplication(new SnatchyBird(), config);
	}
}
