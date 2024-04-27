package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ProjectH;
import objects.player.Player;
import helper.TileMapHelper;


import java.awt.*;

import static helper.Constants.PPM;

public class GameScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private ProjectH game;
    private Music music;
    private Sound noteSound;

    private boolean soundCheck = false;

    private boolean platformerWinCheck = false;
    private boolean platformWinScreenChange = false;



    //game objects
    private Player player;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    public GameScreen(OrthographicCamera camera) {
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-50f), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();

        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
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
            noteSound = Gdx.audio.newSound(Gdx.files.internal("noteSFX.mp3"));
            music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));

            // start the playback of the background music immediately
            music.setLooping(true);
            music.setVolume(0.2f);
            music.play();
            soundCheck = true;
        }

        batch.begin();
        //render objects
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        batch.end();

        if(player.getBody().getPosition().x>130 && player.getBody().getPosition().y > 185){
            platformerWinCheck = true;
            music.pause();
            ProjectH.INSTANCE.setScreen(new MenuScreen(game));

        }




    }


    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();

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
}
