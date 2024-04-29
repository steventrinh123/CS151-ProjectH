package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ProjectH;
import objects.player.Coin;
import objects.player.Player;
import helper.TileMapHelper;

import java.util.ArrayList;

import static helper.Constants.PPM;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private ProjectH game;
    private Music music;
    private timerHud hud;
    private Texture coinTexture;
    private Sprite coinSprite;
    private int coinCounter;
    private boolean soundPlayed = false;
    private ArrayList<Coin> coinsArr_;

    private int widthScreen, heightScreen;

    private boolean soundCheck = false;

    private boolean platformerWinCheck = false;

    //game objects
    private Player player;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;


    public GameScreen(OrthographicCamera camera) {
        widthScreen = Gdx.graphics.getWidth();
        heightScreen = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, widthScreen, heightScreen);
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-50f), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        hud = new timerHud(batch);


        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();

        /*
        coinsArr_ = new ArrayList<Coin>();
        coinsArr_.add(new Coin(50, 60));
        coinsArr_.add(new Coin(70, 60));
        coinsArr_.add(new Coin(90, 60));

         */

        coinTexture = new Texture(Gdx.files.internal("buttons/coin.jpeg"));
        coinSprite = new Sprite(coinTexture, 0, 0, coinTexture.getWidth(), coinTexture.getHeight());
        coinSprite.setPosition(100,50);
    }

    @Override
    public void render(float delta)
    {
        this.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthogonalTiledMapRenderer.render();

        if(!soundCheck) {
            // load the drop sound effect and the rain background "music"

            music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));

            // start the playback of the background music immediately
            music.setLooping(true);
            music.setVolume(0.2f);
            music.play();
            soundCheck = true;
        }


        batch.begin();
        //render objects
        //coinSprite.draw(batch);

        for(Coin coin : coinsArr_) {
            coin.coinDisplay(batch, coin.getX(), coin.getY());
        }

        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        batch.end();
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(player.getBody().getPosition().x>127 && player.getBody().getPosition().y > 185 && coinCounter == 3){
            platformerWinCheck = true;
            music.pause();
            ProjectH.INSTANCE.setScreen(new MenuScreen(game));

        }
        if(player.getBody().getPosition().x>=99 && player.getBody().getPosition().x<=101 && player.getBody().getPosition().y>=49
        && player.getBody().getPosition().y<=51) {
            coinSprite.setPosition(0,0);
            coinCounter++;
            Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coinFound.mp3"));
            if(!soundPlayed) {
                coinSound.play();
                soundPlayed = true;
            }

    }

    }


    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        hud.update(1000);


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }

    }




    private void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public World getWorld() {
        return world;
    }


    @Override
    public void dispose() {
        batch.dispose();
        coinTexture.dispose();
    }
}
