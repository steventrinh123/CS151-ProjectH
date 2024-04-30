package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class timerHud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer timeCount = 0;
    private Label timeLabel;

    private float time;

    private ShapeRenderer shapeRenderer;

    public timerHud(SpriteBatch batch){

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label(String.format("%03d", timeCount), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel.setPosition(40,50);

        table.add(timeLabel).expandX().padTop(10);
        stage.addActor(table);


    }


    public void update(float t){
        time += t;
        timeCount++;
            timeLabel.setText(String.format("%03d", timeCount/60));
            System.out.println(timeLabel);
    }
    @Override
    public void dispose() { stage.dispose(); }
}
