package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthograpicCamera;
	private Music music;
	private Sound noteSound;

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

		// load the drop sound effect and the rain background "music"
		noteSound = Gdx.audio.newSound(Gdx.files.internal("noteSFX.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));

		// start the playback of the background music immediately
		music.setLooping(true);
		music.play();
	}
}
