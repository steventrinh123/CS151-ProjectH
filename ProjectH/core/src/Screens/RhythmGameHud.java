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

public class RhythmGameHud implements Disposable {
    public Stage stage;
    private final Viewport viewport;
    private Integer rhythmGamePoints = 0;
    private final Label pointLabel;

    private final ShapeRenderer shapeRenderer;

    public RhythmGameHud(SpriteBatch batch){

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        pointLabel = new Label(String.format("%08d", rhythmGamePoints), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pointLabel.setPosition(40,50);

        table.add(pointLabel).expandX().padTop(10);
        stage.addActor(table);


        shapeRenderer = new ShapeRenderer();
    }


    public void draw(SpriteBatch batch) {
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(0, stage.getHeight() - 40, stage.getWidth(), 40);
        shapeRenderer.end();
        batch.end();
        stage.draw();
    }

    public void update(int score){

        rhythmGamePoints +=score;
        pointLabel.setText(String.format("%08d", rhythmGamePoints));
    }
    @Override
    public void dispose() {
        stage.dispose();
    }

    public int getPoints(){
        return rhythmGamePoints;
    }
}
