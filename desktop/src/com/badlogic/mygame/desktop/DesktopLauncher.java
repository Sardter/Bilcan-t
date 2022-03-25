package com.badlogic.mygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.mygame.BilcantGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Bilcant";
		config.width = 900;
		config.height = 580;
		new LwjglApplication(new BilcantGame(), config);
	}
}
