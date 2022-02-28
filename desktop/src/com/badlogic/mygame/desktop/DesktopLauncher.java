package com.badlogic.mygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

// importing the Game app
import com.badlogic.mygame.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// name at the top of the window
		config.title = "MyGame";
		//config.resizable = false;

		config.width = 480;
		config.height = 480;

		// calling the Game App
		new LwjglApplication(new MyGame(), config);
	}
}
