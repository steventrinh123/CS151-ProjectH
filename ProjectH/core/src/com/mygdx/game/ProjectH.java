package com.mygdx.game;


import Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;

	private SpriteBatch gameBatch;
	public boolean platformWinCheck = false;
	public boolean rhythmWinCheck = false;

	boolean menuCheck = true;
	public ProjectH() {
		INSTANCE = this;


	}


	@Override
	public void create () {
		gameBatch = new SpriteBatch();
	}

	@Override
	public void render () {
		//Clear the Screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Render the current Screen
		super.render();
		if(menuCheck) {
			menuCheck = false;
			this.setScreen(new MenuScreen(ProjectH.INSTANCE));
		}


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
		}

	}

	//Dispose Assets
	@Override
	public void dispose () {

	}

}