package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ProjectH;

public class WinScreen extends ScreenAdapter {

    private final SpriteBatch batch = new SpriteBatch();
    private final Texture winback = new Texture(Gdx.files.internal("background/winbackmenu.png"));
    private final Texture winImage = new Texture(Gdx.files.internal("background/winImage.png"));

    public WinScreen(ProjectH game) {
        ProjectH.INSTANCE = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(winImage, 480,300,400,200);
        batch.draw(winback, 300, 50,720,360);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            this.dispose();
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.M)){
            this.dispose();
            ProjectH.INSTANCE.platformWinCheck = false;
            ProjectH.INSTANCE.rhythmWinCheck = false;
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