
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
    private ProjectH game;
    private SpriteBatch batch;
    Texture inactivePlayButton;

    Texture activePlayButton;

    Texture inactiveRhythmButton;
    Texture activeRhythmButton;
    boolean checkButton1 = true;
    boolean checkButton2 = true;
    private Sound buttonSound;

    private OrthographicCamera orthograpicCamera = new OrthographicCamera();


    public MenuScreen(ProjectH game) {
        this.game = game;
    }

    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 150, 5, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        //creates buttons
        inactivePlayButton = new Texture(Gdx.files.internal("buttons/inactivePlay.png"));
        activePlayButton = new Texture(Gdx.files.internal("buttons/activePlay.png"));

        inactiveRhythmButton = new Texture(Gdx.files.internal("buttons/inactiveRhythm.png"));
        activeRhythmButton = new Texture(Gdx.files.internal("buttons/activeRhythm.png"));



        batch.begin();

        if(Gdx.input.getX()<700 && Gdx.input.getX()>600 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton1){
                batch.draw(activePlayButton,600,400,100,50);
                batch.draw(inactiveRhythmButton, 300, 400, 100, 50);
            if (Gdx.input.isTouched()){
                buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonClickSound.mp3"));

                buttonSound.play();

                ProjectH.INSTANCE.setScreen(new GameScreen(orthograpicCamera));
                checkButton1 =false;

            }
        }else if(Gdx.input.getX()<400 && Gdx.input.getX()>300 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton2){
            batch.draw(inactivePlayButton,600,400,100,50);
            batch.draw(activeRhythmButton, 300, 400, 100, 50);
            if (Gdx.input.isTouched()){
                buttonSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonClickSound.mp3"));

                buttonSound.play();

                ProjectH.INSTANCE.setScreen(new GameScreen(orthograpicCamera));
                checkButton1 =false;

            }
        }else{
            batch.draw(inactivePlayButton,600,400,100,50);
            batch.draw(inactiveRhythmButton,300,400,100,50);
        }


        batch.end();


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
