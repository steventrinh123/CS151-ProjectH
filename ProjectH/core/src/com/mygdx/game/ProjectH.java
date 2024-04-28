package com.mygdx.game;


import Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;

	private ProjectH test;

	private SpriteBatch batch;

	boolean menuCheck = true;
	public ProjectH() {
		INSTANCE = this;


	}

	@Override
	public void create () {
		batch = new SpriteBatch();
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
	}

	@Override
	public void dispose () {
		//Dispose Assets which you used in the whole Game
	}

}
