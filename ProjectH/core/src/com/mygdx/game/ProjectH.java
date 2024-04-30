package com.mygdx.game;


import Screens.MenuScreen;
import Screens.RhythmGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;

	private ProjectH test;

	private SpriteBatch gameBatch;

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
			this.setScreen(new MenuScreen(test));
		}


		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.U))
		{
			this.dispose();

		}

	}

	@Override
	public void dispose () {
		//Dispose Assets which you used in the whole Game
	}

}
