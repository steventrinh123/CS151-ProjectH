package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//sets fps to 60
		config.setForegroundFPS(60);
		config.setIdleFPS(60);
		//title of game
		config.setTitle("project-h");
		//changes window screen size to 1280, 720
		config.setWindowedMode(1280,720);
		new Lwjgl3Application(new ProjectH(), config);
	}
}
