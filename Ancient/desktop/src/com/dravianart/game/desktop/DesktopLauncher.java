package com.dravianart.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dravianart.game.Ancient;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS=60;
		config.width=Ancient.WIDTH;
		config.height=Ancient.HEIGHT;
		new LwjglApplication(new Ancient(), config);	
	}
}
  