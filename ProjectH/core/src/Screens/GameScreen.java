package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
    private SpriteBatch gameScreenBatch;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Music music;
    private timerHud hud;
    private int coinCounter;
    private boolean soundPlayed = false;
    private ArrayList<Coin> coinsArr_;

    private int widthScreen, heightScreen;

    private boolean soundCheck = false;


    //game objects
    private Player player;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;
    private Texture playerModel;



    public GameScreen(OrthographicCamera camera) {
        widthScreen = Gdx.graphics.getWidth();
        heightScreen = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, widthScreen, heightScreen);
        this.camera = camera;
        this.gameScreenBatch = new SpriteBatch();
        this.world = new World(new Vector2(0,-50f), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        hud = new timerHud(gameScreenBatch);
        playerModel = new Texture(Gdx.files.internal("buttons/playerModel.png"));


        this.tileMapHelper = new TileMapHelper(this);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();


        Vector3 test = camera.position;
        test.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        test.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        System.out.print(test);
        int xOffset = 608;
        int yOffset = 328;
        coinCounter = 0;
        coinsArr_ = new ArrayList<Coin>();
        coinsArr_.add(new Coin(2310 + xOffset, 1816 + yOffset));
        coinsArr_.add(new Coin(4160 + xOffset, 2061 + yOffset));
        coinsArr_.add(new Coin(1727 + xOffset, 2884 + yOffset));
        coinsArr_.add(new Coin(3315 + xOffset, 3178 + yOffset));
        coinsArr_.add(new Coin(3001 + xOffset, 3677 + yOffset));
        coinsArr_.add(new Coin(1696 + xOffset, 4132 + yOffset));
        coinsArr_.add(new Coin(4332 + xOffset, 4914 + yOffset));
        coinsArr_.add(new Coin(3142 + xOffset, 5016 + yOffset));
        coinsArr_.add(new Coin(1528 + xOffset, 5293 + yOffset));
        coinsArr_.add(new Coin(2885 + xOffset, 5790 + yOffset));




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
            music.setVolume(0.1f);
            music.play();
            soundCheck = true;
        }

        for(int i = 0; i < coinsArr_.size(); i++){
            Coin temp = coinsArr_.get(i);
            temp.x_ = temp.initialCords.x - camera.position.x;
            temp.y_ = temp.initialCords.y - camera.position.y;
            temp.coin_.setPosition(temp.x_, temp.y_);

            //System.out.println((temp.initialCords.x - camera.position.x) + " // " + (temp.initialCords.y - camera.position.y));


        }

        gameScreenBatch.begin();
        //render objects
        //coinSprite.draw(gameScreenBatch;

        //gameScreenBatch.draw(playerModel,player.getBody().getPosition().x-1, player.getBody().getPosition().y-1, 2,2);



        // Display the World
        displayWorld();
        gameScreenBatch.begin();
        gameScreenBatch.draw(playerModel, 608, 328, 64,64);
        // Coin logic
        for(Coin coin : coinsArr_) {
            coin.coinDisplay(gameScreenBatch);
            //coinCounter += coin.inRegion(630,350, 64);
            //hud.updateCoinCount(coinCounter);
        }
        gameScreenBatch.end();
        hud.draw(gameScreenBatch);
        //gameScreenBatch.end();

        for(Coin coin : coinsArr_) {
            for(int i = 0; i < 4; i++){
                coinCounter+= coin.inRegion(608+(64*(i%2)), 324+64*(i/2));
                //gameScreenBatch.draw(testTexture, 608+(64*(i%2)),324+64*(i/2) ,10,10);
            }
            //coin.coinDisplay(gameScreenBatch);
            coinCounter += coin.inRegion(630,350);
            hud.updateCoinCount(coinCounter);
        }

        //gameScreenBatch.end();



        // Checking the end conditions
        int end_offset_x = 127;
        int end_offset_y = 185;

        if (end(player, end_offset_x, end_offset_y)) {
            // Done
        }

    }

    /* @brief Ends the game when the offsets are met
    *  @param player
    *  @param end_offset_x
    *  @param end_offset_y
    *  @return boolean on whether we finished the game
    * */
    boolean end(Player player, int end_offset_x, int end_offset_y) {
        float x = player.getBody().getPosition().x;
        float y = player.getBody().getPosition().y;

        if (x > end_offset_x && y > end_offset_y && coinCounter == 10) {
            ProjectH.INSTANCE.platformWinCheck = true;
            music.stop();
            if(ProjectH.INSTANCE.platformWinCheck && ProjectH.INSTANCE.rhythmWinCheck) {
                ProjectH.INSTANCE.setScreen(new WinScreen(ProjectH.INSTANCE));
            }
            else{
                ProjectH.INSTANCE.setScreen(new MenuScreen(ProjectH.INSTANCE));
            }

            return true;
        }

        return false;
    }

    public void displayWorld() {
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
        gameScreenBatch.end();
        gameScreenBatch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.draw(gameScreenBatch);
    }

    private void update() {
        world.step(1/60f, 6, 2);
        cameraUpdate();

        gameScreenBatch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
        player.update();
        hud.update();



        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            Gdx.app.exit();
        }

    }




    private void cameraUpdate(){
        Vector3 position = camera.position;
        System.out.println(position);
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
        gameScreenBatch.dispose();
    }
}
