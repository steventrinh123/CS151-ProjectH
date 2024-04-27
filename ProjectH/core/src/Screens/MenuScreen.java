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
    private OrthographicCamera camera;
    private SpriteBatch batch;
    Texture inactivePlayButton;

    Texture activePlayButton;

    Texture bad;
    private OrthographicCamera orthograpicCamera;


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


        bad = new Texture(Gdx.files.internal("badlogic.jpg"));


        batch.begin();
        batch.draw(bad,600,400,500,600);
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
