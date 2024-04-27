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
	private ProjectH test;

	private GameScreen2 gameScreen2;
	private SpriteBatch batch;

	Texture inactivePlayButton;

	Texture activePlayButton;

	boolean checkButton = true;

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



	}

	@Override
	public void render () {
		//Clear the Screen
		Gdx.gl.glClearColor(0, 0, 5, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		batch.begin();

		if(Gdx.input.getX()<700 && Gdx.input.getX()>600 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
			batch.draw(activePlayButton,600,400,100,50);
			if (Gdx.input.isTouched()){
				this.setScreen(new GameScreen(orthograpicCamera));
				checkButton=false;

			}
		}
		else{
			batch.draw(inactivePlayButton,600,400,100,50);
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

		if(Gdx.input.isKeyPressed(Input.Keys.J)){
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
