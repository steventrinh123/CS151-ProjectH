package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
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
    boolean checkButton = true;

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


        batch.begin();

        if(Gdx.input.getX()<700 && Gdx.input.getX()>600 && Gdx.input.getY()<325 && Gdx.input.getY()>270 && checkButton){
            batch.draw(activePlayButton,600,400,100,50);
            if (Gdx.input.isTouched()){
                ProjectH.INSTANCE.setScreen(new GameScreen(orthograpicCamera));
                checkButton=false;

            }
        }
        else{
            batch.draw(inactivePlayButton,600,400,100,50);
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
