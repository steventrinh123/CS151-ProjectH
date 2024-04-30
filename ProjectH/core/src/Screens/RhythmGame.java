package Screens;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class RhythmGame extends ApplicationAdapter{
    private Texture noteTexture;
    private Texture characterImage;
    private Texture score300;
    private Texture score100;
    private Texture scoreMiss;
    private Sound soundEffect;
    private Music music;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle character;
    private Array<Rectangle> notes;
    private long lastDropTime;
    private int counter = 1;

    private HashMap<Integer, Float> listOfNotes = new HashMap<>();

    @Override
    public void create() {
        // load the images for the note and the character, 64x64 pixels each
        noteTexture = new Texture(Gdx.files.internal("note2.png"));
        characterImage = new Texture(Gdx.files.internal("frij.png"));

        score300 = new Texture(Gdx.files.internal("300.png"));
        score100 = new Texture(Gdx.files.internal("100.png"));
        scoreMiss = new Texture(Gdx.files.internal("miss.png"));


        // load the note sound effect and the music
        soundEffect = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("audio1.mp3"));
        //music = Gdx.audio.newMusic(Gdx.files.internal("audio2.mp3"));
        //music = Gdx.audio.newMusic(Gdx.files.internal("audio3_160bpm.mp3"));

        // start the playback of the music immediately
        music.play();

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        //camera.setToOrtho(false, 1500, 642);
        batch = new SpriteBatch();

        // create a Rectangle to logically represent the character
        character = new Rectangle();
        character.x = 800 / 2 - 64 / 2; // center the character horizontally
        character.y = 20; // bottom left corner of the character is 20 pixels above the bottom screen edge
        character.width = 64;
        character.height = 64;

        // create the notes array
        notes = new Array<Rectangle>();
    }

    private void spawnRaindrop(int a) {
        Rectangle raindrop;
        createPattern();

        raindrop = new Rectangle();
        raindrop.x = listOfNotes.get(a);

        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        notes.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();


    }

    public void createPattern(){

        // note is shown on screen (& meant to be hit) if the float value (second parameter) is within 800F
        // any note > 800F is to be treated as a break (or gap) and not for the user to hit
        listOfNotes.put(1, 400F);
        listOfNotes.put(2, 350F);
        listOfNotes.put(3, 400F);
        listOfNotes.put(4, 350F);
        listOfNotes.put(5, 5000F);
        listOfNotes.put(6, 600F);
        listOfNotes.put(7, 400F);
        listOfNotes.put(8, 550F);

        listOfNotes.put(9, 400F);
        listOfNotes.put(10, 450F);
        listOfNotes.put(11, 380F);
        listOfNotes.put(12, 500F);
        listOfNotes.put(13, 4000F);
        listOfNotes.put(14, 290F);
        listOfNotes.put(15, 350F);
        listOfNotes.put(16, 420F);

        listOfNotes.put(17, 500F);
        listOfNotes.put(18, 400F);
        listOfNotes.put(19, 300F);
        listOfNotes.put(20, 200F);
        listOfNotes.put(21, 350F);
        listOfNotes.put(22, 5000F);
        listOfNotes.put(23, 300F);
        listOfNotes.put(24, 450F);

        listOfNotes.put(25, 600F);
        listOfNotes.put(26, 450F);
        listOfNotes.put(27, 650F);
        listOfNotes.put(28, 550F);
        listOfNotes.put(29, 3500F);
        listOfNotes.put(30, 500F);
        listOfNotes.put(31, 400F);
        listOfNotes.put(32, 350F);

        listOfNotes.put(33, 400F);
        listOfNotes.put(34, 6000F);
        listOfNotes.put(35, 430F);
        listOfNotes.put(36, 550F);
        listOfNotes.put(37, 350F);
        listOfNotes.put(38, 5000F);
        listOfNotes.put(39, 400F);
        listOfNotes.put(40, 6500F);

        listOfNotes.put(41, 550F);
        listOfNotes.put(42, 3800F);
        listOfNotes.put(43, 450F);
        listOfNotes.put(44, 550F);
        listOfNotes.put(45, 390F);
        listOfNotes.put(46, 3000F);
        listOfNotes.put(47, 400F);

        listOfNotes.put(48, 500F);
        listOfNotes.put(49, 300F);
        listOfNotes.put(50, 370F);
        listOfNotes.put(51, 200F);
        listOfNotes.put(52, 350F);
        listOfNotes.put(53, 2200F);
        listOfNotes.put(54, 400F);
        listOfNotes.put(55, 550F);

        listOfNotes.put(56, 500F);
        listOfNotes.put(57, 600F);
        listOfNotes.put(58, 450F);
        listOfNotes.put(59, 600F);
        listOfNotes.put(60, 400F);
        listOfNotes.put(61, 2000F);
        listOfNotes.put(62, 350F);
        listOfNotes.put(63, 500F);

        listOfNotes.put(64, 300F);
        listOfNotes.put(65, 500F);
        listOfNotes.put(66, 350F);
        listOfNotes.put(67, 600F);
        listOfNotes.put(68, 450F);
        listOfNotes.put(69, 200F);
        listOfNotes.put(70, 4500F);
        listOfNotes.put(71, 200F);

        listOfNotes.put(72, 400F);
        listOfNotes.put(73, 600F);
        listOfNotes.put(74, 350F);
        listOfNotes.put(75, 600F);
        listOfNotes.put(76, 350F);
        listOfNotes.put(77, 2000F);
        listOfNotes.put(78, 400F);
        listOfNotes.put(79, 600F);

        listOfNotes.put(80, 400F);
        listOfNotes.put(81, 6000F);
        listOfNotes.put(82, 350F);
        listOfNotes.put(83, 600F);
        listOfNotes.put(84, 350F);
        listOfNotes.put(85, 2000F);
        listOfNotes.put(86, 400F);
        listOfNotes.put(87, 6000F);

        listOfNotes.put(88, 500F);
        listOfNotes.put(89, 3000F);
        listOfNotes.put(90, 370F);
        listOfNotes.put(91, 200F);
        listOfNotes.put(92, 350F);
        listOfNotes.put(93, 2200F);
        listOfNotes.put(94, 400F);
        listOfNotes.put(95, 550F);

        listOfNotes.put(96, 500F);
        listOfNotes.put(97, 600F);
        listOfNotes.put(98, 450F);
        listOfNotes.put(99, 600F);
        listOfNotes.put(100, 400F);
        listOfNotes.put(101, 200F);
        listOfNotes.put(102, 350F);
        listOfNotes.put(103, 500F);

        listOfNotes.put(104, 300F);
        listOfNotes.put(105, 500F);
        listOfNotes.put(106, 350F);
        listOfNotes.put(107, 600F);
        listOfNotes.put(108, 450F);
        listOfNotes.put(109, 200F);
        listOfNotes.put(110, 450F);
        listOfNotes.put(111, 200F);

        listOfNotes.put(112, 400F);
        listOfNotes.put(113, 600F);
        listOfNotes.put(114, 350F);
        listOfNotes.put(115, 600F);
        listOfNotes.put(116, 350F);
        listOfNotes.put(117, 200F);
        listOfNotes.put(118, 400F);
        listOfNotes.put(119, 600F);

        listOfNotes.put(120, 400F);
        listOfNotes.put(121, 600F);
        listOfNotes.put(122, 350F);
        listOfNotes.put(123, 600F);
        listOfNotes.put(124, 350F);
        listOfNotes.put(125, 200F);
        listOfNotes.put(126, 400F);
        listOfNotes.put(127, 600F);

        listOfNotes.put(128, 500F);
        listOfNotes.put(129, 300F);
        listOfNotes.put(130, 370F);
        listOfNotes.put(131, 200F);
        listOfNotes.put(132, 350F);
        listOfNotes.put(133, 220F);
        listOfNotes.put(134, 400F);
        listOfNotes.put(135, 550F);

    }

    @Override
    public void render() {
        // clear the screen with a dark blue color.
        // The arguments to clear are the red, green blue and alpha component in the range [0,1] of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Texture img = new Texture("bg.jpg");
        batch.begin();
        batch.draw(img ,0, 0);
        batch.end();

        if(!music.isPlaying()){
            //exit app when the music ends
            Gdx.app.exit();
        }

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the character and all notes
        batch.begin();
        batch.draw(characterImage, character.x, character.y);
        for(Rectangle raindrop: notes) {
            batch.draw(noteTexture, raindrop.x, raindrop.y);
        }
        batch.end();

        // process user input
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            character.x = touchPos.x - 64 / 2;
        }

        // move character to the left when LEFT key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        // move character to the right when RIGHT key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            character.x += 200 * Gdx.graphics.getDeltaTime();
        }

        // increase the speed of the character to -600 when LEFT is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            character.x -= 600 * Gdx.graphics.getDeltaTime();
        }

        // increase the speed of the character to 600 when RIGHT is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            character.x += 600 * Gdx.graphics.getDeltaTime();
        }

        // close the app when the user press ESCAPE key
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        // make sure the character stays within the screen bounds
        if(character.x < 0) character.x = 0;
        if(character.x > 800 - 64) character.x = 800 - 64;

        //sets the BPM to 151 to match the song -> 397350993 (current)
        //sets the BPM to 160 to match the song -> 395000000
        if(TimeUtils.nanoTime() - lastDropTime > 397350993) {
            System.out.println("Test");
            spawnRaindrop(counter);
            System.out.println(counter);
            counter++;

        }

        // move the notes down, remove any that are beneath the bottom edge of
        // the screen or that hit the character. In the latter case we play back
        // a sound effect if the hit accuracy is 100 or 300.
        for (Iterator<Rectangle> iter = notes.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 500 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            batch.begin();
            if(raindrop.y <= 64) {
                if (raindrop.overlaps(character) && Math.abs(raindrop.x - character.x) < 20) {
                    soundEffect.play();
                    batch.draw(score300, raindrop.x, raindrop.y + 64);
                    iter.remove();

                }else if(raindrop.overlaps(character) && Math.abs(raindrop.x - character.x) < 50 ){
                    soundEffect.play();
                    batch.draw(score100, raindrop.x, raindrop.y + 64);
                    iter.remove();

                } else {
                    batch.draw(scoreMiss, raindrop.x, raindrop.y + 64);
                    iter.remove();
                }
            }
            batch.end();
        }
    }

    @Override
    public void dispose() {
        // dispose of all the native resources
        noteTexture.dispose();
        characterImage.dispose();
        soundEffect.dispose();
        music.dispose();
        batch.dispose();
    }
}
