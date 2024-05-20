package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ProjectH;

import java.awt.*;

public class HelpScreen extends ScreenAdapter {

    private Batch HelpBatch;
    private Texture infoPNG;
    public HelpScreen() {
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.6f, 0.4f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        HelpBatch = new SpriteBatch();
        infoPNG = new Texture(Gdx.files.internal("background/infoPNG.png"));


        HelpBatch.begin();
        HelpBatch.draw(infoPNG, 0, 0, 1280, 720);
        HelpBatch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.M)){
            this.dispose();
            ProjectH.INSTANCE.setScreen(new MenuScreen(ProjectH.INSTANCE));
        }

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
