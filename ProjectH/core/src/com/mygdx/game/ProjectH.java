package com.mygdx.game;


import Screens.GameScreen;
import Screens.GameScreen2;
import Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProjectH extends Game {

	public static ProjectH INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthograpicCamera;
	private Music music;
	private Sound noteSound;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;

	private GameScreen2 gameScreen2;
	private SpriteBatch batch;

	Texture inactivePlayButton;

	Texture activePlayButton;

	public ProjectH() {
		INSTANCE = this;


	}

	
	/*@Override
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
	}*/

	@Override
	public void create () {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.orthograpicCamera = new OrthographicCamera();
		this.orthograpicCamera.setToOrtho(false, widthScreen, heightScreen);
		batch = new SpriteBatch();

		//creates buttons
		inactivePlayButton = new Texture(Gdx.files.internal("buttons/inactivePlay.png"));
		activePlayButton = new Texture(Gdx.files.internal("buttons/activePlay.png"));
		//changeScreen(menuScreen);

		// load the drop sound effect and the rain background "music"
		noteSound = Gdx.audio.newSound(Gdx.files.internal("noteSFX.mp3"));
		music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));

		// start the playback of the background music immediately
		music.setLooping(true);
		music.setVolume(0.4f);
		music.play();

	}

	public void changeScreen(Screen newScreen){
		Screen oldScreen = getScreen();
		setScreen(newScreen);
		//Dispose the old screen to release resources
		if(oldScreen != null)
			oldScreen.dispose();
	}

	@Override
	public void render () {
		//Clear the Screen
		Gdx.gl.glClearColor(0, 0, 5, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		if(Gdx.input.getX()<400 && Gdx.input.getX()>300 && Gdx.input.getY()<200 && Gdx.input.getY()>150){
			batch.draw(activePlayButton,300,300,100,50);
			if (Gdx.input.isTouched()){
				this.setScreen(new GameScreen(orthograpicCamera));
			}
		}
		else{
			batch.draw(inactivePlayButton,300,300,100,50);
		}


		batch.end();

		//Render the current Screen
		super.render();


		if(Gdx.input.isKeyPressed(Input.Keys.P)){
			this.setScreen(new GameScreen2(orthograpicCamera));
		}
		//restart parkour level
		if(Gdx.input.isKeyPressed(Input.Keys.R)){
			this.setScreen(new GameScreen(orthograpicCamera));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.M)){
			this.setScreen(menuScreen);
		}
	}

	@Override
	public void dispose () {
		//Dispose Assets which you used in the whole Game
	}

}
