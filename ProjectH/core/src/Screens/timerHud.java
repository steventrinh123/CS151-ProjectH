package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class timerHud implements Disposable {
    public Stage stage;
    private final Viewport viewport;
    private Integer timeCount = 0;
    private final Label timeLabel;
    private final Label coinCountLabel;
    private float time;
    private final ShapeRenderer shapeRenderer;

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

        coinCountLabel = new Label("Coins: 0/10", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coinCountLabel.setPosition(10, stage.getHeight() - 30);
        stage.addActor(coinCountLabel);

        shapeRenderer = new ShapeRenderer();
    }

    public void updateCoinCount(int coins) {
        coinCountLabel.setText("Coins: " + coins + "/10");
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

    public void update(){
        timeCount++;
            timeLabel.setText(String.format("%03d", timeCount/60));
    }
    @Override
    public void dispose() {
        stage.dispose();
        coinCountLabel.getStyle().font.dispose();
    }
}
