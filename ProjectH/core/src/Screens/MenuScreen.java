package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ProjectH;

public class MenuScreen implements Screen {
    private final ProjectH game;
    private SpriteBatch menuBatch;
    Texture inactivePlayButton;

    Texture activePlayButton;

    Texture inactiveRhythmButton;
    Texture activeRhythmButton;
    Texture inactiveExit;
    Texture activeExit;
    RhythmGame rhythmGame1;
    boolean checkButton = true;
    private Sound buttonSound;

    private final OrthographicCamera orthographicCamera = new OrthographicCamera();


    public MenuScreen(ProjectH game) {
        this.game = game;
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menuBatch = new SpriteBatch();
        rhythmGame1 = new RhythmGame();

        //creates buttons
        inactivePlayButton = new Texture(Gdx.files.internal("buttons/inactivePlay.png"));
        activePlayButton = new Texture(Gdx.files.internal("buttons/activePlay.png"));
        inactiveRhythmButton = new Texture(Gdx.files.internal("buttons/inactiveRhythm.png"));
        activeRhythmButton = new Texture(Gdx.files.internal("buttons/activeRhythm.png"));

        inactiveExit = new Texture(Gdx.files.internal("buttons/inactiveExit.png"));
        activeExit = new Texture(Gdx.files.internal("buttons/activeExit.png"));



        menuBatch.begin();

        menuBatch.draw(inactiveRhythmButton,300,400,100,50);
        menuBatch.draw(inactivePlayButton,600,400,100,50);
        menuBatch.draw(inactiveExit, 900, 400, 100,50);

        if(Gdx.input.getX()<700 && Gdx.input.getX()>600 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            menuBatch.draw(activePlayButton,600,400,100,50);
            if (Gdx.input.isTouched()){
                buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonClickSound.mp3"));

                buttonSound.play();

                ProjectH.INSTANCE.setScreen(new GameScreen(orthographicCamera));
                checkButton=false;

            }
        }else if(Gdx.input.getX()<400 && Gdx.input.getX()>300 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            menuBatch.draw(activeRhythmButton,300,400,100,50);
            if (Gdx.input.isTouched()){
                buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonClickSound.mp3"));

                buttonSound.play();
                this.dispose();
                ProjectH.INSTANCE.setScreen(rhythmGame1);
                checkButton=false;

            }
        }else if(Gdx.input.getX()<1000 && Gdx.input.getX()>900 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            menuBatch.draw(activeExit,900,400,100,50);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }

        menuBatch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}