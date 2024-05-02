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
    private SpriteBatch menuBatch;
    Texture inactivePlatformButton;

    Texture activePlatformButton;

    Texture inactiveRhythmButton;
    Texture activeRhythmButton;
    Texture inactiveExit;
    Texture activeExit;
    Texture title;
    RhythmGame rhythmGame1;
    boolean checkButton = true;
    private Sound buttonSound;

    private final OrthographicCamera orthographicCamera = new OrthographicCamera();


    public MenuScreen(ProjectH game) {
        ProjectH.INSTANCE = game;
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
        title = new Texture(Gdx.files.internal("background/gameTitle.png"));

        //creates buttons
        inactivePlatformButton = new Texture(Gdx.files.internal("buttons/inactivePlatform.png"));
        activePlatformButton = new Texture(Gdx.files.internal("buttons/activePlatform.png"));
        inactiveRhythmButton = new Texture(Gdx.files.internal("buttons/inactiveRhythm.png"));
        activeRhythmButton = new Texture(Gdx.files.internal("buttons/activeRhythm.png"));
        inactiveExit = new Texture(Gdx.files.internal("buttons/inactiveExit.png"));
        activeExit = new Texture(Gdx.files.internal("buttons/activeExit.png"));

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonClickSound.mp3"));



        menuBatch.begin();

        menuBatch.draw(inactiveRhythmButton,300,400,100,50);
        menuBatch.draw(inactivePlatformButton,600,400,100,50);
        menuBatch.draw(inactiveExit, 900, 400, 100,50);

        menuBatch.draw(title, 570,500,200,100);

        if(Gdx.input.getX()<700 && Gdx.input.getX()>600 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            menuBatch.draw(activePlatformButton,600,400,100,50);
            if (Gdx.input.isTouched()){


                buttonSound.play();
                this.dispose();
                ProjectH.INSTANCE.setScreen(new GameScreen(orthographicCamera));
                checkButton=false;

            }
        }else if(Gdx.input.getX()<400 && Gdx.input.getX()>300 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            menuBatch.draw(activeRhythmButton,300,400,100,50);
            if (Gdx.input.isTouched()){


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