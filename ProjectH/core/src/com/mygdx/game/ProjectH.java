package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthograpicCamera;

	public ProjectH() {
		INSTANCE = this;
	}

	
	@Override
	public void create () {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.orthograpicCamera = new OrthographicCamera();
		this.orthograpicCamera.setToOrtho(false, widthScreen, heightScreen);
		setScreen(new GameScreen(orthograpicCamera));
	}
}
